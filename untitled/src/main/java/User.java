import java.time.LocalDateTime;
import java.util.List;

public class User {
    private final String name;
    private final ChatServer mediator;
    private final ChatHistory history;

    public User(String name, ChatServer mediator) {
        this.name = name;
        this.mediator = mediator;
        this.history = new ChatHistory();
    }

    public String getName() {
        return name;
    }

    public ChatHistory getHistory() {
        return history;
    }

    public void sendMessage(List<User> recipients, String content) {
        Message message = new Message(this, recipients, LocalDateTime.now(), content);

        history.addMessage(message);

        System.out.println(this.name + " is sending: '" + content + "'");
        mediator.sendMessage(message);
    }

    public void receiveMessage(Message message) {
        history.addMessage(message);
        System.out.println(this.name + " received message from " + message.getSender().getName() + ": '" + message.getContent() + "'");
    }

    public void undoLastMessage() {
        Message lastMessage = history.getLastMessageSent();

        if (lastMessage != null) {
            MessageMemento memento = new MessageMemento(lastMessage.getContent(), lastMessage.getTimestamp());
            System.out.println(this.name + " undid message: '" + memento.getContent() + "']'");
        } else {
            System.out.println(this.name + " has no messages to undo.");
        }
    }

    public void blockUser(User userToBlock) {
        System.out.println(this.name + " blocked " + userToBlock.getName());
        mediator.blockUser(this, userToBlock);
    }
}