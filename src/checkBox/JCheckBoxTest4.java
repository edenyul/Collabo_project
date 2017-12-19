package checkBox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.FlowLayout;

public class JCheckBoxTest4 extends JFrame implements ItemListener{

	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel;
	private JCheckBox chapple;
	private JCheckBox chgrape;
	private JCheckBox chmango;
	private JCheckBox chwater;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCheckBoxTest4 frame = new JCheckBoxTest4();
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
	public JCheckBoxTest4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		chapple = new JCheckBox("\uC0AC\uACFC");
		panel.add(chapple);
		
		chwater = new JCheckBox("\uC218\uBC15");
		panel.add(chwater);
		
		chgrape = new JCheckBox("\uD3EC\uB3C4");
		panel.add(chgrape);
		
		chmango = new JCheckBox("\uB9DD\uACE0");
		panel.add(chmango);
		
		chapple.addItemListener(this);
		chgrape.addItemListener(this);
		chmango.addItemListener(this);
		chwater.addItemListener(this);
		
		ButtonGroup group=new ButtonGroup();
		group.add(chapple);
		group.add(chgrape);
		group.add(chmango);
		group.add(chwater);
		
		pack();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox check=(JCheckBox) e.getSource();
		
		if(check.isSelected()) {
			textField.setText(textField.getText()+check.getText());
			System.out.println(e.getStateChange());
		}else {
			/*textField.setText("");
			
			if(chapple.isSelected()) {
				textField.setText(textField.getText()+chapple.getText());
			}
			if(chgrape.isSelected()) {
				textField.setText(textField.getText()+chgrape.getText());
			}
			if(chmango.isSelected()) {
				textField.setText(textField.getText()+chmango.getText());
			}
			if(chwater.isSelected()) {
				textField.setText(textField.getText()+chwater.getText());
			}*/
			
//-----------------------------------------------------------------
			
			//text�ʵ忡 �ִ� ���ڿ� ��������
			/*String str=textField.getText();
			
			//������ ���ڿ� �߿� üũ ���� �� ���ڿ��� ��ġ�� ã�Ƽ� ���� ���ڿ����� 
			//������ �� �ؽ�Ʈ �ʵ忡 �����ֱ�
			int start=str.indexOf(check.getText());//������ ���� ������ġ
			int end=start+check.getText().length();//������ ���� �� ��ġ
			
			StringBuffer stb=new StringBuffer(str);
			stb.delete(start, end);
			textField.setText(stb.toString());*/
			
//----------------------------------------------------------------
			
			String str=textField.getText();
			
			int start=str.indexOf(check.getText());
			int end=start+check.getText().length();
			
			textField.select(start, end); //������ŭ ����
			textField.replaceSelection(""); //������ ������ ���ڸ� ""�� ä��
		
		}//else
		
	
		
		
	}//item

}//class
