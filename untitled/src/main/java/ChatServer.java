import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatServer {
    private final Set<User> registeredUsers;
    private final Map<User, Set<User>> blockedUsers;

    public ChatServer() {
        this.registeredUsers = new HashSet<>();
        this.blockedUsers = new HashMap<>();
    }

    public void registerUser(User user) {
        registeredUsers.add(user);
        blockedUsers.putIfAbsent(user, new HashSet<>());
    }

    public void unregisterUser(User user) {
        registeredUsers.remove(user);
        blockedUsers.remove(user);
    }

    public void blockUser(User blocker, User userToBlock) {
        if (blockedUsers.containsKey(blocker)) {
            blockedUsers.get(blocker).add(userToBlock);
        }
    }

    public void sendMessage(Message message) {
        User sender = message.getSender();

        if (!registeredUsers.contains(sender)) {
            System.out.println("Sender is not registered.");
            return;
        }

        for (User recipient : message.getRecipients()) {
            Set<User> recipientBlockList = blockedUsers.get(recipient);

            if (!recipientBlockList.contains(sender)) {
                recipient.receiveMessage(message);
            } else {
                System.out.println("Message blocked by recipient.");
            }
        }
    }
}