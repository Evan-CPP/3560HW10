import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ChatHistoryTest {

    @Test
    public void testGetLastMessageSent() {
        ChatHistory history = new ChatHistory();
        Message msg1 = new Message(new User(), null, LocalDateTime.now(), "First Message");
        Message msg2 = new Message(new User(), null, LocalDateTime.now(), "Second Message");

        history.addMessage(msg1);
        history.addMessage(msg2);

        assertEquals(msg2, history.getLastMessageSent());
        assertEquals(msg1, history.getLastMessageSent());
        assertNull(history.getLastMessageSent());
    }
}