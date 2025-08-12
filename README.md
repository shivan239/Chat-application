# 📡 Spring Boot WebSocket Chat Application

A real-time chat application built with **Spring Boot**, **WebSockets** (STOMP + SockJS), and a simple frontend.  
It supports multiple users chatting in public channels and is deployable on platforms like **Railway**.

---

## 🚀 Features

- Real-time messaging using WebSocket and STOMP
- SockJS fallback for browser compatibility
- Dynamic WebSocket endpoint resolution for HTTP/HTTPS
- Simple HTML/CSS/JavaScript frontend
- Easy deployment on Railway or other cloud platforms
- Ready for database integration for persistent chat history

---

## 🛠 Tech Stack

- **Backend:** Java 17, Spring Boot 3.x, Spring WebSocket, STOMP, SockJS
- **Frontend:** HTML, CSS, JavaScript
- **Build Tool:** Maven
- **Deployment:** Railway (or any JVM hosting)
- **Optional DB:** MySQL / PostgreSQL (future enhancement)

---

## 📂 Project Structure

springboot-websocket-chat/
├── src/main/java/com/chat/app/ # Java source code
│ ├── AppApplication.java # Spring Boot entry point
│ ├── config/ # WebSocket configuration
│ ├── controller/ # Message controllers
│ ├── model/ # Chat message models
│ └── ...
├── src/main/resources/
│ ├── static/ # Frontend (index.html, CSS, JS)
│ ├── application.properties # App config
├── pom.xml # Maven dependencies
└── README.md

---

## ⚙️ Configuration

### application.properties
```properties
spring.application.name=app
server.port=${PORT:8080}

Railway will inject the PORT environment variable automatically.

WebSocket Configuration
java
Copy
Edit
@Override
public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws").withSockJS();
}
💻 Running Locally
1️⃣ Clone the repository

bash
Copy
Edit
git clone https://github.com/your-repo.git
cd springboot-websocket-chat
2️⃣ Build the project

bash
Copy
Edit
mvn clean package
3️⃣ Run the JAR

bash
Copy
Edit
java -jar target/app-0.0.1-SNAPSHOT.jar
4️⃣ Access the app
Open http://localhost:8080 in your browser.

🌐 Deployment on Railway
Push your code to GitHub

Create a new Railway project → Link to your GitHub repo

Railway will detect Maven and build:

bash
Copy
Edit
mvn clean package
Railway will run:

bash
Copy
Edit
java -jar target/app-0.0.1-SNAPSHOT.jar
Set a Custom Domain if needed (no custom port, WebSocket works over 80/443)

Ensure your frontend JS dynamically resolves the socket URL:

javascript
Copy
Edit
var loc = window.location;
var socketUrl = loc.protocol === "https:" 
    ? `https://${loc.host}/ws` 
    : `http://${loc.host}/ws`;
var socket = new SockJS(socketUrl);
🗄 Adding a Database (Optional)
If you want persistent chat history:

Add a DB dependency in pom.xml:

xml
Copy
Edit
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
Configure DB in application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:postgresql://${{DB_HOST}}:${{DB_PORT}}/${{DB_NAME}}
spring.datasource.username=${{DB_USER}}
spring.datasource.password=${{DB_PASSWORD}}
spring.jpa.hibernate.ddl-auto=update
Create a ChatMessage entity and repository to store/retrieve messages.

🐛 Common Issues & Fixes
Frontend not updating after deploy
Cause: Old JAR cached by Railway

Fix:

bash
Copy
Edit
mvn clean package
git commit -am "Rebuild"
git push
This forces Railway to rebuild with your updated static/index.html.

WebSocket not connecting on custom domain
Use dynamic WebSocket URL in JS (see above)

Ensure Railway project is set to “Public” if needed

App works locally but fails on Railway
Check deploy logs for missing dependencies

Ensure server.port=${PORT:8080} is in application.properties

📜 License
MIT License – free to use, modify, and distribute.
