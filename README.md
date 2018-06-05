# social-networking-kata

This maven project is a possible (Spring Boot based) solution based of the exercise available at https://github.com/sandromancuso/social_networking_kata.

# Summary of the technologies used

Maven, Java, Spring Boot, Spring MVC, JPA, Hibernate, JUnit, Mockito, etc.

# How to use

This is a Spring Boot command line software, main commands are the following:

- saving (creating a user): <user name> save
- posting: <user name> -> <message>
- reading: <user name>
- following: <user name> follows <another user>
- wall: <user name> wall
- help (to read again these instructions): welcome
- exit: exit
  
Posting, reading, following and wall are the ones described at https://github.com/sandromancuso/social_networking_kata.

In the following a very brief description of the other ones:

- 'Saving' is a command for creating a new user
- 'help' (remember to digit 'welcome', not 'help') allows the user to have a look again on the menu above with all the needed instructions
- 'exit' does not need further explanations :D

#How to install

Requirements for compiling it:

- JDK 8 installed in the system with JAVA_HOME set, and it added to the PATH system variable
- Maven 3 installed in the system and added to the PATH system variable

In order to compile this software, starting from the root folder, digit:

mvn package

Then, to execute it, digit:

java -jar target/social-networking-kata-1.0.jar
