# Overview

{Important! Do not say in this section that this is college assignment. Talk about what you are trying to accomplish as a software engineer to further your learning.}

Adventure Mania is a web-based adventure game backend and front end that I built in Java using Spring Boot. Players register an account and log in, and each account is automatically given one game character. A character has stats (HP, level, current and maximum experience points), a character class, and a set of skills. The data is stored in a PostgreSQL database and accessed through Spring Data JPA and Hibernate.

The project demonstrates several core Java concepts:

    - Object-oriented design: Entity classes such as UserEnt, CharacterEnt, and CharSkills use encapsulation (private fields with getters and setters) and model real relationships between objects.
    - Relationships between objects: A one-to-one relationship links an account to its character, and a many-to-many relationship links characters to skills.
    - Frameworks and annotations: Spring Boot handles configuration and the embedded web server. Annotations such as @Entity, @OneToOne, @ManyToMany, and @Transactional map classes to database tables and manage transactions.
    - The MVC pattern: Controllers handle form submissions, repository interfaces handle data access, and a service layer holds the logic for creating a user together with their character in a single transaction.
    - Collections and generics: Lists of skills and repository interfaces with generic types, such as JpaRepository<CharacterEnt, Integer>.
    - Database access: Repository interfaces extend JpaRepository, and derived query methods like findByAccountId are translated into SQL automatically.

{Describe your purpose for writing this software.}

{Provide a link to your YouTube demonstration. It should be a 4-5 minute demo of the software running and a walkthrough of the code. Focus should be on sharing what you learned about the language syntax.}

[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

Tools used:
    - Java Virtual Machine
    - Spring Boot Framework
    - Postgresql
    - Docker

    I used Java mainly as the programming language and with the help of Spring Boot I can start up a standalone program with a server embedded so I can run a Web Service. Also I JPA library main for communicating with my sql database. I ran my development postgres db in a container with docker.

# Useful Websites

    - https://spring.io/guides
    - https://docs.oracle.com/javase/8/docs/api/


# Future Work

{Make a list of things that you need to fix, improve, and add in the future.}

- I need to improve the UI when players are hunting
- Need to have scoreboards for everyone to see
- Add world chat for everyone to communicate

