package Commands;

import java.util.ArrayList;

import Client.SetParametersRequest;
import Expression.ExpressionConvertor;
import Expression.RegularVar;
import Expression.VarsHashMap;
import Singlton.Main;
import Utilities.HashMapOfDataServerFG;
import server.FG_ClientHandler;

public class VarCommand extends CommonCommand {

	ArrayList<String> strArray;
	
	

	@Override
	public void doCommand() {

		//System.out.println("Inside do comand : "+code[index-1]+","+code[index]+","+code[index+1]);
		strArray=new ArrayList();
		boolean isAnewVar=true;
		
		//case "bind"
		//[var][throttle][=][bind]["][/controls/engines/current][-][engine/throttle]["]
		//  ^      ^      ^   ^    ^
		//  |      |      |   |    |
		// -2|     -1  |index|+1| +2|
				
		//case default
		//	var h0 = heading
		
		String name=code[index-1];
		StringBuilder sbPath=new StringBuilder();
		
		//This situation 
		//[var][throttle][=][bind]["][/controls/engines/current][-][engine/throttle]["]

		//[var][y][=][bind][simX][
		///////////////////////////////////////////////////////
		if(code[index+1].equals("bind")& (index-2)!=-1) {
			if(code[index-2].equals("var")&&code[index+2].charAt(0)==((char)34)) {
			//if(code[index-2].equals("var")) {
				//["][/controls/engines/current][-][engine/throttle]["]
				//StringBilder Connect the path to one string
				index=index+3;
				while(!(code[index].charAt(0)==((char)34))) {
					sbPath.append(code[index]);
					index++;
					}		}
				
				if(sbPath.length()!=0) {
					String sbPathAsString=sbPath.toString();
					HashMapOfDataServerFG.getHelper().put(name,sbPathAsString);
				}
				else if(code[index-2].equals("var"))
					HashMapOfDataServerFG.getHelper().put(name,code[index+2]);	
				else 
					HashMapOfDataServerFG.getHelper().updatePath(name,code[index+2]);
				}
		
		else if(code[index+1].equals("bind"))
			HashMapOfDataServerFG.getHelper().updatePath(name,code[index+2]);
		//////////////////////////////////////////////////////////////////////////
		//[var] [h0] [=] [heading]
		//[rudder] [=] [(] [h0] [�] [heading] [)] [/] [20]
		else if (!code[index+1].equals("bind")&(index-2)!=-1) {
			if(code[index-2].equals("var")) 
				HashMapOfDataServerFG.getHelper().putVarWithoutPath(name,connectExpretionAndConvertToDouble(code, index, "\r"));
			else//[rudder] [=] [(] [h0] [�] [heading] [)] [/] [20] 
				sendToTheServer(name,connectExpretionAndConvertToDouble(code, index, "\r"));
			}
		//[rudder] [=] [(] [h0] [�] [heading] [)] [/] [20]
		else {
			sendToTheServer(name, connectExpretionAndConvertToDouble(code, index, "\r"));	
		}
	}

	
	
	
	
	@Override
	public String toString() {

		return this.getClass().toString()+": " +index + ",";
	}
	
	
}
