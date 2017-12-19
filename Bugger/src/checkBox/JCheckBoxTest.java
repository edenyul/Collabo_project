package checkBox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JCheckBoxTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JCheckBox chc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCheckBoxTest frame = new JCheckBoxTest();
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
	public JCheckBoxTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		chc = new JCheckBox("\uC0AC\uACFC");
		chc.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {//눌리면 1 아니면 2를 반환(int)
					textField.setText(chc.getText());
				}else {
					textField.setText("");
				}
			}
		});
		/*chc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JCheckBox check=(JCheckBox) e.getSource();
				boolean a=chc.isSelected();
				textField.setText(chc.getText());
				if(a==false) {
					textField.setText("");
				}
				
				 if(chc.isSelected()) {
				 * textField.setText(chc.getText());
				 * }else {
				 * textFiedl.setText("");
				 * }
				 
				
			}
		});*/
		contentPane.add(chc, BorderLayout.NORTH);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		
		pack();
	}

}
