package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 * Shizhan Xu, 771900
 * University of Melbourne
 * All rights reserved
 */
public class ServerThread extends Thread{
    private final Dictionary dictionary;
    private final Socket socket;
    private final UI ui;

    public ServerThread(Dictionary dictionary, Socket socket, UI ui) {
        this.dictionary = dictionary;
        this.socket = socket;
        this.ui = ui;
    }

    /**
     * Thread-per-connection structure:
     * Continuously receive request from the same client,
     * auto-close the in-out streams after the connection ends.
     */
    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            while (true) {
                // Decode the request according to my protocol.
                String[] request = in.readUTF().split("//");
                System.out.println("Request received: " + Arrays.toString(request));
                // Response according to the request type.
                switch (request[0]) {
                    case "search":
                        if (request.length != 2)
                            System.err.println("Wrong request format");
                        else {
                            ui.receiveRequest(request[0] + " " + request[1], socket.getPort());
                            String result = dictionary.search(request[1]);
                            if (result == null)
                                out.writeUTF("Fail//Word doesn't exist");
                            else
                                out.writeUTF("Success//" + result);
                        }
                        break;
                    case "delete":
                        if (request.length != 2)
                            System.err.println("Wrong request format");
                        else {
                            ui.receiveRequest(request[0] + " " + request[1], socket.getPort());
                            if (dictionary.delete(request[1]))
                                out.writeUTF("Success//Word is deleted");
                            else
                                out.writeUTF("Fail//Word doesn't exist");
                        }
                        break;
                    case "update":
                        if (request.length != 3)
                            System.err.println("Wrong request format");
                        else {
                            ui.receiveRequest(request[0] + " " + request[1], socket.getPort());
                            if (dictionary.update(request[1], request[2])) {
                                out.writeUTF("Success//Word has been updated");
                            } else
                                out.writeUTF("Fail//Word doesn't exist");
                        }
                        break;
                    case "add":
                        if (request.length != 3)
                            System.err.println("Wrong request format");
                        else {
                            ui.receiveRequest(request[0] + " " + request[1], socket.getPort());
                            if (dictionary.add(request[1], request[2])) {
                                out.writeUTF("Success//Word is added");
                            } else
                                out.writeUTF("Fail//Word already exist");
                        }
                        break;
                }
            }
        } catch (IOException e) {
            // IO Exception would occur when the in/out stream or the socket itself
            // has got a connection problem. Only socket needs manual close
            // Remove this client from the UI as well. This thread ends afterwards.
            System.err.println(socket.getPort() +
                    ": The connection is over, closing socket now");
            ServerError.connectionError(ui,
                    "client " + socket.getPort() + " just got disconnected");
            ui.removeClient(socket.getPort());
            while (!socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
