package Commands;

import java.util.ArrayList;
import java.util.HashMap;

import Client.SetParametersRequest;
import Expression.ExpressionConvertor;
import Utilities.HashMapOfDataServerFG;

public abstract class CommonCommand implements Command {


	String[] code;
	public int index;
	
	public void setArguments(String[] code,int index ) {
		
		this.code=code;
		this.index=index;
		};
	
		public abstract void doCommand();
	

		static public double connectExpretionAndConvertToDouble(String[] code,int start,String end){
			
			ArrayList<String> strArray=new ArrayList<String>();
			//two stops length and "end"
			while((start+1)!=code.length) {
				start++;
				if(!code[start].equals(end))//
					strArray.add(code[start]);
				else
					break;
			}
			return ExpressionConvertor.calculatePostfix((ExpressionConvertor.infixToPostfix(strArray)));
		}
		
		
		static public void  sendToTheServer (String name, double value) {
			
			
			SetParametersRequest.getHelper().setMassage("set "+HashMapOfDataServerFG.getHelper().getPath(name) + " "+value);
			while(SetParametersRequest.getHelper().massageToServer!=null&&SetParametersRequest.getHelper().turnOn) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			HashMapOfDataServerFG.getHelper().updateValue(name,value);
			
		}


		
		
}
