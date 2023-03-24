package servers;

import java.net.ServerSocket;
import java.net.Socket;

import computation.SearchSimulator;

import java.io.IOException;

public class SingleThreadedServer implements Runnable {

  protected int serverPort = 8080;
  protected ServerSocket serverSocket = null;
  protected boolean isStopped = false;
  protected Thread runningThread = null;

  public SingleThreadedServer(int port) {
    this.serverPort = port;
  }

  public void run() {
    openServerSocket();

    while (!isStopped()) {
      Socket clientSocket;

      try {
        // wait for a client to connect
        System.out.println("Listening for connection.");
        clientSocket = this.serverSocket.accept();
      } catch (IOException e) {
        if (isStopped()) {
          System.out.println("Server Stopped.");
          return;
        }
        throw new RuntimeException(
            "Error accepting client connection", e);
      }

      try {
        SearchSimulator.processClientRequest(clientSocket);
      } catch (Exception e) {
        // log exception and go on to next request.
      }
    }
    stop();
    System.out.println("Server Stopped.");
  }

  private synchronized boolean isStopped() {
    return this.isStopped;
  }

  public synchronized void stop() {
    this.isStopped = true;
    try {
      this.serverSocket.close();
    } catch (IOException e) {
      throw new RuntimeException("Error closing server", e);
    }
  }

  private void openServerSocket() {
    try {
      this.serverSocket = new ServerSocket(this.serverPort);
    } catch (IOException e) {
      throw new RuntimeException("Cannot open port 8080", e);
    }
  }
}