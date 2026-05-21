package observer;

import java.util.HashMap;
import java.util.Map;

public class MetricsObserver implements ChannelObserver {
    private final Map<String, Integer> counts = new HashMap<>();

    @Override
    public void onEvent(ChannelEvent e) {
        counts.merge(e.channelName(), 1, Integer::sum);
        System.out.printf("[METRICS-OBS] channel=%s totalSends=%d%n",
                e.channelName(), counts.get(e.channelName()));
    }
}
