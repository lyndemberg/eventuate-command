package com.weba.config;

public class ProducerParameters {

    private String commandChannel = "webaServiceCommandChannel";
    private String replyChannel = "webaFilmsReplyChannel";
    private String subscriberId = "weba-films";

    public String getCommandChannel() {
        return commandChannel;
    }

    public void setCommandChannel(String commandChannel) {
        this.commandChannel = commandChannel;
    }

    public String getReplyChannel() {
        return replyChannel;
    }

    public void setReplyChannel(String replyChannel) {
        this.replyChannel = replyChannel;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }
}
