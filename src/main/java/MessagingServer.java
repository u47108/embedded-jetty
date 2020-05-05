
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class MessagingServer {
  private Server server;

  public void setup() {
    server = new Server();
    ServerConnector connector = new ServerConnector(server);
    connector.setPort(7171);
    server.addConnector(connector);

    ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
    handler.setContextPath("");
    server.setHandler(handler);

    handler.addServlet(MessagingServlet.class, "");
  }

  public void start() throws Exception {
    server.start();
    server.dump(System.err);
    server.join();
  }

  public static void main(String args[]) throws Exception {
    MessagingServer theServer = new MessagingServer();
    theServer.setup();
    theServer.start();
  }
}
