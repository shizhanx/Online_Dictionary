package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Shizhan Xu, 771900
 * University of Melbourne
 * All rights reserved
 */
public class DictionaryServer {
    // A client will be disconnected after idling for this amount of time.
    private static final int CONNECTION_TIMEOUT = 300000;

    public static void main(String[] args) {
        // Ensure two argument inputs
        if (args.length != 2) {
            ServerError.argError("need 2 arguments");
        }

        int port = 0;
        String filePath = args[1];
        Dictionary dic = new Dictionary(filePath);

        // Try to parse the port number
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            ServerError.argError("first argument must be integer");
        }

        // Initialize the UI
        UI ui = new UI();
        ui.setVisible(true);

        // Opens the server socket for connection
        try (ServerSocket server = new ServerSocket(port)) {
            while(true){
                Socket res = server.accept();
                res.setSoTimeout(CONNECTION_TIMEOUT);
                ServerThread thread = new ServerThread(dic, res, ui);
                ui.addClient(res);
                thread.start();
            }
        } catch (IllegalArgumentException e) {
            ServerError.connectionError(ui,
                    "port number must be between 0 and 65535");
            System.exit(1);
        } catch (IOException e) {
            ServerError.connectionError(ui,
                    "port number in use");
            System.exit(1);
        }
    }
}
