import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.text.*;
import java.util.Formatter;
import java.util.Scanner;
import java.util.ArrayList;

public class BillsPanel extends JPanel {
    public String[] type = {"ALL","FOOD","ENTERTAINMENT","LIVE","TRANSPORTATION"};
    public JComboBox<String> typeBox = new JComboBox<>(type);
    public JButton addButton = new JButton("add");
    public JButton editButton = new JButton("edit");
    public JButton delButton = new JButton("delete");
    public JToolBar toolBar = new JToolBar();
    public String date;
    public String dirName;
    public String fileName;
    public Scanner input;
    public Formatter output;
    public ArrayList<String> typeList = new ArrayList<>();
    public ArrayList<Integer> costList = new ArrayList<>();

    public BillsPanel(){
        setSize(new Dimension(300,600));
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd");
        date = ft.format(dNow);

        dirName = "C:\\Store\\" + date;
        File dir = new File(dirName);
        if(!dir.exists()){
            dir.mkdir();
        }

        fileName = dirName + "\\" + "dayCost.txt";

        ABHandler abHandler = new ABHandler(); //addButton事件處理
        addButton.addActionListener(abHandler);

        EBHandler ebHandler = new EBHandler();
        editButton.addActionListener(ebHandler);

        DBHandle dbHandle = new DBHandle();
        delButton.addActionListener(dbHandle);

        toolBar.add(typeBox);
        toolBar.add(addButton);
        toolBar.add(editButton);
        toolBar.add(delButton);
        toolBar.setFloatable(false);
        toolBar.setSize(300,60);

        add(toolBar,BorderLayout.NORTH);
    }

    private class ABHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            AddFrame addFrame = new AddFrame(BillsPanel.this);
            addButton.setEnabled(false);
            editButton.setEnabled(false);
            delButton.setEnabled(false);
        }
    }
    private class EBHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            EditFrame editFrame = new EditFrame(BillsPanel.this);
            addButton.setEnabled(false);
            editButton.setEnabled(false);
            delButton.setEnabled(false);
        }
    }

    private class DBHandle implements ActionListener{
        public void actionPerformed(ActionEvent e){
            DeleteFrame deleteFrame = new DeleteFrame(BillsPanel.this);
            addButton.setEnabled(false);
            editButton.setEnabled(false);
            delButton.setEnabled(false);
        }
    }
}
