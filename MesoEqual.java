import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * @author Tho Trinh
 * @version 03-22-2019
 * 
 * method that calculates the average of all of the station ID's in Mesonet.txt file
 * It then prints out some information
 */
public class MesoEqual {
    
    private String stationID;
    private ArrayList<String> stationIDS = new ArrayList<String>(); //Array used to hold all station ID's in file
    
    /**
     * 
     * @param stationID - name of station ID
     */
    public MesoEqual(String stationID) {
        this.stationID = stationID;
        
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
    
    /**
     * Write method to read File and store into array
     * @param filename - name of file
     * @throws IOException - possible exception
     */
   
    public void readFile(String filename) throws IOException //reads Mesonet.txt
    { 
            Scanner input = new Scanner(new File(filename));
            
            while (input.hasNextLine()) {
                stationIDS.add(input.nextLine()); // Only adds the Station ID which is first entry of data
            }
            input.close();
            
        }
    
    /**
     * @return the stationIDS
     */
    public String[] getStationIDS()
    {
        return stationIDS.toArray(new String[stationIDS.size()]);
    }
    
    /**
     * 
     * @param HammingDist - Hamming Distance number we want to find
     * @param fromStation
     * @return
     */
    public ArrayList<String> calcStations(int HammingDist, String fromStation){
        ArrayList<String> stations = new ArrayList<String>();
        
        for(String stationID: stationIDS) {
            if(HammingDist == compareID(fromStation, stationID)) {
                stations.add(stationID);
            }
        }
        
        return stations;
    }
    
    /**
     * 
     * @param fromStationID - station to compare with
     * @param toStationID - station to compare with
     * @return
     */
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
    
    /**
     * Prints out an array List like this:
     * {NRMN=79, OKMU=79, STIL=79, JAYX=79, NEWP=79, STUA=79, WATO=79, MRSH=79}
     * that have the same ascii Average as the ascii average of the stationID
     */
    public ArrayList<String> calAsciiEqual() {
        
        ArrayList<String> equalAsciis = new ArrayList<String>();
        MesoAscii Asciiavg = new MesoAscii(stationID);
        int AsciiAvg = Asciiavg.calAverage();
        
        for(String stationID: stationIDS) {
            MesoAscii AsciiAvgTest = new MesoAscii(stationID);
            int AsciiAvgNum = AsciiAvgTest.calAverage();
            
            if(AsciiAvgNum == AsciiAvg) {
                equalAsciis.add(stationID);
            }
        }
        return equalAsciis;
    }
}