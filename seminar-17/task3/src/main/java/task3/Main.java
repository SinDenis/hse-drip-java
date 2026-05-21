package task3;

public class Main {
    public static void main(String[] args) {
        ReportNotification full = new ReportNotification(
                "alice@example.com", "Q4 Report", "<h1>Report</h1>",
                "noreply@example.com", 3, true, "/reports/q4.pdf");
        System.out.println(full);

        ReportNotification simple = new ReportNotification(
                "bob@example.com", "Hi", "Hello!", null, 1, false, null);
        System.out.println(simple);
    }
}
