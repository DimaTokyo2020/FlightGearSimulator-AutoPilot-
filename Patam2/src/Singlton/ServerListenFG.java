// 
// Decompiled by Procyon v0.5.36
// 

package Singlton;

import java.net.Socket;
import java.net.SocketTimeoutException;

import server.ClientHandler;
import server.Server;

import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServerListenFG implements Server
{
	
	//Members
    private int p;//Number of conectting Client
    private int port;
    private volatile boolean stop;
    //Different ClientHandlers
    private ClientHandler ch;
    
    
    //CON
    public ServerListenFG(final int port) {
        this.port = port;
        this.stop = false;
    }
    
    //Opening New Thread for each Client 
    //initialize	 specific clientHeandler
    public void start(final ClientHandler ch) {
    	System.out.println("Opening New Tread");
        this.ch = ch;
        //Lambda Expression Override run/Start
        new Thread(() -> {
            try {
                this.runServer(this.port);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    
    
    public void runServer(final int port) throws IOException {
    	System.out.println("Wait for server");
    	ServerSocket socket = new ServerSocket(5402);
    	 //wait, on welcoming socket for contact by client
        Socket connectionSocket = socket.accept();
    	
    	while (!Main.getStop()) {
			try {
				BufferedReader in = 
					      new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
					      //create output stream, attached to socket
				 DataOutputStream out =
					      new DataOutputStream(connectionSocket.getOutputStream());
				
			    String theValue=null;
			    
			    System.out.println("inside server");
			    //Loop till the client sent command "stop"
	            while(!Main.getStop()) {
	            	theValue = in.readLine();	
	            	System.out.println("from server: "+theValue);
	            }
			}
			catch (IOException ex) {}
        }
        //server.close();
    }
    
    public void open() {
    }
    
    public void stop() {
        this.stop = true;
    }
}
