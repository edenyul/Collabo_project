package checkBox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class JCheckBoxTest3 extends JFrame implements ItemListener{

	private JPanel contentPane;
	private JTextField te;
	private JCheckBox ch1, ch2, ch3, ch4, ch5, ch6;
	private int sum;
	private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCheckBoxTest3 frame = new JCheckBoxTest3();
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
	public JCheckBoxTest3() {
		setTitle("checkBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 1, 0));
		
		ch1 = new JCheckBox("\uBC15\uBCF4\uAC80");
		panel.add(ch1);
		
		ch2 = new JCheckBox("\uC870\uC778\uC131");
		panel.add(ch2);
		
		ch3 = new JCheckBox("\uC720\uD574\uC9C4");
		panel.add(ch3);
		
		ch4 = new JCheckBox("\uC1A1\uC911\uAE30");
		panel.add(ch4);
		
		ch5 = new JCheckBox("\uBC15\uD615\uC2DD");
		panel.add(ch5);
		
		ch6 = new JCheckBox("\uC9C0\uC131");
		panel.add(ch6);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("\uC88B\uC544\uD558\uB294 \uBC30\uC6B0\uB294?");
		panel_1.add(lblNewLabel);
		
		te = new JTextField();
		panel_1.add(te);
		te.setColumns(15);
		
		lblNewLabel_1 = new JLabel("\uCD5C\uB300 3\uBA85\uAE4C\uC9C0 \uC120\uD0DD \uAC00\uB2A5\uD569\uB2C8\uB2E4.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1, BorderLayout.SOUTH);
		
		ch1.addItemListener(this);
		ch2.addItemListener(this);
		ch3.addItemListener(this);
		ch4.addItemListener(this);
		ch5.addItemListener(this);
		ch6.addItemListener(this);
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox check=(JCheckBox) e.getSource();
		
		if(check.isSelected()) {
			te.setText(te.getText()+check.getText());
			sum+=1;
			if(sum>2) {
				if(!ch1.isSelected()) {
					ch1.setEnabled(false);
				}
				if(!ch2.isSelected()) {
					ch2.setEnabled(false);
				}
				if(!ch3.isSelected()) {
					ch3.setEnabled(false);
				}
				if(!ch4.isSelected()) {
					ch4.setEnabled(false);
				}
				if(!ch5.isSelected()) {
					ch5.setEnabled(false);
				}
				if(!ch6.isSelected()) {
					ch6.setEnabled(false);
				}
			}
		}else {
			sum-=1;
		String str=te.getText();
		
		int start=str.indexOf(check.getText());
		int end=start+check.getText().length();
		
			te.select(start, end);
			te.replaceSelection("");
			
			if(sum<3) {
				if(!ch1.isSelected()) {
					ch1.setEnabled(true);
				}
				if(!ch2.isSelected()) {
					ch2.setEnabled(true);
				}
				if(!ch3.isSelected()) {
					ch3.setEnabled(true);
				}
				if(!ch4.isSelected()) {
					ch4.setEnabled(true);
				}
				if(!ch5.isSelected()) {
					ch5.setEnabled(true);
				}
				if(!ch6.isSelected()) {
					ch6.setEnabled(true);
				}
			}
		}
	}

}
