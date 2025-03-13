# **GitHub Integration Service**

This project provides a service to get user details and repository information from
GitHub through RESTful endpoints. It integrates with GitHub's API and handles user data 
retrieval along with caching and error handling.

### **Key features:**

    1. RESTful API to retrieve user details and their list of repositories.
    2. Caching to reduce the number of requests to GitHub's API.
    3. Error Handling to provide meaningful error message when an invalid username is provided.
    4. Integration Tests to ensure that the service behaves as expected.

### **How to install?**

#### _You will need:_

    Java JDK 17
    Maven 3.9.9
    Git
You can clone the repository using

    git clone https://github.com/alekhyachennupati/githubintegrationserv.git
Navigate to project directory

    cd githubintegrationserv
Build the project using maven

    mvn clean install

Run the application

    mvn spring-boot:run

### Endpoints

    Get User Details
    Endpoint: /api/v1/user/{userName}
    Method: GET
    Description: Retrieve GitHub user and repository details by username.

#### Example Request:

    GET http://localhost:8080/api/v1/user/octocat

#### Example Response
```json
{
  "userName": "octocat",
  "displayName": "The Octocat",
  "avatar": "https://avatars.githubusercontent.com/u/583231?v=4",
  "geoLocation": "San Francisco",
  "email": null,
  "url": "https://github.com/octocat",
  "createdAt": "2011-01-25T18:44:36Z",
  "repos": [
    {
      "name": "boysenberry-repo-1",
      "url": "https://github.com/octocat/boysenberry-repo-1"
    },
    {
      "name": "git-consortium",
      "url": "https://github.com/octocat/git-consortium"
    },
    {
      "name": "hello-worId",
      "url": "https://github.com/octocat/hello-worId"
    },
    {
      "name": "Hello-World",
      "url": "https://github.com/octocat/Hello-World"
    },
    {
      "name": "linguist",
      "url": "https://github.com/octocat/linguist"
    },
    {
      "name": "octocat.github.io",
      "url": "https://github.com/octocat/octocat.github.io"
    },
    {
      "name": "Spoon-Knife",
      "url": "https://github.com/octocat/Spoon-Knife"
    },
    {
      "name": "test-repo1",
      "url": "https://github.com/octocat/test-repo1"
    }
  ]
}
```
### Testing

    The project includes integration tests to ensure proper functioning of the service.
    It includes
    1. Happy Path scenario
    2. Invalid github name
    3. Valid name but user name not present in github

### Areas of improvement / Backlog
    
    1. User authentication
    2. Using a customized cache provider with eviction policies
    3. Better error handling and retries
    4. Pagination of repos using a query parameter
    5. Enhanced logging
These are some of the things this code could improve up on. 
