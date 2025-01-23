# Endpoint-Challenge

### Requirements:
Any `OpenJDK 18` distribution as that's what it's been tested on. The latest version of `Maven` if you plan on fiddling with the source code and building the jar.

### How to run?
Navigate to the projects base directory and run:
```
java -jar target/DirectoryManager-1.0-SNAPSHOT.jar
```

### How to make modifications and build your own jar?
After making any changes run the below to create a new jar:
```
mvn clean package
```
All changes to the directory structure will be handled in the `DirectoryManagerApplicaton` class. 

### Suggestions/Improvements?
Please open a pull request if you have any suggestions/improvements.
