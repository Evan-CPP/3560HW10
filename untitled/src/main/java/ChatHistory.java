import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ChatHistory implements IterableByUser {
    private final List<Message> messages;

    public ChatHistory() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Message getLastMessageSent() {
        if (messages.isEmpty()) {
            return null;
        }
        return messages.remove(messages.size() - 1);
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new SearchMessagesByUser(this.messages, userToSearchWith);
    }
}