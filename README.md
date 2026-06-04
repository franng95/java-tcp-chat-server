# Multi-Client TCP Chat Server

Java TCP chat server using sockets, threads, and the Observer pattern. Originally built as Year 2 coursework, this project demonstrates concurrent client handling and group message broadcasting.

## Chat Server

A TCP chat server where multiple clients can connect, send messages to the group, and receive a periodic ping.

**Architecture:**
- `Server.java` - accepts connections and spawns a thread per client
- `ClientHandler.java` - manages a single client's I/O and implements `Observer`
- `Group.java` - broadcasts messages to all connected clients and sends periodic pings
- `Observer.java` - interface for the Observer pattern
- `Message.java` - message wrapper
- `Client.java` / `MockClient.java` - client implementations for manual use and testing
- `GroupTest.java` - JUnit tests for group membership, coordinator handover, member removal, and dead-client handling

**Run:**
```bash
javac task1/*.java
java -cp task1 Server
# in separate terminals:
java -cp task1 Client
```

## JavaExercises

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
