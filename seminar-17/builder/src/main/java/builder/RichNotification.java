package builder;

public final class RichNotification {
    private final String recipient;
    private final String subject;
    private final String body;
    private final String replyTo;
    private final int priority;
    private final boolean htmlBody;
    private final String attachmentPath;

    private RichNotification(Builder b) {
        this.recipient      = b.recipient;
        this.subject        = b.subject;
        this.body           = b.body;
        this.replyTo        = b.replyTo;
        this.priority       = b.priority;
        this.htmlBody       = b.htmlBody;
        this.attachmentPath = b.attachmentPath;
    }

    @Override
    public String toString() {
        return "RichNotification{recipient='" + recipient + '\'' +
               ", subject='" + subject + '\'' +
               ", body='" + body + '\'' +
               ", replyTo='" + replyTo + '\'' +
               ", priority=" + priority +
               ", htmlBody=" + htmlBody +
               ", attachment='" + attachmentPath + '\'' + '}';
    }

    public static class Builder {
        private final String recipient;
        private final String subject;
        private String body           = "";
        private String replyTo        = "";
        private int    priority       = 1;
        private boolean htmlBody      = false;
        private String attachmentPath = "";

        public Builder(String recipient, String subject) {
            this.recipient = recipient;
            this.subject   = subject;
        }

        public Builder body(String body)       { this.body = body; return this; }
        public Builder replyTo(String replyTo) { this.replyTo = replyTo; return this; }
        public Builder priority(int priority)  { this.priority = priority; return this; }
        public Builder htmlBody(boolean html)  { this.htmlBody = html; return this; }
        public Builder attachment(String path) { this.attachmentPath = path; return this; }

        public RichNotification build() { return new RichNotification(this); }
    }
}
