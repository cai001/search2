## Name project
### <b>SIMPLE-SEARCH-ROBOT: search-2</b>
## About author
Name: Semyon, Surname: Pakulko, Skill: pre-junior developer, e-mail: pacoolkos@mail.ru
## Description
This project is a simple search robot. It pulls URL-addresses from MySQL-server.
The date of the status check for these addresses is earlier than a date, set by the user before running a program.
Further, the robot requests the status of each pulled link using the multithread method.
In the end, the database is updated according to the requested status with a current date.
## Requirements
To build and run the robot correctly, it is necessary to install the next applications:
- jdk 8 (or higher)
- jre 8 (or higher)
- apache-maven-3.X.X
- mysql-server
## Prepare
You need to change MySQL connection parameters to your `-url`, `-login`, `-password` and `-table` in the files:
```
~/search2/src/main/java/SQLManager.java

~/search2/src/test/java/SQLManagerTest.java
```
Also, you can change URL threads pool size `number` (default size equals 700) in the files:
```
~/search2/src/main/java/URLManager.java

~/search2/src/test/java/URLManagerTest.java
```
## Installation

You have to start command line or terminal
and change the current directory to the project root directory, for example:
```cmd
>cd /your/clone/directory/search2
```
in the next step, start building the robot:
```cmd
>mvn install
```
## Runnig

The robot can be run with the following command from the project root directory:
```cmd
>java -jar search-2 20181212
```
You should set a date for the selection of rows with later dates.

## Result

The robot returns the standard link statuses.

The following user statuses are applied when receiving exceptions:

- `600` – Invalid link;

- `601` – Invalid link construction;

- `602` – Network overload;

- `603` – Other exception.

## Packaging to the Docker

### WARNING: Next you need the -Docker

After installation, run the command from the project root directory <b>(the dot is needed)</b>:
```cmd
>docker build -t search-2 .
```

The Docker creates an image of the robot.

But also, you can to pull this bot by Docker:
```cmd
>docker pull cai001/search-2
```
