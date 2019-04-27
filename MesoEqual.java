import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/*
 * @author Tho Trinh
 * @version 03-22-2019
 * 
 * method that calculates the average of all of the station ID's in Mesonet.txt file
 * It then prints out some information
 */
public class MesoEqual {
    
    private String stationID;
    private ArrayList<String> stationIDS = new ArrayList<String>(); //Array used to hold all station ID's in file
    
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
    
    //Write method to read File and store into array
   
    public void readFile(String filename) throws IOException //reads Mesonet.txt
    { 
            Scanner input = new Scanner(new File(filename));
            
            String strg;
            //First two lines are not needed in program
            strg = input.nextLine();
            
            while (input.hasNextLine()) {
                String[] words = strg.split(" "); //Splits the Line into individual data
                stationIDS.add(words[0]); // Only adds the Station ID which is first entry of data
                
                strg = input.nextLine();
            }
            input.close();
            
        }
    
    /*
     * Prints out an array List like this:
     * {NRMN=79, OKMU=79, STIL=79, JAYX=79, NEWP=79, STUA=79, WATO=79, MRSH=79}
     * that have the same ascii Average as the ascii average of the stationID
     */
    public HashMap<String, Integer> calAsciiEqual() {
        
        HashMap<String, Integer> equalAsciis = new HashMap<String, Integer>();
        MesoAscii Asciiavg = new MesoAscii(stationID);
        int AsciiAvg = Asciiavg.calAverage();
        
        for(String stationID: stationIDS) {
            MesoAscii AsciiAvgTest = new MesoAscii(stationID);
            int AsciiAvgNum = AsciiAvgTest.calAverage();
            
            if(AsciiAvgNum == AsciiAvg) {
                equalAsciis.put(stationID, AsciiAvg);
            }
        }
        return equalAsciis;
    }
}