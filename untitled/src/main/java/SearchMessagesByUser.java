import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchMessagesByUser implements Iterator<Message> {
    private final List<Message> messages;
    private final User targetUser;
    private int currentIndex = 0;
    private Message nextMessage = null;

    public SearchMessagesByUser(List<Message> messages, User targetUser) {
        this.messages = messages;
        this.targetUser = targetUser;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < messages.size()) {
            Message msg = messages.get(currentIndex);
            if (msg.getSender().equals(targetUser) || msg.getRecipients().contains(targetUser)) {
                nextMessage = msg;
                return true;
            }
            currentIndex++;
        }
        nextMessage = null;
        return false;
    }

    @Override
    public Message next() {
        if (!hasNext() && nextMessage == null) {
            throw new NoSuchElementException();
        }
        Message msgToReturn = nextMessage;
        currentIndex++;
        nextMessage = null;
        return msgToReturn;
    }
}