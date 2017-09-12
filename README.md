# collectService

## Prerequisites
-Create a folder named *.collectService* with a profile specific
application properties in your home directory, i.e.:
```
cd
mkdir .collectService
touch application-dev.properties
```
You have to specify the following properties for your keystore inside that file:
```
server.ssl.key-alias=the alias
server.ssl.key-password=the password
server.ssl.key-store=the path to your keystore
```

## Execute the service
-Run the service with a specific profile (dev, prod), i.e.:
```
mvn spring-boot:run -Dspring.profiles.active=dev
```