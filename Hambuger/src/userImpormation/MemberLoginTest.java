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
			public void actionPerformed(ActionEvent e) { //회원가입 버튼이 눌렸을 경우
				MemberJoinTest mem=new MemberJoinTest();
				mem.setVisible(true);
			}
		});
		
		btnFind = new JButton("\uC544\uC774\uB514/\uBE44\uBC88 \uCC3E\uAE30");
		panel_3.add(btnFind);
		btnFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { //아이디/비번 찾기 버튼이 눌렸을 경우
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
	public void actionPerformed(ActionEvent e) {//로그인 화면 구성
		Object btn=e.getSource();
		MemberDAO dao=new MemberDAO();
		MemberVO vo=new MemberVO();
		Vector<MemberVO> vec=new Vector<>();
		boolean identify=false;//본인 확인을 위함
		int check=0;
		
		
		if(btn==btn1) {
			vec=dao.selectAll();//전체 중에서 확인 하기 위해 전체 검색
			mem.setMid(te1.getText());
			if(te1.getText().equals("")) {//아이디 칸이 비어 있을 경우
				JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요");
			}else if(te2.getText().equals("")){//비밀번호 칸이 비어 있을 경우
				JOptionPane.showMessageDialog(this, "비밀번호를 입력해 주세요");
			}else {//아이디와 비밀번호 칸이 모두 채워져 있을 경우
			for(MemberVO list : vec) {	
				if(list.getId().equals(te1.getText())) {//DB내용과 입력한 아이디가 일치한 경우
					if(list.getPasswd().equals(te2.getText())) {//DB내용과 입력한 비밀번호가 일치한 경우
						identify=true; //본인 확인 완료
						check=0;
						break;
					}else {//비밀번호가 틀렸을 경우
						JOptionPane.showMessageDialog(this, "잘못된 비밀 번호 입니다.");
						te2.setText("");//비밀번호칸 비움
						check=0;
						break;
					}	
				}else {//아이디가 틀렸을 경우
					check=1;
				}
			}
				if(check==1) {
					JOptionPane.showMessageDialog(this, "존재하지 않는 아이디 입니다.");
					te2.setText("");
					te1.setText("");//비밀번호, 아이디 칸 둘다 비움
				}
			
			}
			if(identify) {//본인 확인이 완료되었을 경우
				JOptionPane.showMessageDialog(this, "로그인 되었습니다.");
				identify=false;
				
				MemberLoginStartTest login=new MemberLoginStartTest();
				
				setVisible(false); //현재 창 끄기
				login.setVisible(true); //로그인 완료 후 새로운 창 띄우기 
				login.lbWelcom.setText(mem.Birthday()); //생일인 사람과 아닌사람을 구분하여 환영 메세지 출력
			}
		}
	}

}
