import computation.SearchSimulator;
import java.io.IOException;

import servers.SingleThreadedServer;

/**
 * Represents the main-class.
 * @version 0.1
 */
class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("Starting server");
    // Start your server here
    SingleThreadedServer singleThreadedServer = new SingleThreadedServer();
    singleThreadedServer.run();
    singleThreadedServer.stop();

    try {
      SearchSimulator.processClientRequest();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}