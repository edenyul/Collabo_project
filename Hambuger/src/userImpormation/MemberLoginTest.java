package userImpormation;

import java.awt.BorderLayout;
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

public class MemberLoginTest extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField te1;
	private JTextField te2;
	private JButton btn1;
	private JButton btnjoin;
	private JPanel panel_1;
	private JButton btnFind;
	private JPanel panel_2;
	private JPanel panel_3;
	public Member mem=new Member();

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		btn1 = new JButton("\uB85C\uADF8\uC778~~!");
		panel_2.add(btn1);
		
		btn1.addActionListener(this);
		
		panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_3);
		
		btnjoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		panel_3.add(btnjoin);
		btnjoin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { //ȸ������ ��ư�� ������ ���
				MemberJoinTest mem=new MemberJoinTest();
				mem.setVisible(true);
			}
		});
		
		btnFind = new JButton("\uC544\uC774\uB514/\uBE44\uBC88 \uCC3E\uAE30");
		panel_3.add(btnFind);
		btnFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { //���̵�/��� ã�� ��ư�� ������ ���
				MemberFindTest find=new MemberFindTest();
				find.setVisible(true);
			}
		});
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblId = new JLabel("ID");
		panel.add(lblId);
		
		te1 = new JTextField();
		panel.add(te1);
		te1.setColumns(10);
		
		JLabel lblPassword = new JLabel("PassWord");
		panel.add(lblPassword);
		
		te2 = new JTextField();
		te2.setColumns(10);
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
			vec=dao.selectAll();//��ü �߿��� Ȯ�� �ϱ� ���� ��ü �˻�
			mem.setMid(te1.getText());
			if(te1.getText().equals("")) {//���̵� ĭ�� ��� ���� ���
				JOptionPane.showMessageDialog(this, "���̵� �Է��� �ּ���");
			}else if(te2.getText().equals("")){//��й�ȣ ĭ�� ��� ���� ���
				JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��� �ּ���");
			}else {//���̵�� ��й�ȣ ĭ�� ��� ä���� ���� ���
			for(MemberVO list : vec) {	
				if(list.getId().equals(te1.getText())) {//DB����� �Է��� ���̵� ��ġ�� ���
					if(list.getPasswd().equals(te2.getText())) {//DB����� �Է��� ��й�ȣ�� ��ġ�� ���
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
			
			}
			if(identify) {//���� Ȯ���� �Ϸ�Ǿ��� ���
				JOptionPane.showMessageDialog(this, "�α��� �Ǿ����ϴ�.");
				identify=false;
				
				MemberLoginStartTest login=new MemberLoginStartTest();
				
				setVisible(false); //���� â ����
				login.setVisible(true); //�α��� �Ϸ� �� ���ο� â ���� 
				login.lbWelcom.setText(mem.Birthday()); //������ ����� �ƴѻ���� �����Ͽ� ȯ�� �޼��� ���
			}
		}
	}

}
