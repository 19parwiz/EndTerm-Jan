public class EmailNotificationObserver implements RegistrationObserver {
    @Override
    public void update(String event) {
        System.out.println("Sending email notification for event: " + event);
    }
}
