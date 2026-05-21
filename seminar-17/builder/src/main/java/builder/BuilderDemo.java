package builder;

public class BuilderDemo {
    public static void run() {
        System.out.println("=== Builder ===");

        RichNotification minimal = new RichNotification.Builder("eve@example.com", "Hi")
                .build();
        System.out.println("minimal : " + minimal);

        RichNotification full = new RichNotification.Builder("frank@example.com", "Report")
                .body("<h1>Q4 Report</h1>")
                .replyTo("noreply@example.com")
                .priority(3)
                .htmlBody(true)
                .attachment("/reports/q4.pdf")
                .build();
        System.out.println("full    : " + full);
    }
}
