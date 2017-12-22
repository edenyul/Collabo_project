package order;

import java.awt.BorderLayout;
import java.awt.Color;

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
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JPasswordField;

public class MemberJoinTest extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	private JTextField tName;
	private JTextField tId;
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
	private JPasswordField tPasswd;
	
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
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 10));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MemberJoinTest.class.getResource("/img/name_join.png")));
		panel.add(lblNewLabel);
		
		tName = new JTextField();
		panel.add(tName);
		tName.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MemberJoinTest.class.getResource("/img/id_join.png")));
		panel.add(label);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		tId = new JTextField();
		panel_4.add(tId);
		tId.setColumns(10);
		
		panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		panel_4.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 1));
		
		btnOverlap = new JButton("\uC911\uBCF5 \uD655\uC778");
		btnOverlap.setFont(new Font("새굴림", Font.BOLD, 12));
		btnOverlap.setForeground(new Color(255, 255, 255));
		btnOverlap.setBackground(new Color(128, 128, 128));
		panel_7.add(btnOverlap);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MemberJoinTest.class.getResource("/img/pwd_join.png")));
		panel.add(label_1);
		
		tPasswd = new JPasswordField();
		panel.add(tPasswd);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel.add(panel_2);
		
		tYear = new JTextField();
		panel_2.add(tYear);
		tYear.setColumns(10);
		
		label_2 = new JLabel("\uB144");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_2.setForeground(new Color(128, 0, 0));
		panel_2.add(label_2);
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 4, 0, 0));
		
		cMonth = new JComboBox();
		cMonth.setBackground(new Color(255, 255, 255));
		cMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		panel_3.add(cMonth);
		
		lblNewLabel_1 = new JLabel("\uC6D4");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		panel_3.add(lblNewLabel_1);
		
		tDay = new JTextField();
		panel_3.add(tDay);
		tDay.setColumns(10);
		
		label_6 = new JLabel("\uC77C");
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_6.setForeground(new Color(128, 0, 0));
		panel_3.add(label_6);
		
		btnMan = new JButton("\uB0A8\uC790");
		btnMan.setFont(new Font("새굴림", Font.BOLD, 16));
		btnMan.setForeground(new Color(255, 255, 255));
		btnMan.setBackground(new Color(128, 128, 128));
		panel.add(btnMan);
		
		btnWoman = new JButton("\uC5EC\uC790");
		btnWoman.setFont(new Font("새굴림", Font.BOLD, 16));
		btnWoman.setForeground(new Color(255, 255, 255));
		btnWoman.setBackground(new Color(128, 128, 128));
		panel.add(btnWoman);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(MemberJoinTest.class.getResource("/img/H.P.png")));
		panel.add(label_4);
		
		tPhone = new JTextField();
		tPhone.setColumns(10);
		panel.add(tPhone);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(MemberJoinTest.class.getResource("/img/email_join.png")));
		panel.add(label_5);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		tMail = new JTextField();
		tMail.setBackground(new Color(255, 255, 255));
		panel_1.add(tMail);
		tMail.setColumns(10);
		
		String[] str={"직접 입력", "@naver.com", "@daum.net", "@gmail.com", "@nate.com", "@outlook.com"};
		cMail = new JComboBox<String>();
		cMail.setBackground(new Color(255, 255, 255));
		cMail.setModel(new DefaultComboBoxModel(str));
		panel_1.add(cMail);
		
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel.add(panel_6);
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnEnd = new JButton("\uC785\uB825 \uC644\uB8CC");
		btnEnd.setFont(new Font("새굴림", Font.BOLD, 12));
		btnEnd.setForeground(new Color(255, 255, 255));
		btnEnd.setBackground(new Color(128, 0, 0));
		panel_5.add(btnEnd);
		
		btnCancle = new JButton("   \uCDE8\uC18C   ");
		btnCancle.setFont(new Font("새굴림", Font.BOLD, 12));
		btnCancle.setForeground(new Color(255, 255, 255));
		btnCancle.setBackground(new Color(128, 128, 128));
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
		for(int i=0; i<s.length; i++) {//for를 이용하여 텍스트 필드 돌리기
			if(s[i].isFocusable()) {//만약 해당 텍스트 필드를 쓰고 있을 경우
				
				s[i].addKeyListener(new KeyAdapter() {//키보드 이벤트 생성
					
					@Override
					public void keyReleased(KeyEvent e) {
						if(e.getKeyCode()==KeyEvent.VK_SPACE) {
						
						if(tName.isFocusOwner()) { //해당 텍스트 필드에 초점이 맞춰져 있는 경우
							String name=tName.getText();//해당 필드의 String값을 가져와 변수에 담기
							tName.setText(name.trim()); //띄어쓰기 된 마지막 한칸 삭제
						}else if(tId.isFocusOwner()) {
							String id=tId.getText();
							tId.setText(id.trim());
						}else if(tPasswd.isFocusOwner()) {
							String passwd=String.valueOf(tPasswd.getPassword());
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
						
						JOptionPane.showMessageDialog(contentPane, "띄어쓰기는 사용하실 수 없습니다.");
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
		int result=0; //해당 결과를 확인하기 위함
		String str=null; //성별을 구분하기 위함
		
		if(obj==btnMan) { //만약 '남자' 버튼이 눌린경우
			btnMan.setEnabled(false); //해당 버튼 비활성화
			btnWoman.setEnabled(true); //'여자' 버튼 활성화
			
		}else if(obj==btnWoman) {
			btnWoman.setEnabled(false);
			btnMan.setEnabled(true);
		}
		
		if(obj==btnEnd) {//회원가입을 눌렀을 경우
			String passwd=String.valueOf(tPasswd.getPassword());
			
			if(tName.getText().equals("")) {//만약 해당 칸이 빈칸이라면
				JOptionPane.showMessageDialog(this, "이름을 입력해 주세요");
			}else if(tId.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요");
			}else if(passwd.equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력해 주세요");
			}else if(tYear.getText().equals("")||tDay.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "생일을 입력해 주세요");
			}else if(btnWoman.isEnabled()&&btnMan.isEnabled()) {
				JOptionPane.showMessageDialog(this, "성별을 선택해 주세요");
			}else if(tPhone.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "폰 번호를 입력해 주세요");
			}else if(tMail.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "메일을 입력해 주세요");
			}else {//모든 칸이 입력되어 있을 경우
				try {
					Long.parseLong(tPhone.getText()); //숫자만 입력했는지 알아보기 위함.
					Integer.parseInt(tYear.getText());
					Integer.parseInt(tDay.getText());
					
				if(!btnWoman.isEnabled()) {//'여자' 버튼이 비활성화 되어있는 경우
				str=btnWoman.getActionCommand(); //해당 버튼의 text값을 가져오기
				}else {
					str=btnMan.getActionCommand();
				}
				
				if(tName.getText().length()>5) {//해당 텍스트 길이가 데이터 베이스 지정길이 보다 길 경우
					JOptionPane.showMessageDialog(this, "이름의 최대 길이는 5글자 입니다.");
				}else if(tId.getText().length()>12) {
					JOptionPane.showMessageDialog(this, "아이디의 최대 길이는 12글자 입니다.");
				}else if(passwd.length()>20) {
					JOptionPane.showMessageDialog(this, "패스워드의 최대 길이는 20글자 입니다.");
				}else if(tPhone.getText().length()!=11) {
					JOptionPane.showMessageDialog(this, "번호를 다시 입력해 주세요.");
				}else if(tMail.getText().length()>50) {
					JOptionPane.showMessageDialog(this, "메일의 최대 길이는 50글자 입니다.");
				}else if(tYear.getText().length()!=4||(tDay.getText().length()==1 && tDay.getText().length()==2)||Integer.parseInt(tDay.getText())>31){
					JOptionPane.showMessageDialog(this, "생일을 다시 입력해 주세요.");
				}
				else {
					//해당 필드의 값을 가져와 MemberVO 변수에 담기
					
					vo.setName(tName.getText().trim());
					vo.setId(tId.getText().trim());
					vo.setPasswd(passwd.trim());
					vo.setBirthday(tYear.getText().trim()+"-"+cMonth.getSelectedItem()+"-"+tDay.getText().trim());
					vo.setPhone(tPhone.getText().trim());
					vo.setGender(str);
					if(cMail.getSelectedItem().equals("직접 입력")) {//만약 메일 선택중 직접 입력이 선택되어 있는 경우
						int index=tMail.getText().indexOf("@");//@표시가 있는지 확인
						if(index==-1) {//@가 없다면
							JOptionPane.showMessageDialog(this, "'@메일'을 입력해 주세요.");
						}else {
							vo.setMail(tMail.getText().trim());
							if(check>1) {
								result=dao.insert(vo);//데이터 베이스에 값 입력
							}else {
								JOptionPane.showMessageDialog(this, "아이디 중복체크를 해 주세요.");
							}
						}
						
					}else {//직접입력이 선택되지 않은 경우
						vo.setMail(tMail.getText().trim()+cMail.getSelectedItem());
						if(check>1) {
							result=dao.insert(vo);//데이터 베이스에 값 입력
						}else {
							JOptionPane.showMessageDialog(this, "아이디 중복체크를 해 주세요.");
						}
					}	
						
					if(result>0) {//무사히 입력 완료 되었다면
						JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.");
						dispose();
					}else if (result==0){//초기 값이라면
						//notthing!
					}else {//오류가 났다면
						JOptionPane.showMessageDialog(this, "회원가입을 성공하지 못했습니다.","오류",JOptionPane.WARNING_MESSAGE);
					}
					
				}
				
				}catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(this, "번호나 생일에는 문자를 넣을 수 없습니다.");
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(this, "회원가입을 성공하지 못했습니다.","오류",JOptionPane.WARNING_MESSAGE);
					e2.printStackTrace();
				}
			}//칸의 빈칸 유무 if
		}else if(obj==btnOverlap) {//중복 확인 버튼을 눌렀을 시
			Vector<MemberVO> vec=new Vector<>();
			vec=dao.selectAll();//데이터 베이스 조회
			check=0; //결과 값을 확인하기 위함
			System.out.println(tId.getText());
			
			if(vec.size()!=0) {
				for(MemberVO list : vec) {
					if(tId.getText().equals(list.getId())) { //만약 중복되는 것이 있다면
						check=1; //값 1
						break;
					}else if(tId.getText().equals("")){ //빈칸이라면 값 0
					}else { // 중복되는 것이 없다면
						check=2; //값 2
					}
				}
			}else {
				check=2;
			}
			
			if(check==0) {
				JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요.");
			}else if(check==1) {
				JOptionPane.showMessageDialog(this, "사용 중인 아이디 입니다.");
				tId.setText("");
			}else {
				JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다.");
			}
			
		}//버튼 활성화 if

	}//action

}
