package servers;

import computation.SearchSimulator;
import java.io.IOException;
import java.net.ServerSocket;

public class SingleThreadedServer implements Runnable {

  protected int serverPort;
  protected ServerSocket socket;
  protected boolean isStopped = false;


  public SingleThreadedServer(int port) throws IOException {
    this.serverPort = port;
    this.socket = new ServerSocket(serverPort);
  }

  public SingleThreadedServer() {

  }

  public void run() {
    try {
      openServerSocket();
    } catch (IOException e) {
      e.printStackTrace();
    }

    while (!isStopped()) {
      // wait for a connection
      // on receiving a request, execute the heavy computation
      System.out.println("Waiting for client");
      try {
        System.out.println("Accepted");
        SearchSimulator.processClientRequest();
        socket.accept();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error trying to open socket");
      }
    }
  }

  private synchronized boolean isStopped() {
    return this.isStopped;
  }

  public synchronized void stop() {
    // implementation to stop the server from the main thread if needed
    try {
      socket.close();
      System.out.println("Stopped");

    } catch (IOException exception) {
      exception.printStackTrace();
      System.out.println("Error trying to close socket");
    }
    isStopped = true;
  }

  private void openServerSocket() throws IOException {
    // open server socket here
    this.socket = new ServerSocket(serverPort);
    System.out.println("Server started");
  }
}