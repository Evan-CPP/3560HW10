import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ChatHistoryTest {

    @Test
    public void testGetLastMessageSent() {
        ChatHistory history = new ChatHistory();
        ChatServer server = new ChatServer();
        User testUser = new User("Alice", server);

        Message msg1 = new Message(testUser, null, LocalDateTime.now(), "First Message");
        Message msg2 = new Message(testUser, null, LocalDateTime.now(), "Second Message");

        history.addMessage(msg1);
        history.addMessage(msg2);

        assertEquals(msg2, history.getLastMessageSent());
        assertEquals(msg1, history.getLastMessageSent());
        assertNull(history.getLastMessageSent());
    }
}