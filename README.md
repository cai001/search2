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
You need to change MySQL connection parameters to your `-url`, `-login`, `-password` and `-table` in the file:

>~/search2/src/main/resources/data.txt

Also into this file, you can change URL threads pool size `threadslimit` and searching parametr `date`.

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

The robot can be run with the following commands from the project root directory:
```cmd
>cd target
>java -jar search
```

## Result

The robot returns the standard link statuses.

The following irregular statuses are applied when receiving exceptions:

- `600` – Invalid link;

- `601` – Invalid link construction;

- `602` – Network overload;

- `603` – Other exception.

## Packaging to the Docker

### WARNING: Next you need the -Docker

After installation, run the command from the project root directory <b>(the dot is needed)</b>:
```cmd
>docker build -t anyname .
```

The Docker creates an image of the robot.

But also, you can to pull the image of this bot using Docker:
```cmd
>docker pull cai001/search-2
```
