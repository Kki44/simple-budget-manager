import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
public class AddFrame extends JFrame{
    public BillsPanel billsPanel;
    public JLabel temp;
    public JPanel jPanel;
    public JTextField cost;
    public JButton jButton;

    public static ArrayList<Integer> costNote = new ArrayList<Integer>();
    public AddFrame(BillsPanel billsPanel) throws HeadlessException {
        super("輸入欄位");            //創建輸入GUI

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AddFrame.this.dispose();
                billsPanel.addButton.setEnabled(true);
                billsPanel.editButton.setEnabled(true);
                billsPanel.delButton.setEnabled(true);
            }
        });

        this.billsPanel = billsPanel;
        setSize(400,200);
        setLayout(new GridLayout(3,1));
        temp = new JLabel("請輸入花費");
        add(temp);
        cost = new JTextField("花費");
        jButton = new JButton("確認");
        add(cost);
        add(jButton);
        setVisible(true);

        ButtonHandler buttonHandler = new ButtonHandler();
        jButton.addMouseListener(buttonHandler);
    }

    public void openFile(){

        try{
            FileWriter fw = new FileWriter(billsPanel.fileName,true);
            billsPanel.output = new Formatter(fw);
        }
        catch (SecurityException securityException){
            System.exit(1);
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println(fileNotFoundException);
        }
        catch (IOException ioException){
            System.out.println(ioException);
        }
    }
    public void addRecords(){
        int num = Integer.parseInt(cost.getText());
        try{
            billsPanel.output.format("%s %d\n",billsPanel.typeBox.getItemAt(billsPanel.typeBox.getSelectedIndex()), num);
        }
        catch (FormatterClosedException formatterClosedException){
            System.out.println(formatterClosedException);
        }
    }
    public void closeFile(){
        if(billsPanel.output != null){
            billsPanel.output.close();
        }
    }
    private class ButtonHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e){
            File file = new File(billsPanel.fileName);
            try {
                Integer.parseInt(cost.getText());    //檢查是否為整數
                if (file.createNewFile()) {
                    System.out.println("File created:");
                }
                else {
                    System.out.println("File already exists");
                }
                openFile();
                addRecords();
                closeFile();
                AddFrame.this.dispose();
                billsPanel.addButton.setEnabled(true);
                billsPanel.editButton.setEnabled(true);
                billsPanel.delButton.setEnabled(true);
            }
            catch (IOException ioException){
                System.out.println(ioException);
            }
            catch (NumberFormatException numberFormatException){
                cost.setText("請重新輸入花費");
            }
        }
    }
}
