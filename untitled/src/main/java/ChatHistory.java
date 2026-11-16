import java.util.ArrayList;
import java.util.List;

public class ChatHistory {
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
}