import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HDFrame extends JFrame
{
    
    public HDFrame() {
        
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(gridbag);
        
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        JLabel EHD = new JLabel("Enter Hamming Distance: ");
        
        gridbag.setConstraints(EHD, c);
        panel1.add(EHD);
        JTextField HD = new JTextField();
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        gridbag.setConstraints(HD, c);
        panel1.add(HD);
        panel1.setSize(300, 100);
        
        c.weightx = 0.0;                //reset to the default
        JSlider hdSlider = new JSlider(1,4,1);
        hdSlider.setMajorTickSpacing(1);
        hdSlider.setPaintTicks(true);
        hdSlider.setPaintLabels(true);
        hdSlider.setLabelTable(hdSlider.createStandardLabels(1));
        gridbag.setConstraints(hdSlider, c);
        panel1.add(hdSlider);
        
    this.add(panel1);
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
        MesoEqual mesoequal = new MesoEqual(StID);
        System.out.println("Stations are: "+ mesoequal.calAsciiEqual());
    }

}
