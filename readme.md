# Overview

Adventure Mania is a web-based adventure game backend and front end that I built in Java using Spring Boot. Players register an account and log in, and each account is automatically given one game character. A character has stats (HP, level, current and maximum experience points), a character class, and a set of skills. The data is stored in a PostgreSQL database and accessed through Spring Data JPA and Hibernate.

The project demonstrates several core Java concepts:

    - Object-oriented design: Entity classes such as UserEnt, CharacterEnt, and CharSkills use encapsulation (private fields with getters and setters) and model real relationships between objects.
    - Relationships between objects: A one-to-one relationship links an account to its character, and a many-to-many relationship links characters to skills.
    - Frameworks and annotations: Spring Boot handles configuration and the embedded web server. Annotations such as @Entity, @OneToOne, @ManyToMany, and @Transactional map classes to database tables and manage transactions.
    - The MVC pattern: Controllers handle form submissions, repository interfaces handle data access, and a service layer holds the logic for creating a user together with their character in a single transaction.
    - Collections and generics: Lists of skills and repository interfaces with generic types, such as JpaRepository<CharacterEnt, Integer>.
    - Database access: Repository interfaces extend JpaRepository, and derived query methods like findByAccountId are translated into SQL automatically.

I wrote this software to practice building a complete, realistic Java application instead of isolated exercises. I wanted to learn how the pieces of a modern Java application fit together: user input in a web form, validation, business logic, and persistent storage in a relational database. A game was a good fit because accounts, characters, stats, and skills are easy to model as objects with clear relationships, which let me work with object-oriented design and database mapping.

I also wanted experience with problems that come up in real projects. I handled null and default values on database inserts, kept an account and its character consistent by saving them in one transaction, and kept sensitive data like passwords out of logs and toString() output.


[Software Demo Video] https://youtu.be/5MayxTGHxv0

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

