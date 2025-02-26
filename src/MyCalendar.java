import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyCalendar extends JPanel implements ActionListener {
    private static DataCaculator date=new DataCaculator();
    private JTable calendar;
    private JButton buttonLeft=new JButton();
    private JButton buttonRight=new JButton();
    private JLabel title=new JLabel();

    private GridBagLayout gridBagLayout=new GridBagLayout();
    private GridBagConstraints c=new GridBagConstraints();
    private String dateText="ERROR";

    public MyCalendar(){
        this(date.getYear(), date.getMonth());
    }

    public MyCalendar(int year, int month){
        super();
        date.setTime(year,month);
        init();
    }

    private void init(){

        dateText=String.format("%d年%d月\n",date.getYear(),date.getMonth()+1);
        title.setText(dateText);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.BOTTOM);

        calendar=new JTable(date);

        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        calendar.setDefaultRenderer(Object.class,r);
        calendar.setBackground(Color.darkGray);
        calendar.setForeground(Color.WHITE);
        calendar.setRowHeight(25);


        this.setBounds(0,0,300,200);
        this.setLayout(gridBagLayout);

        this.setBackground(Color.darkGray);

        buttonLeft.addActionListener(this);
        buttonRight.addActionListener(this);

        c.fill=GridBagConstraints.BOTH;

        c.weightx=0;
        c.weighty=1;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=2;
        gridBagLayout.setConstraints(buttonLeft,c);

        c.gridx=1;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=2;
        gridBagLayout.setConstraints(buttonRight,c);

        c.weightx=1;
        c.gridx=2;
        c.gridy=0;
        c.gridwidth=6;
        c.gridheight=2;
        gridBagLayout.setConstraints(title,c);

        c.gridx=0;
        c.gridy=2;
        c.gridwidth=8;
        c.gridheight=2;
        gridBagLayout.setConstraints(calendar.getTableHeader(),c);

        c.gridx=0;
        c.gridy=4;
        c.gridwidth=8;
        c.gridheight=6;
        gridBagLayout.setConstraints(calendar,c);




        this.add(buttonLeft);
        this.add(buttonRight);
        this.add(title);
        this.add(calendar.getTableHeader());
        this.add(calendar);
    }

    private void changeDate(){
        this.remove(calendar);
        dateText=String.format("%d年%d月\n",date.getYear(),date.getMonth()+1);
        title.setText(dateText);
        calendar.setModel(date);
        this.add(calendar,c);
        this.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(350,200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonLeft){
            date.setTime(date.getYear(), date.getMonth()-1);
            changeDate();
        }
        else if(e.getSource()==buttonRight){
            date.setTime(date.getYear(), date.getMonth()+1);
            changeDate();
        }
    }

}
