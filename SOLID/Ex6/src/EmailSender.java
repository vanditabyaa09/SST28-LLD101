public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void doSend(String subject, String body, String email, String phone) {
        if (body.length() > 40) body = body.substring(0, 40);
        System.out.println("EMAIL -> to=" + email + " subject=" + subject + " body=" + body);
        audit.add("email sent");
    }
}