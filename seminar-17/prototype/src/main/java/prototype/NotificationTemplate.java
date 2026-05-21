package prototype;

import java.util.Arrays;

public final class NotificationTemplate implements Cloneable {
    private String recipient;
    private String subject;
    private String body;
    private String[] tags;

    public NotificationTemplate(String recipient, String subject, String body, String... tags) {
        this.recipient = recipient;
        this.subject   = subject;
        this.body      = body;
        this.tags      = tags.clone();
    }

    public NotificationTemplate withRecipient(String recipient) {
        NotificationTemplate copy = clone();
        copy.recipient = recipient;
        return copy;
    }

    public NotificationTemplate withBody(String body) {
        NotificationTemplate copy = clone();
        copy.body = body;
        return copy;
    }

    @Override
    public NotificationTemplate clone() {
        try {
            NotificationTemplate copy = (NotificationTemplate) super.clone();
            copy.tags = this.tags.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return "Template{recipient='" + recipient + "', subject='" + subject +
               "', body='" + body + "', tags=" + Arrays.toString(tags) + "}";
    }
}
