public class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Email Notification : " + message);
    }

    public static class Creator extends  NotificationCreator {
        @Override
        public Notification createNotification() {
            return new EmailNotification();
        }
    }
}
