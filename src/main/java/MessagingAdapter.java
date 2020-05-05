import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
/**
 * MessagingAdapter responsible to handle connection, receiving data, forward 
 * the data to @see id.amrishodiq.jettywebsocket.MessagingLogic and receive 
 * data from @see id.amrishodiq.jettywebsocket.MessagingLogic to be 
 * delivered to recipient.
 * @author amrishodiq
 */
public class MessagingAdapter extends WebSocketAdapter implements SessionWebSocket{
    
    private Session session;
    
    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session); 
        
        this.session = session;
    }
    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        //MessagingLogic.getInstance().setOffline();
        
        this.session = null;
        
        System.err.println("Close connection "+statusCode+", "+reason);
        
        super.onWebSocketClose(statusCode, reason); 
    }
    @Override
    public void onWebSocketText(String message) {
        super.onWebSocketText(message); 
       // System.out.println("LLEGO la DATA");
        MessagingLogic.getInstance().receiveText( message);
    }
    @Override
    public void receiveText(String text) throws Exception {
        if (session != null && session.isOpen()) {
            session.getRemote().sendString(text);
        }
    }

    @Override
    public void disconnect(int status, String reason) {
        
        session.close(status, reason);
        disconnect(status, reason);
    }
    @OnWebSocketError
    public void onError(Throwable error){
      System.out.println("error en mensaje");
    }
}