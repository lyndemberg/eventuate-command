package br.edu.ifpb.uploadservice.consumer;

public class CommandConsumerParameters {
  private String commandChannel = "webaServiceCommandChannel";
  private String dispatcherId = "webaServiceCommandDispatcher";

  public String getCommandChannel() {
    return commandChannel;
  }

  public void setCommandChannel(String commandChannel) {
    this.commandChannel = commandChannel;
  }

  public String getDispatcherId() {
    return dispatcherId;
  }

  public void setDispatcherId(String dispatcherId) {
    this.dispatcherId = dispatcherId;
  }
}
