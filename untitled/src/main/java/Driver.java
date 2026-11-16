import java.util.Arrays;
import java.util.Iterator;

public class Driver {
    public static void main(String[] args) {
        ChatServer mediator = new ChatServer();

        User alice = new User("Alice", mediator);
        User bob = new User("Bob", mediator);
        User charlie = new User("Charlie", mediator);

        mediator.registerUser(alice);
        mediator.registerUser(bob);
        mediator.registerUser(charlie);

        System.out.println("\n---Test chat:---");

        alice.sendMessage(Arrays.asList(bob, charlie), "Oi");
        bob.sendMessage(Arrays.asList(alice), "Oi wassup");
        charlie.sendMessage(Arrays.asList(alice), "Watch me unsend this message");
        charlie.undoLastMessage();
        bob.sendMessage(Arrays.asList(charlie), "I'm gonna block you");
        bob.blockUser(charlie);
        charlie.sendMessage(Arrays.asList(bob), "Bet let's see if you get this");
        alice.sendMessage(Arrays.asList(bob), "Why did you block Charlie bro.");

        System.out.println("\n---Searching Bob's history for messages with Alice---");
        Iterator<Message> bobAliceIterator = bob.iterator(alice);

        while (bobAliceIterator.hasNext()) {
            Message msg = bobAliceIterator.next();
            System.out.println("[" + msg.getTimestamp() + "] " + msg.getSender().getName() + ": " + msg.getContent());
        }
    }
}