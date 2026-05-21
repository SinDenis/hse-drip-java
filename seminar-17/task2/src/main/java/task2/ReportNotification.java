package task2;

public class ReportNotification {

    private final String recipient;
    private final String subject;
    private final String body;
    private final String replyTo;
    private final int priority;
    private final boolean htmlBody;
    private final String attachmentPath;

    public ReportNotification(String recipient, String subject, String body,
                               String replyTo, int priority, boolean htmlBody,
                               String attachmentPath) {
        this.recipient      = recipient;
        this.subject        = subject;
        this.body           = body;
        this.replyTo        = replyTo;
        this.priority       = priority;
        this.htmlBody       = htmlBody;
        this.attachmentPath = attachmentPath;
    }

    @Override
    public String toString() {
        return "ReportNotification{recipient='" + recipient + '\'' +
               ", subject='" + subject + '\'' +
               ", body='" + body + '\'' +
               ", replyTo='" + replyTo + '\'' +
               ", priority=" + priority +
               ", htmlBody=" + htmlBody +
               ", attachment='" + attachmentPath + '\'' + '}';
    }
}
