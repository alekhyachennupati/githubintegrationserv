# **GitHub Activity Project**

This project provides a snapshot of a Github user's activity using SpringBoot.
An endpoint is provided that takes a username and returns a subset of the respective GitHub user's
data in JSON format. A sample of JSON is provided below,

```json
{
    "user_name": "octocat",
    "display_name": "The Octocat",
    "avatar": "https://avatars3.githubusercontent.com/u/583231?v=4", 
    "geo_location": "San Francisco",
    "email": null,
    "url": "https://github.com/octocat ",
    "created_at": "2011-01-25 18:44:36",
    "repos": [{
         "name": "boysenberry-repo-1",
         "url": "https://github.com/octocat/boysenberry-repo-1"
        },
      {
        
      }
    ]
}
```

## _Dependencies_

Browse the Maven pom.xml file for details of libraries and versions used.

## _Building the project_

You will need:

    Java JDK 17
    Maven 3.9.9
    Git

Clone the project and use Maven to build the server

**$ mvn clean install**

## _To run the app_

**$ mvn spring-boot:run**

The app will start running at http://localhost:8080.

## _Explore Rest APIs_

The app defines following end point.

**GET /api/v1/user/{userName}**


