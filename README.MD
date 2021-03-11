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

- Access to pgAdmin from the browser http://localhost:5050(The port is that specified with -p flag in docker run command)


- [Optional] You can check your databases via IntellijIDEA

### Create pgAdmin server in UI

#### Steps:

- Right click on Servers.
- Create.
- For hostName specify your local IP address.
- Port is that specified in with -p flag of docker run.
- Password is that specified with POSTGRES_PASSWORD env of docker run.
