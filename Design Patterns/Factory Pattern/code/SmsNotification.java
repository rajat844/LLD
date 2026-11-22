public class SmsNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Send SMS : " + message);
    }

    public static class Creator extends NotificationCreator {
        @Override
        public Notification createNotification() {
            return new SmsNotification();
        }
    }

}
