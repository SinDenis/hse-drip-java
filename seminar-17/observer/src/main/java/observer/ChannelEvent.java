package observer;

import core.Notification;

public record ChannelEvent(String channelName, Notification notification, boolean success) {}
