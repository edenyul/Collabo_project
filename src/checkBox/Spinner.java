package checkBox;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
 
public class Spinner {
	private static JTable table;
 
    public static void main(String[] args) {
        String[] str= {"2","3","4","5"};
    	DefaultTableModel mo=new DefaultTableModel(str, 0);
        JFrame  frame=new JFrame("Spinner");
        
        Container contain=frame.getContentPane();
        contain.setLayout(null);
        
        SpinnerNumberModel step=new SpinnerNumberModel(1, 1, 10, 1);
        
        JSpinner spinner=new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        
        contain.add(spinner);
        
        spinner.setBounds(414, 266, 80, 30);
        
        JPanel panel = new JPanel();
        panel.setBounds(78, 49, 413, 207);
        frame.getContentPane().add(panel);
        
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		for(int i=0; i<5; i++) {
        			if(table.isRowSelected(i)) {
        				
        				System.out.println(str);
        			}
        		}
        	}
        });
        table.setModel(mo);
        panel.add(table);
        
        
      
        
        Object[] o=new Object[5];
        o[0]= "a";
        o[1]= "v";
        o[2]= "b";
      
    	mo.addRow(o);
    	mo.addRow(o);
    	mo.addRow(o);
    	mo.addRow(o);
    	mo.addRow(o);
        
        
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            
             System.out.println(spinner.getValue());
            }
        });
        
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 700, 500);
        frame.setVisible(true);
 
    }
}
