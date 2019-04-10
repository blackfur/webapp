package practise;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import net.jodah.concurrentunit.Waiter;

class TCPTest {

    Logger log = Logger.getLogger(getClass().getSimpleName());

    @Test
    void sample() throws Throwable{
        final Waiter waiter = new Waiter();
        log.info("waiter Please.");

        new Thread(new Runnable() {
            public void run() {
                    ServerSocket welcomeSocket =null;
                try {
                    String resp = null;
                    welcomeSocket = new ServerSocket(6789);
                    log.info("Server listening...");
                    while(null == resp){
                        Socket connectionSocket = welcomeSocket.accept();
                        log.info("Welcome...");
                        BufferedReader inFromClient = new BufferedReader( new InputStreamReader(connectionSocket.getInputStream()));
                        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                        resp = inFromClient.readLine();
                        //inFromClient.close();
                        log.info("server Received: " + resp);
                        String capitalizedSentence = "####-" + resp.toUpperCase() + "-####\n";
                        log.info("Server feedback: "+capitalizedSentence);
                        outToClient.writeBytes(capitalizedSentence);
                        //outToClient.close();
                        connectionSocket.close();
                        log.info("disconnect from client.");
                    }
                    welcomeSocket.close();
                    log.info("Server shutdown.");
                    waiter.resume();
                    log.info("leave me alone.");
                } catch (Exception e) {
                    log.error("Server fault, closing socket...", e);
                    try {
                        welcomeSocket.close();
                    } catch (Exception ex) {
                        log.error("Server init socket failed.",ex);
                    }
                    waiter.fail();
                    log.info("fuck out of my way.");
                    return;
                }

            }
        }).start();
        log.info("Server thread Started.");

        String answer=null;
        log.info("Client Connecting...");
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        log.info("Client Create output buffer.");
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        log.info("Client Create input buffer.");
        String clientMsg = "May I ask a question?\n";
        outToServer.writeBytes(clientMsg);
        log.info("client ask: " + clientMsg);
        answer= inFromServer.readLine();
        log.info("FROM SERVER: " + answer);
        clientSocket.close();
        log.info("Client shutdown.");
        log.info("What Can I do for You?");
        waiter.await();
    }
}