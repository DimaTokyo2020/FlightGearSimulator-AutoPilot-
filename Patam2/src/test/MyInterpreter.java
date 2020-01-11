package test;

import Singlton.AdapterOfSplitedLines;
import Singlton.Lexer;
import Singlton.Parset;

public class MyInterpreter {

	static public int value;
	
	public static  int interpret(String[] lines){
		int x=0;
		try {
			x=new Parset().parse(new Lexer().lexer(AdapterOfSplitedLines.splitedArrStringToString(lines)),0,new Lexer().lexer(AdapterOfSplitedLines.splitedArrStringToString(lines)).length);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return x;
	}
	
	
	
}
