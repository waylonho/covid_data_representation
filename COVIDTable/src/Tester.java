import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class Tester {
	
	public static void mightThrow() throws FileNotFoundException{
		if(false){
			throw new FileNotFoundException();
		}
	}
	public static HashMap<String,String> map(String[] init){
		HashMap<String,String> ret=new HashMap<String,String>();
		for(int i=0;i<init.length;i+=2){
			ret.put(init[i],init[i+1]);
		}
		return ret;
	}
	public static int count(String in,String find){
		int i=in.indexOf(find);
		int count=0;

		while(i>=0){
			count++;
			i=in.indexOf(find,i+1);
		}
		
		return count;
	}

	public static String abrev(String abv){
		HashMap<String,String> name=map(new String[]{"CA","California","WA","Washington","HI","Hawaii","OR","Oregon","AL","Alabama","AK","Alaska","AZ","Arizona","AR","Arkansas","CO","Colorado","CT","Connecticut","DE","Delaware","FL","Florida","GA","Georgia","ID","Idaho","IL","Illinois","IN","Indiana","IA","Iowa","KS","Kansas","KY","Kentucky","LA","Louisiana","ME","Maine","MD","Maryland","MA","Massachusetts","MI","Michigan","MN","Minnesota","MS","Mississippi","MO","Missouri","MT","Montana","NE","Nebraska","NV","Nevada","NH","New Hampshire","NJ","New Jersey","NM","New Mexico","NY","New York","NC","North Carolina","ND","North Dakota","OH","Ohio","OK","Oklahoma","PA","Pennsylvania","RI","Rhode Island","SC","South Carolina","SD","South Dakota","TN","Tennessee","TX","Texas","UT","Utah","VT","Vermont","VA","Virginia","WV","West Virginia","WI","Wisconsin","WY","Wyoming","DC","District of Columbia","GU","Guam","MH","Marshall Islands","MP","Northern Mariana Island","PR","Puerto Rico","VI","Virgin Islands"});
		String ret=name.get(abv.toUpperCase());
		if(ret!=null){
			return ret;//name.get(abv.toUpperCase());
		}else{
			return abv;
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		int grade=50;
		
		String files="2020-08-02\n";
		System.setIn( (InputStream)new ByteArrayInputStream(files.getBytes()));
		PrintStream realOut=System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);
		int tgrade=0;
		
		baos.reset();
		realOut.println("Testing getDate()");
		String date=COVIDTable.getDate();
		if(date.equals("2020-08-02")){
			realOut.println("\tgetDate() correct");
		}else{
			realOut.println("\tgetDate() incorrect; returned \""+date+"\" instead of \"2020-08-02\"(the input date)");
		}
		realOut.println();
		
		realOut.println("Testing getData(String,String[][])");
		String[][] table = new String[56][4];
		String[][] expected={
								{"AK","136","1","0"},
								{"AL","2095","0","24"},
								{"AR","0","42","-2"},
								{"AS","0","0","0"},
								{"AZ","1465","25","18"},
								{"CA","9032","0","132"},
								{"CO","449","9","0"},
								{"CT","0","0","0"},
								{"DC","69","0","1"},
								{"DE","72","0","0"},
								{"FL","7056","180","62"},
								{"GA","3165","69","15"},
								{"GU","0","0","0"},
								{"HI","86","15","0"},
								{"IA","516","0","4"},
								{"ID","393","6","8"},
								{"IL","1467","0","14"},
								{"IN","735","67","4"},
								{"KS","0","0","0"},
								{"KY","462","0","2"},
								{"LA","3467","0","58"},
								{"MA","418","6","12"},
								{"MD","909","74","9"},
								{"ME","21","0","0"},
								{"MI","429","0","0"},
								{"MN","759","33","8"},
								{"MO","582","0","0"},
								{"MP","3","0","0"},
								{"MS","672","0","10"},
								{"MT","112","3","0"},
								{"NC","1341","0","5"},
								{"ND","57","0","2"},
								{"NE","180","7","0"},
								{"NH","0","0","0"},
								{"NJ","357","0","6"},
								{"NM","220","17","3"},
								{"NV","1131","0","0"},
								{"NY","531","0","6"},
								{"OH","944","43","14"},
								{"OK","494","13","1"},
								{"OR","325","0","3"},
								{"PA","654","0","5"},
								{"PR","292","0","5"},
								{"RI","0","0","0"},
								{"SC","1189","0","26"},
								{"SD","88","3","1"},
								{"TN","1443","32","6"},
								{"TX","6226","0","0"},
								{"UT","473","18","1"},
								{"VA","981","45","3"},
								{"VI","0","0","0"},
								{"VT","4","0","0"},
								{"WA","711","87","28"},
								{"WI","932","36","1"},
								{"WV","119","0","1"},
								{"WY","39","0","0"}
							};
		
							
		

		boolean fail=false;
		ArrayList<String> ignore=new ArrayList<String>();
		ignore.add("AS");
		ignore.add("FM");
		ignore.add("GU");
		ignore.add("MH");
		ignore.add("MP");
		ignore.add("PR");
		ignore.add("PW");
		ignore.add("VI");
		ignore.add("UM");
		try{
			COVIDTable.getData(date,table);
			mightThrow();
			int mismatchCount=0;
			for(int i=0;i<expected.length;i++){
				for(int j=0;j<expected[i].length;j++){
					if(j==0){
						//realOut.println("\""+abrev(expected[i][j])+"\" \""+table[i][j]+"\"");
						if(!expected[i][j].equalsIgnoreCase(table[i][j])) {
							if(!abrev(expected[i][j]).equalsIgnoreCase(table[i][j])) {
						
							
								if(!ignore.contains(expected[i][j])) {
									fail=true;
									realOut.println("\t\tExpected \""+expected[i][j]+"\" or \""+abrev(expected[i][j])+"\" Received \""+table[i][j]+"\"");
								}else {
									
									//realOut.println("Ignored");
								}
							
							}
							
						}

					}else{
						fail=!expected[i][j].equals(table[i][j])||fail;
					}
				}
			}
			if(fail){
				realOut.println("\tgetData did not match expected");
			}else{
				realOut.println("\tgetData works as expected");
			}
		}catch(FileNotFoundException e){
				realOut.println("\tgetDate could not find file");
		}
		
		File file=new File("all-states-history-new.csv");
		realOut.println();
		if(file.exists()){
			realOut.println("\"all-states-history-new.csv\" found");
		}else{
			realOut.println("\"all-states-history-new.csv\" not found");
		}
		
		realOut.println();
		realOut.println("Testing getColumn(int,String)");
		String col=COVIDTable.getColumn(3,"one,two,three,four,five");
		if("three".equals(col)){
				realOut.println("\tgetColumn(int,String) works as expected");
		}else{
				realOut.println("\tgetColumn(3,\"one,two,three,four,five\") returned \""+col+"\" instead of \"three\"");
		}
		
		realOut.println();
		baos.reset();
		COVIDTable.printTable(expected);
		
		String bin=baos.toString();
		Scanner scan=new Scanner(bin);
		realOut.println("Testing printTable");
		fail=false;
		for(int i=0;i<expected.length;i++){
			String line=scan.nextLine().toLowerCase();

			if(line.contains("state")){//this is a header row
				if(!(line.contains("state")&&line.contains("cases")&&line.contains("hosp")&&line.contains("deaths"))){
					realOut.println("\tHeader wrong");
					
				}
				i--;
			}else{
				String[] raw=line.split(" ");
				int f=0;
				String[] elems=new String[4];
				
				for(int j=0;j<raw.length;j++) {
					if(raw[j].equals("")) {
						continue;
					}						
					raw[f]=raw[j];
					f++;
				}

				int k=3;
				//for(int j=f-1;j>=0;j--) {
				//realOut.print(raw[j]+"|");
				//}
				//realOut.println();
				for(int j=f-1;j>=0;j--) {
					if(k!=0) {
						elems[k]=raw[j];
						k--;
					}else {
						String ml="";
						for(int h=0;h<=j;h++) {
							ml+=raw[h]+ " ";
						}
						elems[0]=ml.trim();
						break;
					}
						
						
				}
				//realOut.println(expected[i][0]+"|"+expected[i][1]+"|"+expected[i][2]+"|"+expected[i][3]+"|");
				//realOut.println(elems[0]+"|"+elems[1]+"|"+elems[2]+"|"+elems[3]+"|");
				for(int j=0;j<expected[i].length;j++){
					String elem=elems[j];
					//realOut.print(elem+" ");
					if(j==0){
						if(!expected[i][j].equalsIgnoreCase(elem)) {
							if(!abrev(expected[i][j]).equalsIgnoreCase(elem)) {
						
							
								if(!ignore.contains(expected[i][j])) {
									fail=true;
									realOut.println("\t\tExpected \""+expected[i][j]+"\" or \""+abrev(expected[i][j])+"\" Received \""+elem+"\"");
									break;
								}else {
									
									//realOut.println("Ignored");
								}
							
							}
							
						}
						}else{
						if(!expected[i][j].equals(elem)) {
							//realOut.println(expected[i][j]+"|"+elem+"|");
							//realOut.println("Unexpected output from row "+elems[0]);
							fail=true;
							//break;
							
						}
						
					}
				}
			}
			//realOut.println();
		}
		scan.close();
		if(fail){
			realOut.println("\tprintTable didnt print as expected");
		}else{
			realOut.println("\tprintTable worked");			
		}
		realOut.println();
		realOut.println("Reminder: you are not allowed to change Main for this assignment, it should be");
		realOut.println("	public static void main(String[] args) throws FileNotFoundException {\n"+
						"		String[][] table = new String[56][4];\n"+
						"		String date = getDate();\n"+
						"		getData(date,table);\n"+
						"		printTable(table);\n"+
						"	}");


		//COVIDHistogram.main(args);
		//COVIDHistogram.main(args);
		
		// TODO Auto-generated method stub
		
		
	}

}
