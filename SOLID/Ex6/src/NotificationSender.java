public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public final void send(Notification n) {
        if (n == null) throw new IllegalArgumentException("notification is null");
        String subject = n.subject == null ? "" : n.subject;
        String body = n.body == null ? "" : n.body;
        String email = n.email == null ? "" : n.email;
        String phone = n.phone == null ? "" : n.phone;
        doSend(subject, body, email, phone);
    }

    protected abstract void doSend(String subject, String body, String email, String phone);
}