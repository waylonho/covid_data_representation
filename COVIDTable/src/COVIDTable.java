import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class COVIDTable {
	
	public static String getDate() {
		System.out.print("Enter a date (YYYY-MM-DD): ");
		Scanner information=new Scanner(System.in);
		String date = information.nextLine();
		information.close();
		return date;
	}
	
	public static void getData(String date, String[][] table) throws FileNotFoundException {
		File csv = new File("all-states-history-new.csv");
		Scanner file = new Scanner(csv);
		int row = 0;
		while(file.hasNextLine()) {
			String nextline = file.nextLine();
			if (nextline.contains(date)) {
				table[row][0] = getStateName(getColumn(2, nextline));
				table[row][1] = getColumn(22, nextline);
				table[row][2] = getColumn(10, nextline);
				table[row][3] = getColumn(5, nextline);
				row++;
			}
		}
		file.close();
	}
	
	public static void printTable(String[][] table) {
		System.out.printf("%-21s%-21s%-21s%-21s\n", "State", "Cases", "Hosp", "Deaths");
		for(int i = 0; i < 56; i++) {
			for(int k = 0; k < 4; k++) {
				System.out.printf("%-21s", table[i][k]);
			}
			System.out.println();
		}
	}
	
	//EXTRA CREDIT THAT CHANGES STATE ABBREVIATION TO FULL NAME
	public static String getStateName(String abbreviation) {
		abbreviation.toUpperCase();
		switch (abbreviation) {
			case "CA":
				return "California";
			case "HI":
				return "Hawaii";
			case "OR":
				return "Oregon";
			case "WA":
				return "Washington";
			case "AL":
				return "Alabama";
			case "AK":
				return "Alaska";
			case "AZ":
				return "Arizona";
			case "AR":
				return "Arkansas";
			case "CO":
				return "Colorado";
			case "CT":
				return "Connecticut";
			case "DE":
				return "Delaware";
			case "FL":
				return "Florida";
			case "GA":
				return "Georgia";
			case "ID":
				return "Idaho";
			case "IL":
				return "Illinois";
			case "IN":
				return "Indiana";
			case "IA":
				return "Iowa";
			case "KS":
				return "Kansas";
			case "KY":
				return "Kentucky";
			case "LA":
				return "Louisiana";
			case "ME":
				return "Maine";
			case "MD":
				return "Maryland";
			case "MA":
				return "Massachusetts";
			case "MI":
				return "Michigan";
			case "MN":
				return "Minnesota";
			case "MS":
				return "Mississippi";
			case "MO":
				return "Missouri";
			case "MT":
				return "Montana";
			case "NE":
				return "Nebraska";
			case "NV":
				return "Nevada";
			case "NH":
				return "New Hampshire";
			case "NJ":
				return "New Jersey";
			case "NM":
				return "New Mexico";
			case "NY":
				return "New York";
			case "NC":
				return "North Carolina";
			case "ND":
				return "North Dakota";
			case "OH":
				return "Ohio";
			case "OK":
				return "Oklahoma";
			case "PA":
				return "Pennsylvania";
			case "RI":
				return "Rhode Island";
			case "SC":
				return "South Carolina";
			case "SD":
				return "South Dakota";
			case "TN":
				return "Tennessee";
			case "TX":
				return "Texas";
			case "UT":
				return "Utah";
			case "VT":
				return "Vermont";
			case "VA":
				return "Virginia";
			case "WV":
				return "West Virginia";
			case "WI":
				return "Wisconsin";
			case "WY":
				return "Wyoming";
			case "DC":
				return "District of Columbia";
			case "AS":
				return "American Samoa";
			case "GU":
				return "Guam";
			case "MP":
				return "Northern Mariana";
			case "PR":
				return "Puerto Rico";
			case "VI":
				return "Virgin Islands";
			default:
				return "No string found";
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
		String[][] table = new String [56][4];
		String date = getDate();
		getData(date, table);
		printTable(table);
	}

}
