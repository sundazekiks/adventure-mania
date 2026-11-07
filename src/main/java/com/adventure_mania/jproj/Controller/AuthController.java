package com.adventure_mania.jproj.Controller;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adventure_mania.jproj.Entities.CharSkills;
import com.adventure_mania.jproj.Entities.CharacterEnt;
import com.adventure_mania.jproj.Entities.SkillType;
import com.adventure_mania.jproj.Entities.UserEnt;
import com.adventure_mania.jproj.Records.UserLogin;
import com.adventure_mania.jproj.Records.UserSignUp;
import com.adventure_mania.jproj.Repository.CharSkillsRepo;
import com.adventure_mania.jproj.Repository.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller 
@RequestMapping ("/auth")
public class AuthController {
    private final UserRepo userRepo;
    private final CharSkillsRepo charSkillsRepo;

    public AuthController(UserRepo userRepo, CharSkillsRepo charSkillsRepo) {
        this.userRepo = userRepo;   
        this.charSkillsRepo = charSkillsRepo;
    }

    @PostMapping ("/login")
    public String login(@ModelAttribute UserLogin userLogin, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        if (userRepo.findByUsername(userLogin.username()).isPresent()) {
            System.out.println("User found: " + userLogin.username());
        } else {
            System.out.println("User not found: " + userLogin.username());
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to access the dashboard.");
            return "redirect:/sign-in"; // Redirect to login page if not logged in
        }
        session.setAttribute("user", userLogin.username());
        // Handle login logic here
        return "redirect:/dashboard"; // Redirect to home page after successful login   
    }

    @PostMapping ("/signup")
    public String login(@ModelAttribute  UserSignUp userDetails, HttpSession session, Model model, RedirectAttributes redirectAttributes){
            if (userRepo.findByUsername(userDetails.username()).isPresent()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Username already taken");
                return "redirect:/sign-up";
            }
            SkillType s_type = switch (userDetails.characterClass()) {
                case "Warrior" -> SkillType.Warrior;
                case "Cleric"  -> SkillType.Cleric;
                case "Mage"    -> SkillType.Mage;
                case "Rogue"   -> SkillType.Rogue;
                default        -> throw new IllegalArgumentException("Unknown character class: " + userDetails.characterClass());
            };
            List<CharSkills> skills = charSkillsRepo.findAllByTypeSkill(s_type);
            Random rand = new Random();
            CharSkills skill_one = skills.get(rand.nextInt(skills.size()));            // Create user and character
            CharSkills skill_two = skills.get(rand.nextInt(skills.size()));    
            UserEnt user = new UserEnt();
            user.setUsername(userDetails.username());
            user.setPassword(userDetails.password());
            CharacterEnt c = new CharacterEnt();

            c.setCharClass(userDetails.characterClass());
            c.addSkill(skill_one);
            c.addSkill(skill_two);
            user.setCharacter(c);
            userRepo.save(user);
            
            session.setAttribute("user", user.getUsername());

            return "redirect:/dashboard";
    }

    
    @GetMapping ("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // Invalidate the session to log out the user
        redirectAttributes.addFlashAttribute("message", "You have been logged out successfully.");
        return "redirect:/sign-in"; // Redirect to login page after logout
    }
}
