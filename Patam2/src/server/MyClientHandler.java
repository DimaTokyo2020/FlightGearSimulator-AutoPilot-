
package server;

import java.io.IOException;
import grafs.Searchable;
import grafs.Bfs;
import grafs.DronGame;
import grafs.State;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;

public class MyClientHandler implements ClientHandler
{/*
    Solver solver;
    CacheManager cm;
    
    @Override
    public void handleClient(final InputStream in, final OutputStream out) {
        final BufferedReader serverInput = new BufferedReader(new InputStreamReader(in));
        final PrintWriter outToServer = new PrintWriter(out);
        try {
        	
        	
            final String[] stringNumbers = new String[100];
            int i = 0;
            String fromClient;
            
            //ReadLines from Server Massage; Until he recive "end"
            while (!(fromClient = serverInput.readLine()).equals("end")) {
            	//Getting in every massage Row with numbers
                
            	
            	stringNumbers[i] = fromClient;
                System.out.println(stringNumbers[i]);
                ++i;
            }
            final String[] startS = (fromClient = serverInput.readLine()).split(",");
            final String[] goalS = (fromClient = serverInput.readLine()).split(",");
            //Puts StarPossition & GoalPossition as (x,y)
            final int[] startI = { Integer.parseInt(startS[0]), Integer.parseInt(startS[1]) };
            final int[] goalI = { Integer.parseInt(goalS[0]), Integer.parseInt(goalS[1]) };
            //Test
            System.out.println(String.valueOf(startI[0]) + "," + startI[1]);
            System.out.println(String.valueOf(goalI[0]) + "," + goalI[1]);
            //Splitting one row from "," to get The number of number inside 
            //row for initialize the matrix in the end
            final String[] splitedNum = stringNumbers[0].split(",");
            final String[][] matrix = new String[i][splitedNum[0].length() + 1];
            
            System.out.println("initializing the matrix");
            
            for (int q = 0; q < i; ++q) {
                final String[] splitedNumbers1 = stringNumbers[q].split(",");
                for (int w = 0; w < splitedNum[0].length() + 1; ++w) {
                    matrix[q][w] = splitedNumbers1[w];
                    System.out.println(String.valueOf(matrix[q][w]) + "[" + q + "]" + "[" + w + "]");
                }
            }
            final State<String> v = new State<String>(matrix);
            final DronGame x = new DronGame(v, startI, goalI, matrix.length, matrix[0].length);
            final Bfs bfs = new Bfs();
            outToServer.println(((DronSolution)bfs.search(x)).solve());
            outToServer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
