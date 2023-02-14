/**
package servers;

import computation.AsyncSearchSimulator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {

  protected int serverPort = 8080;
  protected Socket clientSocket;
  protected boolean isStopped = false;

  //It did not initially return void. Be aware.
  public void SingleThreadedServer(int port) throws IOException {
    this.serverPort = port;
  }


  public void run() {

    while (!isStopped()) {
      // wait for a connection
      System.out.println("waiting for client");

      try {
        openServerSocket();
      } catch (IOException e) {
        e.printStackTrace();
      }

      // on receiving a request, execute the heavy computation in a new thread
      new Thread(
          new AsyncSearchSimulator(
              clientSocket,
              "Multithreaded Server"
          )
      ).start();
    }
    isStopped();
  }


  private synchronized boolean isStopped() {
    return this.isStopped;
  }

  public synchronized void stop() throws IOException {
    // implementation to stop the server from the main thread if needed
    clientSocket.close();
    System.out.println("Server Stopped.");
    isStopped = true;
  }


  private void openServerSocket() throws IOException {
    // open server socket here
    ServerSocket socket = new ServerSocket(serverPort);
    System.out.println("Server started");
    Socket client = socket.accept();
    System.out.println("Client accepted");
  }
}

*/