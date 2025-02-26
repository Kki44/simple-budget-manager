import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class EditFrame extends JFrame{
    public BillsPanel billsPanel;
    public JLabel temp;
    public JTextField choose;
    public JTextField type;
    public JTextField money;
    public JButton jButton;

    public EditFrame(BillsPanel billsPanel) throws HeadlessException{
        super("編輯欄位");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EditFrame.this.dispose();
                billsPanel.addButton.setEnabled(true);
                billsPanel.editButton.setEnabled(true);
                billsPanel.delButton.setEnabled(true);
            }
        });

        this.billsPanel = billsPanel;
        setLayout(new GridLayout(5,1));
        setSize(400,200);

        temp = new JLabel("請輸入要修改的資料");
        add(temp);
        choose = new JTextField("第幾筆資料");
        type = new JTextField("修改型態為");
        money = new JTextField("修改金額為");
        jButton = new JButton("確認");
        add(choose);
        add(type);
        add(money);
        add(jButton);
        setVisible(true);

        ButtonHander buttonHander = new ButtonHander();
        jButton.addMouseListener(buttonHander);
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
    private class ButtonHander extends MouseAdapter{
        int num;
        String restr;
        int remon;
        public void mouseClicked(MouseEvent e){
            billsPanel.costList.clear();
            billsPanel.typeList.clear();
            openFile();
            readRecords();
            closeFile();
            try{
                num = Integer.parseInt(choose.getText()) - 1;
                restr = type.getText();
                remon = Integer.parseInt(money.getText());
                billsPanel.typeList.set(num , restr);
                billsPanel.costList.set(num , remon);
                reopenFile();
                readdRecords();
                recloseFile();
                EditFrame.this.dispose();
                billsPanel.addButton.setEnabled(true);
                billsPanel.editButton.setEnabled(true);
                billsPanel.delButton.setEnabled(true);
            }
            catch (NumberFormatException numberFormatException){
                choose.setText("請重新輸入第幾筆資料");
                type.setText("請重新輸入修改型態");
                money.setText("請重新輸入修改金額");
            }
        }
    }
}
