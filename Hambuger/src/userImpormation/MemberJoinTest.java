package userImpormation;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;

public class MemberJoinTest extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	private JTextField tName;
	private JTextField tId;
	private JTextField tPasswd;
	private JTextField tPhone;
	private JButton btnEnd;
	private JButton btnMan;
	private JButton btnWoman;
	private JPanel panel_1;
	private JTextField tMail;
	private JComboBox cMail;
	private JPanel panel_2;
	private JPanel panel_3;
	private JTextField tYear;
	private JLabel label_2;
	private JComboBox cMonth;
	private JLabel lblNewLabel_1;
	private JTextField tDay;
	private JLabel label_6;
	private JPanel panel_4;
	private int check;
	private JPanel panel_5;
	private JButton btnCancle;
	private JPanel panel_6;
	private JPanel panel_7;
	private JButton btnOverlap;
	
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MemberJoinTest frame = new MemberJoinTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public MemberJoinTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 10));
		
		JLabel lblNewLabel = new JLabel("\uC774\uB984");
		panel.add(lblNewLabel);
		
		tName = new JTextField();
		panel.add(tName);
		tName.setColumns(10);
		
		JLabel label = new JLabel("\uC544\uC774\uB514");
		panel.add(label);
		
		panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		tId = new JTextField();
		panel_4.add(tId);
		tId.setColumns(10);
		
		panel_7 = new JPanel();
		panel_4.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 1));
		
		btnOverlap = new JButton("\uC911\uBCF5 \uD655\uC778");
		panel_7.add(btnOverlap);
		
		JLabel label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		panel.add(label_1);
		
		tPasswd = new JTextField();
		tPasswd.setColumns(10);
		panel.add(tPasswd);
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		
		tYear = new JTextField();
		panel_2.add(tYear);
		tYear.setColumns(10);
		
		label_2 = new JLabel("\uB144");
		panel_2.add(label_2);
		
		panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 4, 0, 0));
		
		cMonth = new JComboBox();
		cMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		panel_3.add(cMonth);
		
		lblNewLabel_1 = new JLabel("\uC6D4");
		panel_3.add(lblNewLabel_1);
		
		tDay = new JTextField();
		panel_3.add(tDay);
		tDay.setColumns(10);
		
		label_6 = new JLabel("\uC77C");
		panel_3.add(label_6);
		
		btnMan = new JButton("\uB0A8\uC790");
		panel.add(btnMan);
		
		btnWoman = new JButton("\uC5EC\uC790");
		panel.add(btnWoman);
		
		JLabel label_4 = new JLabel("\uD3F0\uBC88\uD638");
		panel.add(label_4);
		
		tPhone = new JTextField();
		tPhone.setColumns(10);
		panel.add(tPhone);
		
		JLabel label_5 = new JLabel("\uBA54\uC77C");
		panel.add(label_5);
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		tMail = new JTextField();
		panel_1.add(tMail);
		tMail.setColumns(10);
		
		String[] str={"���� �Է�", "@naver.com", "@daum.net", "@gmail.com", "@nate.com", "@outlook.com"};
		cMail = new JComboBox<String>();
		cMail.setModel(new DefaultComboBoxModel(str));
		panel_1.add(cMail);
		
		panel_6 = new JPanel();
		panel.add(panel_6);
		
		panel_5 = new JPanel();
		contentPane.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnEnd = new JButton("\uC785\uB825 \uC644\uB8CC");
		panel_5.add(btnEnd);
		
		btnCancle = new JButton("   \uCDE8\uC18C   ");
		panel_5.add(btnCancle);
		btnCancle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnEnd.addActionListener(this);
		btnMan.addActionListener(this);
		btnWoman.addActionListener(this);
		btnOverlap.addActionListener(this);
		
		JTextField[] s= {tName,tId,tPasswd,tYear,tDay,tPhone,tMail};
		for(int i=0; i<s.length; i++) {//for�� �̿��Ͽ� �ؽ�Ʈ �ʵ� ������
			if(s[i].isFocusable()) {//���� �ش� �ؽ�Ʈ �ʵ带 ���� ���� ���
				
				s[i].addKeyListener(new KeyAdapter() {//Ű���� �̺�Ʈ ����
					
					@Override
					public void keyReleased(KeyEvent e) {
						if(e.getKeyCode()==KeyEvent.VK_SPACE) {
						
						if(tName.isFocusOwner()) { //�ش� �ؽ�Ʈ �ʵ忡 ������ ������ �ִ� ���
							String name=tName.getText();//�ش� �ʵ��� String���� ������ ������ ���
							tName.setText(name.trim()); //���� �� ������ ��ĭ ����
						}else if(tId.isFocusOwner()) {
							String id=tId.getText();
							tId.setText(id.trim());
						}else if(tPasswd.isFocusOwner()) {
							String passwd=tPasswd.getText();
							tPasswd.setText(passwd.trim());
						}else if(tYear.isFocusOwner()) {
							String year1=tYear.getText();
							tYear.setText(year1.trim());
						}else if(tDay.isFocusOwner()) {
							String day1=tDay.getText();
							tDay.setText(day1.trim());
						}else if(tPhone.isFocusOwner()) {
							String phone=tPhone.getText();
							tPhone.setText(phone.trim());
						}else if(tMail.isFocusOwner()) {
							String mail=tMail.getText();
							tMail.setText(mail.trim());
						}
						
						JOptionPane.showMessageDialog(contentPane, "����� ����Ͻ� �� �����ϴ�.");
					}
					}
					
				});
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		MemberDAO dao=new MemberDAO();
		MemberVO vo=new MemberVO();
		int result=0; //�ش� ����� Ȯ���ϱ� ����
		String str=null; //������ �����ϱ� ����
		
		if(obj==btnMan) { //���� '����' ��ư�� �������
			btnMan.setEnabled(false); //�ش� ��ư ��Ȱ��ȭ
			btnWoman.setEnabled(true); //'����' ��ư Ȱ��ȭ
			
		}else if(obj==btnWoman) {
			btnWoman.setEnabled(false);
			btnMan.setEnabled(true);
		}
		
		if(obj==btnEnd) {//ȸ�������� ������ ���
			if(tName.getText().equals("")) {//���� �ش� ĭ�� ��ĭ�̶��
				JOptionPane.showMessageDialog(this, "�̸��� �Է��� �ּ���");
			}else if(tId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "���̵� �Է��� �ּ���");
			}else if(tPasswd.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��� �ּ���");
			}else if(tYear.getText().equals("")||tDay.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "������ �Է��� �ּ���");
			}else if(btnWoman.isEnabled()&&btnMan.isEnabled()) {
				JOptionPane.showMessageDialog(this, "������ ������ �ּ���");
			}else if(tPhone.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "�� ��ȣ�� �Է��� �ּ���");
			}else if(tMail.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "������ �Է��� �ּ���");
			}else {//��� ĭ�� �ԷµǾ� ���� ���
				try {
					Long.parseLong(tPhone.getText()); //���ڸ� �Է��ߴ��� �˾ƺ��� ����.
					Integer.parseInt(tYear.getText());
					Integer.parseInt(tDay.getText());
					
				if(!btnWoman.isEnabled()) {//'����' ��ư�� ��Ȱ��ȭ �Ǿ��ִ� ���
				str=btnWoman.getActionCommand(); //�ش� ��ư�� text���� ��������
				}else {
					str=btnMan.getActionCommand();
				}
				
				if(tName.getText().length()>5) {//�ش� �ؽ�Ʈ ���̰� ������ ���̽� �������� ���� �� ���
					JOptionPane.showMessageDialog(this, "�̸��� �ִ� ���̴� 5���� �Դϴ�.");
				}else if(tId.getText().length()>12) {
					JOptionPane.showMessageDialog(this, "���̵��� �ִ� ���̴� 12���� �Դϴ�.");
				}else if(tPasswd.getText().length()>20) {
					JOptionPane.showMessageDialog(this, "�н������� �ִ� ���̴� 20���� �Դϴ�.");
				}else if(tPhone.getText().length()!=11) {
					JOptionPane.showMessageDialog(this, "��ȣ�� �ٽ� �Է��� �ּ���.");
				}else if(tMail.getText().length()>50) {
					JOptionPane.showMessageDialog(this, "������ �ִ� ���̴� 50���� �Դϴ�.");
				}else if(tYear.getText().length()!=4||(tDay.getText().length()==1 && tDay.getText().length()==2)||Integer.parseInt(tDay.getText())>31){
					JOptionPane.showMessageDialog(this, "������ �ٽ� �Է��� �ּ���.");
				}
				else {
					//�ش� �ʵ��� ���� ������ MemberVO ������ ���
					
					vo.setName(tName.getText().trim());
					vo.setId(tId.getText().trim());
					vo.setPasswd(tPasswd.getText().trim());
					vo.setBirthday(tYear.getText().trim()+"-"+cMonth.getSelectedItem()+"-"+tDay.getText().trim());
					vo.setPhone(tPhone.getText().trim());
					vo.setGender(str);
					if(cMail.getSelectedItem().equals("���� �Է�")) {//���� ���� ������ ���� �Է��� ���õǾ� �ִ� ���
						int index=tMail.getText().indexOf("@");//@ǥ�ð� �ִ��� Ȯ��
						if(index==-1) {//@�� ���ٸ�
							JOptionPane.showMessageDialog(this, "'@����'�� �Է��� �ּ���.");
						}else {
							vo.setMail(tMail.getText().trim());
							if(check>1) {
								result=dao.insert(vo);//������ ���̽��� �� �Է�
							}else {
								JOptionPane.showMessageDialog(this, "���̵� �ߺ�üũ�� �� �ּ���.");
							}
						}
						
					}else {//�����Է��� ���õ��� ���� ���
						vo.setMail(tMail.getText().trim()+cMail.getSelectedItem());
						if(check>1) {
							result=dao.insert(vo);//������ ���̽��� �� �Է�
						}else {
							JOptionPane.showMessageDialog(this, "���̵� �ߺ�üũ�� �� �ּ���.");
						}
					}	
						
					if(result>0) {//������ �Է� �Ϸ� �Ǿ��ٸ�
						JOptionPane.showMessageDialog(this, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
						dispose();
					}else if (result==0){//�ʱ� ���̶��
						//notthing!
					}else {//������ ���ٸ�
						JOptionPane.showMessageDialog(this, "ȸ�������� �������� ���߽��ϴ�.","����",JOptionPane.WARNING_MESSAGE);
					}
					
				}
				
				}catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(this, "��ȣ�� ���Ͽ��� ���ڸ� ���� �� �����ϴ�.");
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(this, "ȸ�������� �������� ���߽��ϴ�.","����",JOptionPane.WARNING_MESSAGE);
					e2.printStackTrace();
				}
			}//ĭ�� ��ĭ ���� if
		}else if(obj==btnOverlap) {//�ߺ� Ȯ�� ��ư�� ������ ��
			Vector<MemberVO> vec=new Vector<>();
			vec=dao.selectAll();//������ ���̽� ��ȸ
			check=0; //��� ���� Ȯ���ϱ� ����
			
			for(MemberVO list : vec) {
				if(tId.getText().equals(list.getId())) { //���� �ߺ��Ǵ� ���� �ִٸ�
					check=1; //�� 1
					break;
				}else if(tId.getText().equals("")){ //��ĭ�̶�� �� 0
				}else { // �ߺ��Ǵ� ���� ���ٸ�
					check=2; //�� 2
				}
			}
			
			if(check==0) {
				JOptionPane.showMessageDialog(this, "���̵� �Է��� �ּ���.");
			}else if(check==1) {
				JOptionPane.showMessageDialog(this, "��� ���� ���̵� �Դϴ�.");
				tId.setText("");
			}else {
				JOptionPane.showMessageDialog(this, "��� ������ ���̵� �Դϴ�.");
			}
			
		}//��ư Ȱ��ȭ if

	}//action

}
