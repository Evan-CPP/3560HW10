import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchMessagesByUserTest {

    @Test
    public void testIteratorFiltersCorrectly() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        ChatHistory history = new ChatHistory();

        history.addMessage(new Message(alice, Arrays.asList(bob), LocalDateTime.now(), "Hi Bob"));
        history.addMessage(new Message(alice, Arrays.asList(charlie), LocalDateTime.now(), "Hi Charlie"));
        history.addMessage(new Message(bob, Arrays.asList(alice), LocalDateTime.now(), "Hi Alice"));

        Iterator<Message> bobIterator = history.iterator(bob);

        assertTrue(bobIterator.hasNext());
        assertEquals("Hi Bob", bobIterator.next().getContent());

        assertTrue(bobIterator.hasNext());
        assertEquals("Hi Alice", bobIterator.next().getContent());

        assertFalse(bobIterator.hasNext());
    }
}