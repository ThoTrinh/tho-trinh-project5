import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class HammingDist {
	
	private String fromStationID;
	private String toStationID;
	private ArrayList<String> stationIDS = new ArrayList<String>(); //Array used to hold all station ID's in file
	

public HammingDist(String fromStationID, String toStationID)   //Constructor
{
		this.fromStationID = fromStationID;
		this.toStationID = toStationID;
		
		try
    	{
    		readFile("Mesonet.txt"); //adds all station ID's to array
    	}
    	catch(IOException e)
    	{
    		System.out.println("Error reading from file!\n");
    		e.printStackTrace();
    	}
}
	
	//Write method to read File and store into array
	
public void readFile(String filename) throws IOException //reads Mesonet.txt
{ 
		Scanner input = new Scanner(new File(filename));
		
		String strg;
		//First two lines are not needed in program
		strg = input.nextLine();
		strg = input.nextLine();
		strg = input.nextLine();
	
		
		while (input.hasNextLine()) {
			String[] words = strg.split(" "); //Splits the Line into individual data
			stationIDS.add(words[1]); // Only adds the Station ID which is first entry of data
			
			strg = input.nextLine();
		}
		input.close();
		
	}

public int compareID(String fromStationID, String toStationID) { //compares two station ID's
	int wrongCharCount = 0; //keeps track of Hamming Distance
	
	if (fromStationID.length()==4 && toStationID.length() == 4) { //checks to see if argument passed is valid
		char[] firstID = fromStationID.toCharArray(); // Splits the two ID's into character arrays
		char[] secondID = toStationID.toCharArray();
		
		for(int i = 0; i < 4; i++) { //compares the array
			if(firstID[i]!=secondID[i]) {
				wrongCharCount++; //increments if one is not the same
			}
		}
	}
	
	return wrongCharCount;
}

public int compareToAllID(String fromStationID, int distanceWanted) { //counts how many stations with some hamming distance quanitity with a station ID
	int wrongCharCount = 0; //keeps track of hamming distance
	
	int distanceCounter = 0; //keeps track of number of stations
	for(int i = 0; i < stationIDS.size() ; i++) { //iterates through list of station ID's
		wrongCharCount = compareID(fromStationID, stationIDS.get(i)) ; 
		if(wrongCharCount == distanceWanted) { 
			distanceCounter++; 
		}
	}
	return distanceCounter;
}

@Override
public String toString() { //converts a HammingDist object to words
	int betweenTwo = compareID(fromStationID, toStationID);
	
	return String.format("The Hamming Distance of %s and %s: %d.\nOut of 119, for %s, " +
			"number of nodes are: %d, %d, %d, %d and \nfor %s, number of nodes are: %d, %d, %d, %d respectively.", 
			fromStationID, toStationID, compareID(fromStationID, toStationID), 
			fromStationID, compareToAllID(fromStationID, 1), compareToAllID(fromStationID, 2), 
			compareToAllID(fromStationID, 3), compareToAllID(fromStationID, 4), 
			toStationID, compareToAllID(toStationID, 1), compareToAllID(toStationID, 2), 
			compareToAllID(toStationID, 3), compareToAllID(toStationID, 4)); 
}




	
	
}