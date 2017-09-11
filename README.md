# webodidecrypt

Online tool for Oracle Data integrator 10g and 11g password string decryption. Allows to retrive lost ODI passwords located commonly on the XML connection declaration or in the ODI database

## Build
Previous to compile webodidecrypt the dependency library odidecrypt must be installed in the local repository

	$ mvn clean package

## Run
WebOdiDecrypt was created with Spring Boot, it can executed with the maven spring-boot target or executing the 

	$ mvn spring-boot:run