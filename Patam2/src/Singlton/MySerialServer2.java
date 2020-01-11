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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MySerialServer2 implements Server
{
	
	//Members
    private int p;//Number of conectting Client
    private int port;
    private volatile boolean stop;
    //Different ClientHandlers
    private ClientHandler ch;
    
    
    //CON
    public MySerialServer2(final int port) {
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
       // final ServerSocket server = new ServerSocket(this.port);
        //server.setSoTimeout(3000);
        
    	PrintWriter out = null;
        BufferedReader in = null;
    	
    	Socket socket=new Socket("127.0.0.1",5403);
    	while (!Main.getStop()) {
            //final Socket aClient = server.accept();
			try {
			    
				out = new PrintWriter(socket.getOutputStream());
			    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
			    //BufferedReader inKB=new BufferedReader(new InputStreamReader(System.in));
	            String theValue=null;
			    
			    //Loop till the client sent command "stop"
	            System.out.println("inside server");
	            while(!Main.getStop()) {
	            	//System.out.println("Thread server: "+Main.getIsThereMsg());
	            	Thread.interrupted();
	            	try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	            	if(Main.getIsThereMsg()) {
	            		System.out.println("sending: "+Main.getStr());
	            		out.println(Main.getStr());
	            		out.flush();
	            		theValue=in.readLine();
	            		System.out.println("from server: "+theValue);
	            		Main.setStr(theValue);
	            		Main.noMsg();
	            		theValue=null;
	            	
	            	}
	            	
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
