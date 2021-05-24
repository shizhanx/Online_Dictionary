package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Shizhan Xu, 771900
 * University of Melbourne
 * All rights reserved
 */

public class DictionaryClient {
    private String address;
    private int port;
    private DataInputStream in;
    private DataOutputStream out;


    /**
     * Start a client of the online dictionary system
     * @param args the host address and the port number
     */
    public static void main(String[] args) {
        // Make sure the amount of argument is correct
        if (args.length != 2) {
            ClientError.argError("need 2 arguments");
        }

        DictionaryClient client = new DictionaryClient();

        client.address = args[0];
        client.port = 0;

        // Make sure the port is a valid integer
        try {
            client.port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            ClientError.argError("second argument must be integer");
        }

        // Initialize the GUI.
        new UI(client);
    }

    /**
     * Try to connect to the specified server and socket.
     * @throws IllegalArgumentException port number incorrect
     * @throws IOException connection problems
     */
    protected void connect()
            throws IllegalArgumentException, IOException {
        Socket socket = new Socket(address, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Send an encoded String request to the server and
     * expect to receive an encoded String response.
     */
    protected String sendRequestForResponse(String s) {
        try {
            out.writeUTF(s);
            return in.readUTF();
        } catch (IOException e) {
            return "Error//Unable to connect to the server, please restart the client";
        }
    }

}
