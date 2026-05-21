package adapter;

public class LegacyAlertService {
    public void triggerAlert(String destination, String message) {
        System.out.printf("[LEGACY-ALERT] dst=%s msg=%s%n", destination, message);
    }
}
