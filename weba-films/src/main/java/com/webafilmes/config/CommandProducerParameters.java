package com.webafilmes.config;

public class CommandProducerParameters {

    private String commandChannel = "webaServiceCommandChannel";
    private String replyChannel = "webaServiceReplyChannel";

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
}
