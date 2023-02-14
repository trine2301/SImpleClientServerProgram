package computation;

import java.net.Socket;

public class AsyncSearchSimulator implements Runnable {

  protected Socket clientSocket;
  protected String serverText;

  public AsyncSearchSimulator(Socket clientSocket, String serverText) {
    this.clientSocket = clientSocket;
    this.serverText = serverText;
  }

  public void run() {

  }
}
