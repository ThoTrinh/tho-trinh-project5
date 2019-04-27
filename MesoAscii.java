/*
 * @author Tho Trinh
 * @version 03-22-2019
 * 
 * This class extends from another class
 * very similar to aspects from Project 1 and 2
 * 
 */
public class MesoAscii {
    
    private char[] wordChars; //character array that stores the stationd ID characters
    
    /*
     * Constructor intialzies 
     */
    public MesoAscii(String STID) {
        wordChars = STID.toCharArray();
    }
    
    /*
     * (non-Javadoc)
     * @see MesoAsciiAbstract#calAverage()
     * 
     * Calculates average of station ID
     */
    public int calAverage()
    {
        double sum = 0;
        
        for(char letter: wordChars) {
            sum += getAscii(letter);
        }
        
        if((sum / (double) wordChars.length) % 1 >= .50) {
            return (int) Math.ceil(sum / wordChars.length); //Assigns Ascii Average
        }else {
            return (int) Math.floor(sum / wordChars.length);//Assigns Ascii Average
        }
    }
    
  //Gets the Ascii Value of an individual letter
    public int getAscii(char letter) {
        
        return (int)letter;
    }
    
}