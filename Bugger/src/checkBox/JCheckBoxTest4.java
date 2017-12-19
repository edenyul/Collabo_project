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
			
			//text필드에 있는 문자열 가져오기
			/*String str=textField.getText();
			
			//가져온 물자열 중에 체크 해제 된 문자열의 위치를 찾아서 원본 문자열에서 
			//삭제한 후 텍스트 필드에 보여주기
			int start=str.indexOf(check.getText());//지워질 글자 시작위치
			int end=start+check.getText().length();//지워질 글자 끝 위치
			
			StringBuffer stb=new StringBuffer(str);
			stb.delete(start, end);
			textField.setText(stb.toString());*/
			
//----------------------------------------------------------------
			
			String str=textField.getText();
			
			int start=str.indexOf(check.getText());
			int end=start+check.getText().length();
			
			textField.select(start, end); //범위만큼 선택
			textField.replaceSelection(""); //선택한 범위의 글자를 ""로 채움
		
		}//else
		
	
		
		
	}//item

}//class
