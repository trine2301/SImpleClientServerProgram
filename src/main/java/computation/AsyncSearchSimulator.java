package computation;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

import utils.ResponseGenerator;

/**
 */
public class AsyncSearchSimulator implements Runnable {

  protected Socket clientSocket;
  protected String serverText;

  public AsyncSearchSimulator(Socket clientSocket, String serverText) {
    this.clientSocket = clientSocket;
    this.serverText = serverText;
  }

  public void run() {
    try {
      InputStream input = clientSocket.getInputStream();
      OutputStream output = clientSocket.getOutputStream();

      long time1 = System.currentTimeMillis();
      System.out.println("Request processing started at: " + time1);
      Thread.sleep(10 * 1000);
      long time2 = System.currentTimeMillis();
      System.out.println("Request processing ended at: " + time2);

      // Generating response
      byte[] responseDocument = ResponseGenerator.generatorResponseHTML(time1, time2).getBytes("UTF-8");
      byte[] responseHeader = ResponseGenerator.generatorResponseHeader(responseDocument.length).getBytes("UTF-8");

      output.write(responseHeader);
      output.write(responseDocument);
      output.close();
      input.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
