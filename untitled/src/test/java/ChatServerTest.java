import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChatServerTest {

    @Test
    public void testSendMessage() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        alice.sendMessage(Arrays.asList(bob), "Oi");

        assertEquals("Oi", bob.getHistory().getMessages().get(0).getContent());
    }

    @Test
    public void testSendMessageWhenBlocked() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        bob.blockUser(alice);

        alice.sendMessage(Arrays.asList(bob), "Oi");

        assertTrue(bob.getHistory().getMessages().isEmpty());
    }
}