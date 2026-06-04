# Advanced Programming – Multi-Client Chat Server (Java)

Year 2 coursework building a multi-client chat server in Java using sockets, threads, and the Observer pattern.

## task1 – Chat Server

A TCP chat server where multiple clients can connect, send messages to the group, and receive a periodic ping.

**Architecture:**
- `Server.java` — accepts connections, spawns a thread per client
- `ClientHandler.java` — manages a single client's I/O, implements `Observer`
- `Group.java` — broadcasts messages to all connected clients, sends periodic pings
- `Observer.java` — interface for the Observer pattern
- `Message.java` — message wrapper
- `Client.java` / `MockClient.java` — client implementations for testing
- `GroupTest.java` — JUnit tests

**Run:**
```bash
javac task1/*.java
java -cp task1 Server
# in separate terminals:
java -cp task1 Client
```

## JavaExercises – Progressive exercises

Step-by-step exercises building up to the full server:

| File | Concept |
|------|---------|
| `GreetingProgram.java` | Basic Java |
| `Dog.java` / `DogList.java` | OOP, generics |
| `SimpleServer.java` / `SimpleClient.java` | Basic sockets |
| `MultiClientServer.java` | Multi-threaded server |
| `InteractiveClient.java` | Interactive client I/O |
| `ThreadExample.java` | Thread / Callable / Future |

## Tech

Java · Sockets · Multithreading · Observer pattern · JUnit
