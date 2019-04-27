import java.awt.Dimension;

import javax.swing.JFrame;

public class HDFrame extends JFrame
{
    public HDFrame() {
    setSize(new Dimension(500, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
    }
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        new HDFrame();
        
        
        String StID = "FAIR";
        /*
        MesoAscii Asciiavg = new MesoAscii(new MesoStation(StID));
        int AsciiAvg = Asciiavg.calAverage();
        
        HashMap<String, Integer> AsciiVal = new HashMap<String, Integer>();
        AsciiVal.put(StID, AsciiAvg);
        System.out.print("ASCII average: ");
        System.out.println(AsciiVal.get(StID));
        */
        MesoEqual mesoequal = new MesoEqual(StID);
        System.out.println("Stations are: "+ mesoequal.calAsciiEqual());
    }

}
