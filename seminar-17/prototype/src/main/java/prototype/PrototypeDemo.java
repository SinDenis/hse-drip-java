package prototype;

public class PrototypeDemo {
    public static void run() {
        System.out.println("=== Prototype ===");

        NotificationTemplate welcome = new NotificationTemplate(
                "template", "Welcome to the platform!", "Dear user, ...", "onboarding", "welcome");
        System.out.println("original  : " + welcome);

        NotificationTemplate forAlice = welcome.withRecipient("alice@example.com")
                                               .withBody("Dear Alice, welcome!");
        NotificationTemplate forBob   = welcome.withRecipient("bob@example.com")
                                               .withBody("Dear Bob, welcome!");

        System.out.println("for alice : " + forAlice);
        System.out.println("for bob   : " + forBob);
        System.out.println("original unchanged: " + welcome);
    }
}
