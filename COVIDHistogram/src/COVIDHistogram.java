import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * ICS Assignment 7
 * @WaylonHo 
 */

public class COVIDHistogram {

	//printLabel method
	public static void printLabel(int n) {
		switch (n) {
			case 0:
				System.out.printf("%12s", "0: ");
				break;
			case 1:
				System.out.printf("%12s", "1-500: ");
				break;
			case 2:
				System.out.printf("%12s", "501-1000: ");
				break;
			case 3:
				System.out.printf("%12s", "1001-1500: ");
				break;
			case 4:
				System.out.printf("%12s", "1501-2000: ");
				break;
			case 5:
				System.out.printf("%12s", "2001-2500: ");
				break;
			case 6:
				System.out.printf("%12s", "2501-3000: ");
				break;
			case 7:
				System.out.printf("%12s", "3001-3500: ");
				break;
			case 8:
				System.out.printf("%12s", "3501-4000: ");
				break;
			case 9:
				System.out.printf("%12s", "4001-4500: ");
				break;
			case 10:
				System.out.printf("%12s", "4501-5000: ");
				break;
			case 11:
				System.out.printf("%12s", ">5000: ");
				break;
			default:
				System.out.print("Something went wrong.");
				break;
		}
	}
	
	//printBar method
	public static void printBar(int n) {
		for (int i = 0; i < n; i ++) {
			System.out.print("*");
		}
	}
	
	//reused getColumn method
	public static String getColumn(int index, String row) {
		int column = 0;
		int breakout = 0;
		Scanner lineScan = new Scanner(row);
		lineScan.useDelimiter(",");
		while(lineScan.hasNext()) {
			column++;
			if (column == index) {
				breakout++;
				String returnValue = lineScan.next();
				lineScan.close();
				return returnValue;
			} else {
				lineScan.next();
			}
		}
		if (breakout == 0) {
			lineScan.close();
			return ("Column was not found.");
		} else {
			lineScan.close();
			return ("Something went completely wrong");
		}
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//collecting user input (date and state)
		System.out.print("Enter a date (YYYY-MM-DD): ");
		Scanner information=new Scanner(System.in);
		String date = information.nextLine();
		information.close();
		
		//setting up int array with 12 elements
		int[] cases = new int[12];
		
		//setting up scanner and setting values into array
		File csv = new File("all-states-history-new.csv");
		Scanner file = new Scanner(csv);
		while(file.hasNextLine()) {
			String nextline = file.nextLine();
			//incrementing respective element if positiveIncrease is in the range
			if (nextline.contains(date)) {
				int positiveIncrease = Integer.parseInt(getColumn(22, nextline));
				if (positiveIncrease > 5000) {
					cases[11]++;
				} else if (positiveIncrease > 4500) {
					cases[10]++;
				} else if (positiveIncrease > 4000) {
					cases[9]++;
				} else if (positiveIncrease > 3500) {
					cases[8]++;
				} else if (positiveIncrease > 3000) {
					cases[7]++;
				} else if (positiveIncrease > 2500) {
					cases[6]++;
				} else if (positiveIncrease > 2000) {
					cases[5]++;
				} else if (positiveIncrease > 1500) {
					cases[4]++;
				} else if (positiveIncrease > 1000) {
					cases[3]++;
				} else if (positiveIncrease > 500) {
					cases[2]++;
				} else if (positiveIncrease > 0) {
					cases[1]++;
				} else {
					cases[0]++;
				}
			}
			
		}
		file.close();

		//simple for loop to print everything out :)
		for(int i = 0; i < 12; i++) {
			printLabel(i);
			printBar (cases[i]);
			System.out.println("(" + cases[i] + ")"); //EXTRA CREDIT PART
		}
		
	}
	
}
