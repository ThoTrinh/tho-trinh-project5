import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HDFrame extends JFrame
{
    
    public HDFrame() {
        this.setLayout(new GridLayout(4,2));
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
        
        c.weightx = 0.0;                //reset to the default
        JSlider hdSlider = new JSlider(1,4,1);
        hdSlider.setMajorTickSpacing(1);
        hdSlider.setPaintTicks(true);
        hdSlider.setPaintLabels(true);
        hdSlider.setLabelTable(hdSlider.createStandardLabels(1));
        hdSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e)
            {
                HD.setText(String.valueOf(hdSlider.getValue()));
            }
            
        });
        gridbag.setConstraints(hdSlider, c);
        panel1.add(hdSlider);
        
        c.gridwidth = GridBagConstraints.RELATIVE;
        
        JButton show = new JButton("Show Button");
        gridbag.setConstraints(show, c);
        panel1.add(show);
        
        JPanel panel2 = new JPanel(new GridLayout(2,0));
        
        JList stations = new JList(new MesoEqual("FAIR").calAsciiEqual().toArray());
        stations.setSize(getPreferredSize());
        panel2.add(stations);
        
        JPanel panel3 = new JPanel(new GridLayout(2,2));
        JLabel compare = new JLabel("Compare With: ");
        String[] stationIDS = new MesoEqual("FAIR").getStationIDS();
        JComboBox<String> compareID = new JComboBox<String>(stationIDS);
        JButton calc = new JButton("Calculate HD");
        panel3.add(compare);
        panel3.add(compareID);
        panel3.add(calc);
        
        JPanel panel4 = new JPanel(new GridLayout(6, 2));
        JLabel distance0 = new JLabel("Distance 0");
        JTextField dis0TF = new JTextField();
        JLabel distance1 = new JLabel("Distance 1");
        JTextField dis1TF = new JTextField();
        JLabel distance2 = new JLabel("Distance 2");
        JTextField dis2TF = new JTextField();
        JLabel distance3 = new JLabel("Distance 3");
        JTextField dis3TF = new JTextField();
        JLabel distance4 = new JLabel("Distance 4");
        JTextField dis4TF = new JTextField();
        JButton add = new JButton("Add Station");
        JTextField stationAdd = new JTextField();
        
        panel4.add(distance0);
        panel4.add(dis0TF);
        panel4.add(distance1);
        panel4.add(dis1TF);
        panel4.add(distance2);
        panel4.add(dis2TF);
        panel4.add(distance3);
        panel4.add(dis3TF);
        panel4.add(distance4);
        panel4.add(dis4TF);
        panel4.add(add);
        panel4.add(stationAdd);
        
    this.add(panel1);
    this.add(panel2);
    this.add(panel3);
    this.add(panel4);
    setSize(new Dimension(600, 1000));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        new HDFrame();
        
    }

}
