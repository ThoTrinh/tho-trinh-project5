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
    
}