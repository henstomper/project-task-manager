## How to Initialize Project Manager:

Starting from the root project-manager directory, enter the java directory

First, start the backend:
Run ```./mvnw.cmd install```
Run ```java -jar target\projectmanager-0.0.1-SNAPSHOT.jar```

This starts the backend, but it has no data.

Run ```curl "http://localhost:8080/populate"``` to populate the database with sample data

Open a new command line at the root project-manager directory

Next, start the frontend:
Run ```npm install```
Run ```npm run dev```


After this, you should be able to open the Project Manager interface in your browser with the URL ```http://localhost:5173/```
