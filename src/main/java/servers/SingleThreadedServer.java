package servers;

import computation.SearchSimulator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedServer implements Runnable {

  protected int serverPort;
  protected ServerSocket serverSocket;
  protected boolean isStopped = false;


  public SingleThreadedServer(int port) throws IOException {
    this.serverPort = port;
    //this.serverSocket = new ServerSocket(serverPort);
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
        Socket client = serverSocket.accept();
        System.out.println("Client accepted");
        SearchSimulator.processClientRequest();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  private synchronized boolean isStopped() {
    return this.isStopped;
  }

  public synchronized void stop() {
    // implementation to stop the server from the main thread if needed
    try {
      serverSocket.close();
      System.out.println("Stopped");
    } catch (IOException exception) {
      exception.printStackTrace();
      System.out.println("Error trying to close socket");
    }
    isStopped = true;
  }

  private void openServerSocket() throws IOException {
    // open server socket here
    this.serverSocket = new ServerSocket(serverPort);
    System.out.println("Server started");
  }
}