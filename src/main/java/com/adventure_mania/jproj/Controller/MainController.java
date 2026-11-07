package com.adventure_mania.jproj.Controller;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adventure_mania.jproj.Entities.CharSkills;
import com.adventure_mania.jproj.Entities.CharacterEnt;
import com.adventure_mania.jproj.Entities.MonsterEnt;
import com.adventure_mania.jproj.Entities.SkillType;
import com.adventure_mania.jproj.Entities.UserEnt;
import com.adventure_mania.jproj.Records.CharacterDetails;
import com.adventure_mania.jproj.Repository.CharSkillsRepo;
import com.adventure_mania.jproj.Repository.CharacterRepo;
import com.adventure_mania.jproj.Repository.MonsterRepo;
import com.adventure_mania.jproj.Repository.UserRepo;
import com.adventure_mania.jproj.Services.Monsters;

import jakarta.servlet.http.HttpSession;

@Controller 
@RequestMapping ("/")
public class MainController {
    private final UserRepo userRepo;
    private final CharacterRepo characterRepo;
    private final MonsterRepo monsterRepo;
    private final Monsters monsters;
    private final CharSkillsRepo charSkills;

    public MainController(UserRepo userRepo, CharacterRepo characterRepo, MonsterRepo monsterRepo, Monsters monsters, CharSkillsRepo charSkills) {
        this.userRepo = userRepo;
        this.characterRepo = characterRepo;
        this.monsterRepo = monsterRepo;
        this.monsters = monsters;
        this.charSkills = charSkills;
    }

    @GetMapping("/sign-up") 
    public String signUp(Model model) {

        return "sign-up";
    }
    
    @GetMapping
    public String home(Model model) {
        model.addAttribute("game_name", "Adventure Mania!");
        return "landing-page";
    }
    @GetMapping("/sign-in")
    public String homePage(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("user", "Welcome to Adventure Mania! 2.3");
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // Check if user is logged in
        if (session.getAttribute("user") == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to access the dashboard.");
            return "redirect:/sign-in"; // Redirect to login page if not logged in
        }
       

        String username = (String) session.getAttribute("user");
        CharacterEnt character = characterRepo.findById(userRepo.findByUsername(username).get().getId()).orElse(null);
        model.addAttribute("user", username);
        model.addAttribute("characterDetails", new CharacterDetails(character.getId(), character.getHp(), character.getLevel(), character.getCurrent_exp(), character.getMax_exp(), character.getCharClass()));
        return "dashboard"; // Return the dashboard view
    }

    @GetMapping ("/monsters")
    public String MonsterList(Model model) {
        
        List<MonsterEnt> monsterList = monsters.monsterList();
        model.addAttribute("monsters",monsterList);
        return "monsters";
    }

    @GetMapping ("/monstersd")
    @ResponseBody 
    public CharacterEnt monsters(@RequestParam ("m") Long id) {
        CharacterEnt ch = characterRepo.findWithSkills(id).orElse(null);
        System.out.println(ch.getSkills());

        return ch;
    }

   @GetMapping("/character/skills")
    public String characterSkills(@RequestParam("id") Long id, Model model) {
    CharacterEnt ch = characterRepo.findWithSkills(id).orElse(null);

    model.addAttribute("characterClass", ch == null ? null : ch.getCharClass());
    model.addAttribute("skills", ch == null ? List.of() : ch.getSkills());
    return "charSkillsPage";   
    }

    @GetMapping("/hunt")
    public String Hunt(HttpSession session, Model model) {
        // Get a random monster
        MonsterEnt monster = null;
        Random rand = new Random();
        while (monster == null) {
            Long randNum = (long)rand.nextInt(52);
            MonsterEnt potentialMonster = monsterRepo.findById(randNum).orElse(null);
            if (potentialMonster != null) {
                monster = potentialMonster;
            }
        }
        // Get the character's details
        String username = String.valueOf(session.getAttribute("user"));
        UserEnt user = userRepo.findByUsername(username).orElse(null);

        model.addAttribute("user", user);        
        model.addAttribute("monster", monster);
        System.out.println(monster);
        return "hunt";
    }

    @GetMapping("/hunt/attack")
    public String attack(@RequestParam ("monster_id") Long monster_id, @RequestParam ("character_id") Long character_id, HttpSession session, Model model) {
        // get both entities
        MonsterEnt monster = monsterRepo.findById(monster_id).orElse(null);
        UserEnt user = userRepo.findByUsername(String.valueOf(session.getAttribute("user"))).orElse(null);
        CharacterEnt character = characterRepo.findById(user.getId()).orElse(null);
        // do a picker who wons (random)
        Random randBol = new Random();
        Boolean isWon = randBol.nextBoolean();
        if (isWon) {
            Integer monster_exp_drop = monster.getExpDrop();
            Integer character_current_exp = character.getCurrent_exp();
            Integer character_max_exp = character.getMax_exp();
            // Get chars earned exp add it to the current exp
           int totalExp = character_current_exp + monster_exp_drop;
            int maxExp = character_max_exp;

            // 2. Process level ups using a loop (handles multi-level jumps from boss monsters!)
            while (totalExp >= maxExp) {
                totalExp -= maxExp;                           // Deduct the EXP consumed by this level up
                character.setLevel(character.getLevel() + 1); // Increment level by 1
                
                // Optional: If you want the next level to require more EXP, update maxExp here
                // e.g., maxExp = (int) (maxExp * 1.15); 
            }
            character.setCurrent_exp(totalExp);
            characterRepo.save(character);
            model.addAttribute("characterDetails", character);
        }
        if (!isWon) {
            model.addAttribute("status", isWon);
        }
        return "attack-results";
    }

    // API's
    @GetMapping ("/skills-list")
    @ResponseBody 
    public List<CharSkills> skillsList() {
        List<CharSkills> skills = charSkills.findAllByTypeSkill(SkillType.Cleric);
        skills.forEach(skill -> System.out.println(skill));
        return skills;
    }
}
