import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void testSendMessage() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        alice.sendMessage(Arrays.asList(bob), "Oi");

        assertEquals("Oi", alice.getHistory().getMessages().get(0).getContent());
    }

    @Test
    public void testReceiveMessage() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        Message incomingMsg = new Message(alice, Arrays.asList(bob), LocalDateTime.now(), "Oi");

        bob.receiveMessage(incomingMsg);

        assertEquals("Oi", bob.getHistory().getMessages().get(0).getContent());
    }

    @Test
    public void testUndoLastMessage() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        alice.sendMessage(Arrays.asList(bob), "Oi 1");
        alice.sendMessage(Arrays.asList(bob), "Oi 2");

        assertEquals(2, alice.getHistory().getMessages().size());

        alice.undoLastMessage();

        assertEquals("Oi 1", alice.getHistory().getMessages().get(0).getContent());
    }

    @Test
    public void testBlockUser() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        alice.blockUser(bob);

        bob.sendMessage(Arrays.asList(alice), "Oi");

        assertTrue(alice.getHistory().getMessages().isEmpty());
    }
}