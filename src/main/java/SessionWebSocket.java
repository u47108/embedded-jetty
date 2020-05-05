
public interface SessionWebSocket {
  void receiveText(String text) throws Exception;
  void disconnect(int status, String reason);
}
