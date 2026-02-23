public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void doSend(String subject, String body, String email, String phone) {
        System.out.println("SMS -> to=" + phone + " body=" + body);
        audit.add("sms sent");
    }
}