public class LoggingObserver implements RegistrationObserver {
    @Override
    public void update(String event) {
        System.out.println("Logging event: " + event);
    }
}
