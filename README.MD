# Education system

### Prerequisites

 - download and install [Docker desktop](https://www.docker.com/get-started)

### Start PostgresSQL via docker desktop

 - Start postgresSQL server

Example:

```
docker run --name postgresql-container -p 5432:5432 -e POSTGRES_PASSWORD=123456 -d postgres
```
- [Optional] Start pgAdmin
 
Example:
```
docker run --rm -p 5050:5050 thajeztah/pgadmin4
```

- Access to pgAdmin from the browser http://localhost:5050 (The port is that specified with -p flag in docker run command)


- [Optional] You can check your databases via IntellijIDEA

### Create pgAdmin server in UI

#### Steps:

- Right click on Servers.
- Create.
- For hostName specify your local IP address.
- Port is that specified in with -p flag of docker run.
- Password is that specified with POSTGRES_PASSWORD env of docker run.


# Api endpoints

## Get

#### Students

##Get
####Students
- `/students` - get all students

- `/students/{id}` - get student by id
   
- `/students/findByComponent` - finds student by component

- `/students/results/average` - gets the average of all students results
 
- `/students/results/centralTendention` - get the central tendention of all students

- `/students/summary` - get summary info about the students activities and their results

- `/students/results/dispersion` - get dispersion of students results
  
- `/students/results/scope` - get scope of students results


###Example

Request body:
```
{
"eventName": "Wiki page updated",
"results": [3, 4]
}
```

**Prams**

|          Name | Required |  Type   | Description                       |
| -------------:|:--------:|:-------:| -----------------------------------
|      `id`     | required | Path variable(string)  | The id of the student
|     `component`| required | Query param(string)  | The component name of the student log|
|     `eventName`| required | Query param(string)  | The eventName name of the student log |

#### Logs
- `/logs` - get all logs

- `/logs/{id}` - get log by id

**Query Params**

|          Name | Required |  Type   | Description                       |
| -------------:|:--------:|:-------:| -----------------------------------
|      `id`     | required |  Path variable(string)  | The id of the log

#### Courses

- `/courses` - get all courses

- `/courses/{id}` - get course by id


**Query Params**

|          Name | Required |  Type   | Description                       |
| -------------:|:--------:|:-------:| -----------------------------------
|      `id`     | required |  Path variable(string) string  | The id of the course

## Post

#### Students

- `/students/seed` - add students from XLSX file

#### Logs 

- `/logs/seed` - add logs from XLSX file

