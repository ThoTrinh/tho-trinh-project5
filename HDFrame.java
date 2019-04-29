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
    private JPanel panel0 = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel(new GridLayout(2,0));
    private JPanel panel3 = new JPanel(new GridLayout(2,2));
    private  JPanel panel4 = new JPanel(new GridLayout(6, 2));
    private JPanel freePanel = new JPanel();
    
    public HDFrame() {
        this.setLayout(new GridLayout(1,2));
        
        /**
         * JComponents used in panel1
         */
        JTextField HD = new JTextField();
        JLabel EHD = new JLabel("Enter Hamming Distance: ");
        JButton show = new JButton("Show Button");
        JSlider hdSlider = new JSlider(1,4,1);
        
        panel1Setup(HD, EHD, show, hdSlider);
        
        /**
         * Jcomponenets used in pnael2
         */
        JList stations = new JList(new MesoEqual("FAIR").calAsciiEqual().toArray());
        JScrollPane menuScroll = new JScrollPane(stations);
        panel2Setup(menuScroll);
        
        /**
         * JComponents used in panel3
         */
        JLabel compare = new JLabel("Compare With: ");
        String[] stationIDS = new MesoEqual("FAIR").getStationIDS();
        JComboBox<String> compareID = new JComboBox<String>(stationIDS);
        compareID.setSelectedItem(stationIDS[0]);
        
        JButton calc = new JButton("Calculate HD");
        panel3setup(compare, compareID, calc);
        
       /**
        * JComponents used in Panel4
        */
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
        
        panel4Setup(distance0, dis0TF, distance1, dis1TF, distance2, dis2TF, distance3, dis3TF, distance4, dis4TF, add,
                stationAdd);
        
        /**
         * JComponents used in freePanel
         */
        JLabel gif = new JLabel(new ImageIcon("giphy.gif")); //success picture for freepanel
        JLabel snap = new JLabel(new ImageIcon("snap.gif")); // snap picture for freepanel
        freePanelSetup(gif, snap);
        
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
        
        panel0Setup();
        
        this.add(panel0);
        this.add(freePanel);
        
        setSize(new Dimension(800, 1000));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Panel 0 setup
     * This is the left panel for the HDFrame()
     */
    private void panel0Setup()
    {
        panel0.setLayout(new GridLayout(4,1));
        panel0.setPreferredSize(new Dimension(300, 1000));
        
        panel0.add(panel1);
        panel0.add(panel2);
        panel0.add(panel3);
        panel0.add(panel4);
    }

    /**
     * FreePanel setup
     * This is the right panel for the HDFrame()
     * 
     * @param gif - gif of loki screaming success, it will play when you press the show button
     * @param snap - gif of thanos snapping his fingers, it will play when you press the calculate HD button
     */
    private void freePanelSetup(JLabel gif, JLabel snap)
    {
        freePanel.setPreferredSize(new Dimension(500, 1000));
        freePanel.setLayout(new BorderLayout());
        
        gif.setVisible(false);
        
        snap.setVisible(false);
        
        freePanel.add(new JLabel("Use Program for GIFS"), BorderLayout.NORTH);
        freePanel.add(gif, BorderLayout.CENTER);
        freePanel.add(snap, BorderLayout.SOUTH);
        
    }

    private void panel3setup(JLabel compare, JComboBox<String> compareID, JButton calc)
    {
        panel3.add(compare);
        panel3.add(compareID);
        panel3.add(calc);
    }

    private void panel2Setup(JScrollPane menuScroll)
    {
        panel2.add(menuScroll);
    }

    private void panel4Setup(JLabel distance0, JTextField dis0TF, JLabel distance1, JTextField dis1TF, JLabel distance2,
            JTextField dis2TF, JLabel distance3, JTextField dis3TF, JLabel distance4, JTextField dis4TF, JButton add,
            JTextField stationAdd)
    {
        stationAdd.setText("ZERO");
        
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
    }

    private void panel1Setup(JTextField HD, JLabel EHD, JButton show, JSlider hdSlider)
    {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        panel1.setLayout(gridbag);
        
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        
        gridbag.setConstraints(EHD, c);
        panel1.add(EHD);
        
        HD.setText(String.valueOf(1));
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        gridbag.setConstraints(HD, c);
        panel1.add(HD);
        
        c.weightx = 0.0;                //reset to the default
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
        
        gridbag.setConstraints(show, c);
        panel1.add(show);
    }
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        new HDFrame();
        
    }

}
