import javax.swing.table.AbstractTableModel;
import java.util.Calendar;
import java.util.Date;

public class DataCaculator extends AbstractTableModel {
    private Calendar calendar=Calendar.getInstance();
    private final String[] title = {"日","一","二","三","四","五","六"};
    private Object date[][];
    public DataCaculator(){
       calendar.setTime(new Date());
       calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
       makeCalendar();
    }

    public DataCaculator(int year,int month){
        calendar.set(year,month-1,1);
        makeCalendar();
    }

    private void makeCalendar(){
        int index=calendar.get(Calendar.DAY_OF_WEEK)-1;
        int dayInMonth;
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,0);
        dayInMonth=calendar.get(Calendar.DAY_OF_MONTH);
        if((index>=5&&dayInMonth>=31)||(index>=6&&dayInMonth>=30))
            date=new Object[6][7];
        else
            date=new Object[5][7];
        int day=1;
        for(int i=index;i<7;i++){
            date[0][i]=day++;
        }
        for(int i=1;i<6;i++){
            for(int j=0;j<7;j++){
                if(day>dayInMonth){
                    i=6;
                    break;
                }
                date[i][j]=day++;
            }
        }

    }

    public void setTime(int year, int month){
        calendar.set(year,month,1);
        makeCalendar();
    }

    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }

    public int getMonth(){
        return calendar.get(Calendar.MONTH);
    }

    @Override
    public String getColumnName(int column){
        return title[column];
    }

    @Override
    public int getRowCount() {
        return date.length;
    }

    @Override
    public int getColumnCount() {
        return title.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return date[rowIndex][columnIndex];
    }
}
