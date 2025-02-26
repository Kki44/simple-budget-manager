import javax.swing.*;
import java.awt.*;

public class mainTheme extends JFrame{
    JPanel calendar = new MyCalendar();
    BillsPanel billPanel = new BillsPanel();
    JPanel graph = new JPanel();
    JPanel terminal = new JPanel();

    JPanel left=new JPanel(new GridBagLayout());
    JPanel right=new JPanel(new GridBagLayout());
    GridBagConstraints c=new GridBagConstraints();
    GridBagLayout gridBagLayout = new GridBagLayout();
    public mainTheme(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1260,800);
        //this.setVisible(true);
        //this.getContentPane().setBackground(new Color(30,30,30));
        //this.setBackground(Color.BLACK);
        c.fill=c.BOTH;

        c.gridx=0;
        c.gridy=0;
        c.weightx=0;
        c.weighty=0;
        c.gridwidth=1;
        c.gridheight=1;
        left.add(calendar,c);

        c.gridx=0;
        c.gridy=1;
        //c.anchor=GridBagConstraints.SOUTHWEST;
        c.weightx=0;
        c.weighty=1;
        c.gridwidth=1;
        c.gridheight=2;
        left.add(billPanel,c);

        c.gridx=1;
        c.gridy=0;
        c.weightx=1;
        c.weighty=1;
        c.gridwidth=4;
        c.gridheight=3;
        graph.setBackground(Color.MAGENTA);
        right.add(graph,c);

        c.gridx=1;
        c.gridy=4;
        c.weightx=1;
        c.weighty=1;
        c.gridwidth=4;
        c.gridheight=3;
        terminal.setBackground(Color.CYAN);
        right.add(terminal,c);

        this.add(left,BorderLayout.WEST);
        this.add(right);

    }
}
