import java.time.LocalDateTime;
import java.util.List;

public class Message {
    private final User sender;
    private final List<User> recipients;
    private final LocalDateTime timestamp;
    private final String content;

    public Message(User sender, List<User> recipients, LocalDateTime timestamp, String content) {
        this.sender = sender;
        this.recipients = recipients;
        this.timestamp = timestamp;
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }
}