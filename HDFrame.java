import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HDFrame extends JFrame
{
    
    public HDFrame() {
        this.setLayout(new GridLayout(1,2));
        
        JPanel panel0 = new JPanel();
        
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
        HD.setText(String.valueOf(1));
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
        JScrollPane menuScroll = new JScrollPane(stations);
        panel2.add(menuScroll);
        
        JPanel panel3 = new JPanel(new GridLayout(2,2));
        JLabel compare = new JLabel("Compare With: ");
        String[] stationIDS = new MesoEqual("FAIR").getStationIDS();
        JComboBox<String> compareID = new JComboBox<String>(stationIDS);
        compareID.setSelectedItem(stationIDS[0]);
        
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
        
        JLabel gif = new JLabel(new ImageIcon("giphy.gif")); //success picture for freepanel
        gif.setVisible(false);
        JLabel snap = new JLabel(new ImageIcon("snap.gif")); // snap picture for freepanel
        snap.setVisible(false);
        
        JTextField stationAdd = new JTextField();
        stationAdd.setText("ZERO");
        calc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
             
                snap.setVisible(true);
                Timer timer = new Timer(1500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        snap.setVisible(false);
                        
                        dis0TF.setText(String.valueOf(new MesoEqual("FAIR").calcStations(0, String.valueOf(compareID.getSelectedItem())).size()));
                        dis1TF.setText(String.valueOf(new MesoEqual("FAIR").calcStations(1, String.valueOf(compareID.getSelectedItem())).size()));
                        dis2TF.setText(String.valueOf(new MesoEqual("FAIR").calcStations(2, String.valueOf(compareID.getSelectedItem())).size()));
                        dis3TF.setText(String.valueOf(new MesoEqual("FAIR").calcStations(3, String.valueOf(compareID.getSelectedItem())).size()));
                        dis4TF.setText(String.valueOf(new MesoEqual("FAIR").calcStations(4, String.valueOf(compareID.getSelectedItem())).size()));
                        
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
            
        });
        
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<String> old =  new ArrayList(Arrays.asList(new MesoEqual("FAIR").getStationIDS()));
                old.add(stationAdd.getText());
                
                try (PrintWriter output = new PrintWriter("Mesonet.txt")){          
                    for(String station: old) {
                        if(station.equals(old.get(old.size() - 1))) {
                            output.write(station);
                        }else {
                        output.write(station + "\n");
                        }
                    }
                    compareID.addItem(stationAdd.getText());
                } catch (IOException excp) {
                    excp.printStackTrace();
                } 
            }
            
        });
        
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
        
    
        show.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
               stations.setListData(new MesoEqual("FAIR").calcStations(Integer.parseInt(HD.getText()), String.valueOf(compareID.getSelectedItem())).toArray());
               

               gif.setVisible(true);
               Timer timer = new Timer(1500, new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent ae) {
                       gif.setVisible(false);
                   }
               });
               timer.setRepeats(false);
               timer.start();
            }
            
        });
        
        panel0.setLayout(new GridLayout(4,1));
        panel0.setPreferredSize(new Dimension(300, 1000));
        JPanel freePanel = new JPanel();
        freePanel.setPreferredSize(new Dimension(500, 1000));
        freePanel.setLayout(new BorderLayout());
        
        panel0.add(panel1);
        panel0.add(panel2);
        panel0.add(panel3);
        panel0.add(panel4);
        this.add(panel0);
        
        freePanel.add(new JLabel("Use Program for GIFS"), BorderLayout.NORTH);
        freePanel.add(gif, BorderLayout.CENTER);
        freePanel.add(snap, BorderLayout.SOUTH);
        this.add(freePanel);
        
    setSize(new Dimension(800, 1000));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        new HDFrame();
        
    }

}
