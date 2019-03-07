/* 
 * Project: Project 1
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	9/23/2018
 * Description:	Main class: to run the program.
 */
package project1_8puzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException{		
		
		Scanner scan = new Scanner(System.in);		
		int input = 0;
		while (input!=4){
			System.out.println("Main Menu: "
	                + "\n(1) Generate random 8-puzzle problem."
	                + "\n(2) Enter a specific 8-puzzle configuration, each digits are separated by a space."
	                + "\n(3) read text file."
	                + "\n(4) Exit");
	    	
	    	input = scan.nextInt();	  	    	
	    	switch(input){
	    	case 1:
	    		randomPuzzle();
                break;               
	    	case 2:
                enterPuzzle();
                break;
	    	case 3:
	    		readTest();
                break;
            case 4:
            	System.out.println("Bye Bye. \n");
                break;                
            default:
                System.out.println("Invalid Choose.!\n");
                break;
	    	
	    	}//end switch
						
	    }//end while
	}
	
    public static void randomPuzzle() {
    	int[] array = {3, 1, 2, 4, 7, 5, 6, 8, 0};
		shuffle(array); 	
		//for h1
		System.out.println("When heuristic is the number of misplaced tiles (h1): ");
		long startTime = System.currentTimeMillis();
		State startH1 = new MissTilesState(array);
        AStar runnerH1 = new AStar(startH1);
        if (runnerH1.isSolvable(startH1)) {
            State goalH1 = runnerH1.run();
            List<State> list = runnerH1.traceBack(goalH1);
            System.out.println("Form Start State to Goal State: ");
            for (int i = list.size() - 1; i >= 0; i--) {
            	list.get(i).print();
            }                     
            System.out.println("The Depth is: " + goalH1.getgValue());
            System.out.println("A* Search Costs : " + runnerH1.getNodeGenerated());
        }
        else {
            System.out.println("Not solvable.");
        }
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("the time used: " + duration);
        System.out.println();
        
        //for h2
        System.out.println("When heuristic is manhattan distance (h2): ");
        startTime = System.currentTimeMillis();
        State startH2 = new ManhattanState(array);
        AStar runnerH2 = new AStar(startH2);
        if (runnerH2.isSolvable(startH2)) {
            State goalH2 = runnerH2.run();
            List<State> list = runnerH2.traceBack(goalH2);
            System.out.println("Form Start State to Goal State: ");
            for (int i = list.size() - 1; i >= 0; i--) {
            	list.get(i).print();
            }                     
            System.out.println("The Depth is: " + goalH2.getgValue());
            System.out.println("A* Search Costs" + runnerH2.getNodeGenerated());
        }
        else {
            System.out.println("Not solvable.");
        }
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("the time used: " + duration);
    }
    
    private static void enterPuzzle() throws IOException {
  	
		System.out.println("Please input a specific 8-puzzle configuration, and the digits are separated by a space: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        
        if(array.length != 9) {
        	throw new IOException("Wrong Input: there should be 9 digits. ");
        }
        else {
        	for(int i = 0; i < array.length; i++) {
            	if(array[i] > 8) {
            		throw new IOException("Wrong Input: digits should be less than 9. ");
            	}
            }
        }
        	
		System.out.println("When heuristic is the number of misplaced tiles (h1): ");
        long startTime = System.currentTimeMillis();
		State startH1 = new MissTilesState(array);
        AStar runnerH1 = new AStar(startH1);
        if (runnerH1.isSolvable(startH1)) {
            State goalH1 = runnerH1.run();
            List<State> list = runnerH1.traceBack(goalH1);
            System.out.println("Form Start State to Goal State: ");
            for (int i = list.size() - 1; i >= 0; i--) {
            	list.get(i).print();
            }                     
            System.out.println("The Depth is: " + goalH1.getgValue());
            System.out.println("A* Search Costs : " + runnerH1.getNodeGenerated());
        }
        else {
            System.out.println("Not solvable.");
        }
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("the time used: " + duration );
        
        System.out.println();
        //for h2
        System.out.println("When heuristic is manhattan distance (h2): ");
        startTime = System.currentTimeMillis();
        State startH2 = new ManhattanState(array);
        AStar runnerH2 = new AStar(startH2);
        if (runnerH2.isSolvable(startH2)) {
            State goalH2 = runnerH2.run();
            List<State> list = runnerH2.traceBack(goalH2);
            System.out.println("Form Start State to Goal State: ");
            for (int i = list.size() - 1; i >= 0; i--) {
            	list.get(i).print();
            }                     
            System.out.println("The Depth is: " + goalH2.getgValue());
            System.out.println("A* Search Costs : " + runnerH2.getNodeGenerated());
        }
        else {
            System.out.println("Not solvable.");
        }
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("the time used: " + duration);
        
	}
 
    private static void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0 ; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    
    public static void readTest() {
    	final String FILENAME = "C:\\Users\\gfy13\\Desktop\\2018 Fall\\CS 4200 - Artificial Intelligence\\Project1\\100 Scrambled Puzzles.txt"; 
    	int aveH1 = 0;
    	int aveH2 = 0;
    	int conuter = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.length() == 17) {
					int[] array = Arrays.stream(sCurrentLine.split("\\s")).mapToInt(Integer::parseInt).toArray();
					conuter++;
					//for h1
					long startTime = System.currentTimeMillis();
					State startH1 = new MissTilesState(array);
			        AStar runnerH1 = new AStar(startH1);
			        if (runnerH1.isSolvable(startH1)) {
			            State goalH1 = runnerH1.run();                   
			        }
			        else {
			            System.out.println("Not solvable.");
			        }
			        long endTime = System.currentTimeMillis();
			        long duration1 = (endTime - startTime);
			        
			        //for h2
			        startTime = System.currentTimeMillis();
			        State startH2 = new ManhattanState(array);
			        AStar runnerH2 = new AStar(startH2);
			        if (runnerH2.isSolvable(startH2)) {
			            State goalH2 = runnerH2.run();			            
			        }
			        else {
			            System.out.println("Not solvable.");
			        }
			        endTime = System.currentTimeMillis();
			        long duration2 = (endTime - startTime);
			        aveH1 += runnerH1.getNodeGenerated();
			        aveH2 += runnerH2.getNodeGenerated();
			        
			        System.out.printf("%-22s%-22s%-10s%-22s%-10s\n", sCurrentLine, runnerH1.getNodeGenerated(), duration1, runnerH2.getNodeGenerated(), duration2);
			        
				}
				else {
					System.out.printf("%-22s%-22s%-10s%-22s%-10s\n", sCurrentLine, "h1", "h1-time", "h2", "h2-time");
				}
				
			}//end while
			System.out.println("total has: " + conuter);
			System.out.println("average h1: " + aveH1/conuter);
			System.out.println("average h2: " + aveH2/conuter);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	  	
    }
	
	
	
	
}
