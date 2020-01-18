package Singlton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import Client.Client;
import Client.ClientFlightGear;
import Client.SetParametersRequest;
import Client.SpecificRequest;
import Commands.Command;
import Commands.ConditionCommand;
import Commands.ConnectCommand;
import Commands.DataServerCommand;
import Commands.DisconnectCommand;
import Commands.PrintCommand;
import Commands.SleepCommand;
import Commands.VarCommand;
import Expression.ExpressionConvertor;
import Expression.RegularVar;
import Expression.Var;
import Expression.VarsHashMap;
import Utilities.HashMapOfDataServerFG;
import server.FG_ClientHandler;
import server.MyServer;
import server.Server;


/*
--telnet=socket,in,10,127.0.0.1,5403,tcp --httpd=8080
--generic=socket,out,10,127.0.0.1,1234,tcp,generic_small
*/

public class Main {
	

	private static Boolean stop=false;
	private static String str=null;
	private static Boolean isThereMassage=false;
	
	public static void main(String[] args) {
		
	    
		MyServer server= new MyServer(5400);
		ClientFlightGear client =new ClientFlightGear("127.0.0.1",5402);
		
		
	
		
		
		//can hold inputs from keyboard
		BufferedReader inKeybord=new BufferedReader(new InputStreamReader(System.in));
		
		int input=-1;
		while(input!=0){
			try {
				input=Integer.parseInt(inKeybord.readLine());
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}//Waiting for input from user with keyboard
			
			
			
			switch(input){
			case 0: input=0;break;
			case 1: try {
					new Parset().parse(new Lexer().lexer(getCode1()),0,new Lexer().lexer(getCode1()).length);
				} catch (InstantiationException | IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}break;
			case 2: try {
					new Parset().parse(new Lexer().lexer(getCode2()),0,new Lexer().lexer(getCode2()).length);
				} catch (InstantiationException | IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}break;
			case 3: System.out.println(FG_ClientHandler.pathAndValueFromFG);break;
			case 4: Thread.interrupted();break;
			case 5: System.out.println(FG_ClientHandler.pathAndValueFromFG.entrySet());break;
			case 6: HashMapOfDataServerFG.getHelper().print();break;
			case 7: server.start(FG_ClientHandler.getHelper());break;
			case 8: client.start(SetParametersRequest.getHelper());break;
			case 9: SetParametersRequest.getHelper().setMassage("nasal");break;
			case 10: SetParametersRequest.getHelper().setMassage("nasal \r\n" + 
					"c172p.autostart(); \r\n" + 
					"##EOF##");break;
			case 11: SetParametersRequest.getHelper().setMassage("##EOF##");break;
			case 12: SetParametersRequest.getHelper().setMassage("clear");
			case 13: try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}}
		/*
			String e=new String("controls/flight/speedbrake = '0' (double)");
			String[] eSplited= e.split("'");
			for(String x:eSplited)
			System.out.println(x);
			*/
		
		
		
	}
	

	
		

	
		
		
	

	
	


	
	private static String getCode1() {
		
		return (" openDataServer 5400 10\r\n" + 
				" connect 127.0.0.1 5402\r\n" + 
				" var breaks = bind \"/controls/flight/speedbrake\"\r\n" + 
				" var throttle = bind \" /controls/engines/current-engine/throttle\"\r\n" + 
				" var heading = bind \"/instrumentation/heading-indicator/heading-bug-error-deg\"\"\r\n" + 
				" var airspeed = bind \"/instrumentation/airspeed-indicator/indicated-speed-kt\"\r\n" + 
				" var roll = bind \"/instrumentation/attitude-indicator/indicated-roll-deg\"\r\n" + 
				" var pitch = bind \"/instrumentation/attitude-indicator/internal-pitch-deg\"\r\n" + 
				" var rudder = bind \"/controls/flight/rudder\"\r\n" + 
				" var aileron = bind \"/controls/flight/aileron\"\r\n" + 
				" var elevator = bind \"/controls/flight/elevator\"\r\n" + 
				" var alt = bind \"/instrumentation/altimeter/indicated-altitude-ft\"\r\n") + 
				" throttle = 1\r\n"+ 
				" breaks = 0\r\n" + 
				" var h0 = heading\r\n" + 
				" while alt < 1000 {\r\n" + 
				" rudder = (h0 – heading)/60\r\n" + 
				" aileron = - roll / 70\r\n" + 
				" elevator = pitch / 50\r\n" + 
				" print alt\r\n" + 
				" sleep 250\r\n" + 
				" }\r\n" + 
				" print \"done\"";
		
	};
	
private static String getCode2() {
		
		
		return " breaks = 0\r\n" + 
				" throttle = 1\r\n"+ 
				" var h0 = heading\r\n";
				/*"while alt < 1000 {\r\n" + 
			" rudder = (h0 – heading)/20\r\n" + 
			" aileron = - roll / 70\r\n" + 
			" elevator = pitch / 50\r\n" + 
			" print alt\r\n" + 
			" sleep 250\r\n" + 
			" }\r\n" + 
			" print \"done\"";*/
		
	}
	
	
	public static void stop() {
		System.out.println("Stop=True");
		stop=true;};	
		
	public static String getStr() {return Main.str;}
	public static void setStr(String str) {Main.str=str;}
	public static boolean getStop() {return stop;};
	synchronized public static boolean getIsThereMsg() {return isThereMassage;}
	synchronized public static void noMsg() {
		System.out.println("isThereMassgae=false");
		isThereMassage=false;};
	
	public static void printVarCommands () {
		
		VarsHashMap varsHM=VarsHashMap.getVarsHashMap();
		System.out.println(varsHM);
		
	};	
	
	
}
