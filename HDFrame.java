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
    }

}
