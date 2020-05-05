
import com.google.gson.Gson;
import java.util.HashMap;

/**
 * MessagingLogic will be responsible for parsing received data, do
 * authentication and forward the messages between users.
 * 
 * @author amrishodiq
 */
public class MessagingLogic {

  private static MessagingLogic instance;

  public static MessagingLogic getInstance() {
    if (instance == null) {
      instance = new MessagingLogic();
    }
    return instance;
  }

  private Gson gson = new Gson();

  private MessagingLogic() {
  }

  public void receiveText(String text) {
    try {
      System.out.println("LLEGO la DATA: "+text);
      receiveData(gson.fromJson(text, Data.class));
      
    } catch (Throwable t) {
    }
    
  }

  private void receiveData(Data data) {
   // System.out.println("LLEGO la DATA");
    if (data == null)
      return;

    switch (data.operation) {

    case Data.MESSAGING_SEND:
      send(data.message);
      break;
    }
  }





  private void send(Message message) {
  
      System.out.println("try to send message");

      try {
        //userSession.receiveText(gson.toJson(message));
      } catch (Exception ex) {
        // put to offline message
        System.out.println("User is offline");
      }
  
  }

}