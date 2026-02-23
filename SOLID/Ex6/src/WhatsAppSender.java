public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void doSend(String subject, String body, String email, String phone) {
        if (phone.isEmpty() || !phone.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
        System.out.println("WA -> to=" + phone + " body=" + body);
        audit.add("wa sent");
    }
}