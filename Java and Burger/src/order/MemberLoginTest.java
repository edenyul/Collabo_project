package order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SpringLayout;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JPasswordField;

public class MemberLoginTest extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField te1;
	private JButton btn1;
	private JButton btnjoin;
	private JPanel panel_1;
	private JButton btnFind;
	private JPanel panel_2;
	private JPanel panel_3;
	public Member mem=new Member();
	private JPasswordField te2;
	
	public static OrderMain order=new OrderMain();
	
	private JFrame mainF;
		
		public void setMainF(JFrame mainF) {
			this.mainF = mainF;
		}
		public JFrame getMainF() {
			return mainF;
		}
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public MemberLoginTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_2);
		
		btn1 = new JButton("\uB85C\uADF8\uC778~~!");
		btn1.setFont(new Font("������", Font.BOLD, 12));
		btn1.setBackground(new Color(128, 0, 0));
		btn1.setForeground(new Color(255, 255, 255));
		panel_2.add(btn1);
		
		btn1.addActionListener(this);
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_3);
		
		btnjoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnjoin.setFont(new Font("������", Font.BOLD, 12));
		btnjoin.setForeground(new Color(255, 255, 255));
		btnjoin.setBackground(new Color(128, 128, 128));
		panel_3.add(btnjoin);
		btnjoin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { //ȸ������ ��ư�� ������ ���
				MemberJoinTest mem=new MemberJoinTest();
				mem.setVisible(true);
			}
		});
		
		btnFind = new JButton("\uC544\uC774\uB514/\uBE44\uBC88 \uCC3E\uAE30");
		btnFind.setFont(new Font("������", Font.BOLD, 12));
		btnFind.setBackground(new Color(128, 128, 128));
		btnFind.setForeground(new Color(255, 255, 255));
		panel_3.add(btnFind);
		btnFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { //���̵�/��� ã�� ��ư�� ������ ���
				MemberFindTest find=new MemberFindTest();
				find.setVisible(true);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(128, 0, 0));
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblId.setBackground(new Color(255, 255, 255));
		panel.add(lblId);
		
		te1 = new JTextField();
		panel.add(te1);
		te1.setColumns(10);
		
		JLabel lblPassword = new JLabel("PassWord");
		lblPassword.setForeground(new Color(128, 0, 0));
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblPassword.setBackground(new Color(255, 255, 255));
		panel.add(lblPassword);
		
		te2 = new JPasswordField();
		panel.add(te2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {//�α��� ȭ�� ����
		Object btn=e.getSource();
		MemberDAO dao=new MemberDAO();
		MemberVO vo=new MemberVO();
		Vector<MemberVO> vec=new Vector<>();
		boolean identify=false;//���� Ȯ���� ����
		int check=0;
		
		
		if(btn==btn1) {
			String passwd=String.valueOf(te2.getPassword());
			
			vec=dao.selectAll();//��ü �߿��� Ȯ�� �ϱ� ���� ��ü �˻�
			mem.setMid(te1.getText());
			if(te1.getText().equals("")) {//���̵� ĭ�� ��� ���� ���
				JOptionPane.showMessageDialog(this, "���̵� �Է��� �ּ���");
			}else if(passwd.equals("")){//��й�ȣ ĭ�� ��� ���� ���
				JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��� �ּ���");
			}else{//���̵�� ��й�ȣ ĭ�� ��� ä���� ���� ���
			
			if(vec.size()!=0) {
				for(MemberVO list : vec) {	
					if(list.getId().equals(te1.getText())) {//DB����� �Է��� ���̵� ��ġ�� ���
						if(list.getPasswd().equals(passwd)) {//DB����� �Է��� ��й�ȣ�� ��ġ�� ���
							identify=true; //���� Ȯ�� �Ϸ�
							check=0;
							break;
						}else {//��й�ȣ�� Ʋ���� ���
							JOptionPane.showMessageDialog(this, "�߸��� ��� ��ȣ �Դϴ�.");
							te2.setText("");//��й�ȣĭ ���
							check=0;
							break;
						}	
					}else {//���̵� Ʋ���� ���
						check=1;
					}
				}
			
				if(check==1) {
					JOptionPane.showMessageDialog(this, "�������� �ʴ� ���̵� �Դϴ�.");
					te2.setText("");
					te1.setText("");//��й�ȣ, ���̵� ĭ �Ѵ� ���
				}
			
				
				if(identify) {//���� Ȯ���� �Ϸ�Ǿ��� ���
					JOptionPane.showMessageDialog(this, "�α��� �Ǿ����ϴ�.");
					identify=false;
					
				
				
					setVisible(false); //���� â ����
					//order.setVisible(true); //�α��� �Ϸ� �� ���ο� â ���� 
					JFrame main=getMainF();
					order.setVisible(true);
					order.allReset();
					
					mem.Birthday(order.panel_4);
				}
				
			}else {
				JOptionPane.showMessageDialog(contentPane, "������ ������ �����ϴ�. ȸ�������� �� �ּ���.");
				te2.setText("");
				te1.setText("");
			}
		}
		}
	}

}
