package checkBox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Test1 extends JFrame implements ItemListener{

	private JPanel contentPane;
	static JCheckBox[] ch;
	private JTextField te;
	private int sum;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test1 frame = new Test1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 1, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\uC22B\uC790 \uD074\uB9AD \uD074\uB9AD");
		panel_1.add(label);
		
		te = new JTextField();
		panel_1.add(te);
		te.setColumns(15);
		
		ch=new JCheckBox[6];
		for(int i=0; i<ch.length; i++) {
			ch[i]=new JCheckBox();
			ch[i].setText((i+1)+"");
			ch[i].addItemListener(this);
			
			panel.add(ch[i]);
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox check=(JCheckBox) e.getSource();
		
			if(check.isSelected()) {		
				te.setText(te.getText()+check.getText());
				sum+=1;
				if(sum>3) {
					for(int i=0; i<ch.length; i++) {
						if(!ch[i].isSelected())
							ch[i].setEnabled(false);
					}
				}
			}else {
				sum-=1;
				te.setText("");
				for(int i=0; i<ch.length; i++) {
					if(ch[i].isSelected()) {
					te.setText(te.getText()+ch[i].getText());
				}
				if(sum<4) {
					if(!ch[i].isSelected())
						ch[i].setEnabled(true);
				}
			}
		
		
			}
		
	}
	

}
