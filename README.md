# LoginUserManagement

## Description

This project demonstrates a basic setup for user roles and access control using a Spring Boot application with a MySQL database. It includes SQL statements to set up user roles and instructions to register users with different roles using API endpoints.

## Setup

1. **Database Configuration:**
   - Make sure you have a MySQL database running.
   - Execute the following SQL insert statements to set up user roles:
     ```sql
     INSERT INTO roles(name) VALUES('ROLE_USER');
     INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
     INSERT INTO roles(name) VALUES('ROLE_ADMIN');
     ```

2. **API Usage:**
   - Register users with the desired roles using the `/signup` API:
     - Admin with ROLE_ADMIN
       ```json
       POST /signup
       {
         "username": "admin",
         "password": "adminpassword",
         "email" : "admin@login.com",
         "roles": ["ROLE_ADMIN"]
       }
       ```
       **Response:**
       ```json
       {
         "message": "User registered successfully!"
       }
       ```

     - Moderator with ROLE_MODERATOR and ROLE_USER
       ```json
       POST /signup
       {
         "username": "moderator",
         "password": "moderatorpassword",
         "email" : "moderator@login.com",
         "roles": ["ROLE_MODERATOR", "ROLE_USER"]
       }
       ```
       **Response:**
       ```json
       {
         "message": "User registered successfully!"
       }
       ```

     - User with ROLE_USER
       ```json
       POST /signup
       {
         "username": "user",
         "password": "userpassword",
         "email" : "user@login.com",
         "roles": ["ROLE_USER"]
       }
       ```
       **Response:**
       ```json
       {
         "message": "User registered successfully!"
       }
       ```

3. **Resource Access:**
   - Access public resource:
     ```
     GET /api/test/all
     ```
     **Response:**
     ```json
     {
        "message": "Public Content"
     }
     ```

   - Access protected resources based on roles:
     - User resource (requires ROLE_USER):
       ```
       GET /api/test/user
       ```
       **Response:**
       ```json
       {
         "timestamp": "2024-01-21T07:00:01.792+00:00",
         "status": 401,
         "error": "Unauthorized",
         "message": "Error: Unauthorized",
         "path": "/api/test/user"
       }
       ```

     - Moderator resource (requires ROLE_MODERATOR):
       ```
       GET /api/test/mod
       ```
       **Response:**
       ```json
       {
         "timestamp": "2024-01-21T07:01:05.357+00:00",
         "status": 401,
         "error": "Unauthorized",
         "message": "Error: Unauthorized",
         "path": "/api/test/mod"
       }
       ```

     - Admin resource (requires ROLE_ADMIN):
       ```
       GET /api/test/admin
       ```
       **Response:**
       ```json
       {
         "timestamp": "2024-01-21T07:14:44.742+00:00",
         "status": 401,
         "error": "Unauthorized",
         "message": "Error: Unauthorized",
         "path": "/api/test/admin"
       }
       ```

4. **Authentication:**
   - To access protected resources, first, obtain a JWT token by logging in:
     ```json
     POST /api/auth/signin
     {
       "username": "your_username",
       "password": "your_password"
     }
     ```
     **Response:**
     ```json
     {
       "accessToken": "your_access_token",
       "tokenType": "Bearer"
     }
     ```
     Include the obtained token in the Authorization header for subsequent requests.

   - Access protected resources based on roles with token code:
     - User resource (requires ROLE_USER):
       ```json
       POST /api/auth/signin
       {
         "username": "user",
         "password": "userpassword"
       }
       ```
       **Response:**
       ```json
       {
         "email": "user@login.com",
         "id": 2,
         "roles": [
             "ROLE_USER"
         ],
         "username": "user",
         "tokenType": "Bearer",
         "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzA1ODIxODEyLCJleHAiOjE3MDU5MDgyMTJ9.IZ-oLzIA_8B-p7Yi_WcfLJ6BZTaml0Bu_5diVZHHWAQ"
       }
       ```
       ```
       GET /api/test/user
       ```
       Curl:
       ```bash
       curl --location 'http://localhost:8080/api/test/user' \
       --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzA1ODIxODEyLCJleHAiOjE3MDU5MDgyMTJ9.IZ-oLzIA_8B-p7Yi_WcfLJ6BZTaml0Bu_5diVZHHWAQ' \
       --data ''
       ```
       **Response:**
       ```json
       {
         "message": "User Content"
       }
       ```

     - Moderator resource (requires ROLE_MODERATOR):
       ```json
       POST /api/auth/signin
       {
         "username": "moderator",
         "password": "moderatorpassword"
       }
       ```
       **Response:**
       ```json
       {
         "email": "moderator@login.com",
         "id": 1,
         "roles": [
             "ROLE_MODERATOR",
             "ROLE_USER"
         ],
         "username": "moderator",
         "tokenType": "Bearer",
         "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb2RlcmF0b3IiLCJpYXQiOjE3MDU4MjIzNjQsImV4cCI6MTcwNTkwODc2NH0.EPGClvay6SsNTbN0MFBlUUTiXYMFvd5u5kIyd8udVmQ"
       }
       ```
       ```
       GET /api/test/mod
       ```
       Curl:
       ```bash
       curl --location 'http://localhost:8080/api/test/mod' \
       --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtb2RlcmF0b3IiLCJpYXQiOjE3MDU4MjIzNjQsImV4cCI6MTcwNTkwODc2NH0.EPGClvay6SsNTbN0MFBlUUTiXYMFvd5u5kIyd8udVmQ' \
       --data ''
       ```
       **Response:**
       ```json
       {
         "message": "Moderator Content"
       }
       ```

     - Admin resource (requires ROLE_ADMIN):
       ```json
       POST /api/auth/signin
       {
         "username": "admin",
         "password": "adminpassword"
       }
       ```
       **Response:**
       ```json
       {
         "email": "admin@login.com",
         "id": 3,
         "roles": [
             "ROLE_ADMIN"
         ],
         "username": "admin",
         "tokenType": "Bearer",
         "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwNTgyMjUxMywiZXhwIjoxNzA1OTA4OTEzfQ.UnvvN91pVQPVixivgq6IQu9QMnjCKYFF-eJ8-IKWvX8"
       }
       ```
       ```
       GET /api/test/admin
       ```
       Curl:
       ```bash
       curl --location 'http://localhost:8080/api/test/admin' \
       --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwNTgyMjUxMywiZXhwIjoxNzA1OTA4OTEzfQ.UnvvN91pVQPVixivgq6IQu9QMnjCKYFF-eJ8-IKWvX8' \
       --data ''
       ```
       **Response:**
       ```json
       {
         "message": "Admin Content"
       }
       ```

## Dependencies

- Spring Boot
- Spring Security
- MySQL
- JWT (JSON Web Tokens)

## Contributors

- Aman Singh (amansingh25170600@gmail.com)
