import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
public class DeleteFrame extends JFrame{
    BillsPanel billsPanel;
    JLabel temp;
    JTextField del;
    JButton jButton;
    public DeleteFrame(BillsPanel billsPanel){
        super("刪除欄位");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DeleteFrame.this.dispose();
                billsPanel.addButton.setEnabled(true);
                billsPanel.editButton.setEnabled(true);
                billsPanel.delButton.setEnabled(true);
            }
        });

        this.billsPanel = billsPanel;
        setLayout(new GridLayout(3,1));
        setSize(400,200);

        temp = new JLabel("請選擇刪除第幾筆資料");
        del = new JTextField("請選擇刪除第幾筆資料");
        jButton = new JButton("確認");
        add(temp);
        add(del);
        add(jButton);
        setVisible(true);

        ButtonHandler buttonHandler = new ButtonHandler();
        jButton.addMouseListener(buttonHandler);
    }

    public void openFile(){
        try{
            billsPanel.input = new Scanner(Paths.get(billsPanel.fileName));
        }
        catch (IOException ioException){
            System.out.println(ioException);
        }
    }
    public void readRecords(){
        try{
            while(billsPanel.input.hasNextLine()){
                billsPanel.typeList.add(billsPanel.input.next());
                billsPanel.costList.add(billsPanel.input.nextInt());
            }
        }
        catch (NoSuchElementException noSuchElementException){

        }
        catch (IllegalStateException illegalStateException){
            System.out.println(illegalStateException);
        }
    }
    public void closeFile(){
        if(billsPanel.input != null){
            billsPanel.input.close();
        }
    }

    public void reopenFile(){

        try{
            billsPanel.output = new Formatter(billsPanel.fileName);
        }
        catch (SecurityException securityException){
            System.exit(1);
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println(fileNotFoundException);
        }
    }
    public void readdRecords(){
        try{
            for(int i = 0; i < billsPanel.costList.size(); i++){
                billsPanel.output.format("%s %d\n",billsPanel.typeList.get(i),billsPanel.costList.get(i));
            }
        }
        catch (FormatterClosedException formatterClosedException){
            System.out.println(formatterClosedException);
        }
    }
    public void recloseFile(){
        if(billsPanel.output != null){
            billsPanel.output.close();
        }
    }
    private class ButtonHandler extends MouseAdapter{
        int num;
        public void mouseClicked(MouseEvent e){
            billsPanel.costList.clear();
            billsPanel.typeList.clear();
            openFile();
            readRecords();
            closeFile();
            try{
                num = Integer.parseInt(del.getText()) - 1;
                billsPanel.typeList.remove(num);
                billsPanel.costList.remove(num);
                reopenFile();
                readdRecords();
                recloseFile();
                DeleteFrame.this.dispose();
                billsPanel.addButton.setEnabled(true);
                billsPanel.editButton.setEnabled(true);
                billsPanel.delButton.setEnabled(true);
            }
            catch (NumberFormatException numberFormatException){
                del.setText("請重新輸入欲刪除數字");
            }
        }
    }
}
