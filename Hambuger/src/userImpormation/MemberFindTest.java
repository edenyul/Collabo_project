package userImpormation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

public class MemberFindTest extends JDialog {
	private CardLayout card=new CardLayout(); //패널을 바꾸기 위함
	private JPanel panel; //메인 패널
	private String str=""; //현재 무슨 창인지 구분하기 위함
	private JButton cancelButton;
	//public int num=0; //?? 기억 안남ㅋㅋㅋ

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MemberFindTest dialog = new MemberFindTest();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MemberFindTest() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(card);
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "find");
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2, BorderLayout.CENTER);
					panel_2.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel logID = new JLabel("\uC544\uC774\uB514 \uCC3E\uAE30");
						panel_2.add(logID);
						logID.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								change("idfind");
								cancelButton.setText("돌아가기");
							}
						});
						logID.setHorizontalAlignment(SwingConstants.CENTER);
						logID.setFont(new Font("굴림", Font.BOLD, 18));
					}
					{
						JLabel logPasswd = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
						panel_2.add(logPasswd);
						logPasswd.addMouseListener(new MouseAdapter() {
							
							@Override
							public void mouseClicked(MouseEvent e) {
								change("passwdfind");
								cancelButton.setText("돌아가기");
							}
						});
						logPasswd.setHorizontalAlignment(SwingConstants.CENTER);
						logPasswd.setFont(new Font("굴림", Font.BOLD, 18));
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			/*{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}*/
			{
				cancelButton = new JButton("닫기");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(str.equals("passwdfind")||str.equals("idfind")) { //현재 아이디/패스워드 찾기 창일 경우
							change("find"); //선택 창으로 이동
							cancelButton.setText("닫기"); //버튼은 '닫기'로 변경
						}else { //선택 창일 경우
							dispose();	//정상 종료					
						}
						
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	public void change(String nulls) { //선택창에서 새창을 띄우지 않고 패널만 바꾸기 위한 메소드
		
		MemberFindTest2 id=new MemberFindTest2(); //아이디 찾기 창을 띄우기 위한 선언
		MemberFindTest3 passwd=new MemberFindTest3(); //패스워드 찾기 창을 띄우기 위한 선언
		
		panel.add(id.getContentPane(), "idfind"); //카드패널에 아이디 찾기 창 붙이기
		panel.add(passwd.getContentPane(), "passwdfind"); //카드패널에 비밀번호 찾기 창 붙이기
		
		if(nulls.equals("idfind")) { //idfind를 입력 받은 경우
			card.show(panel, "idfind"); //아이디 찾기 창으로 전환
			str="idfind";				//현재창이 아이디 찾기 창이라고 표시
		}else if(nulls.equals("passwdfind")) {
			card.show(panel, "passwdfind");
			str="passwdfind";
		}else if(nulls.equals("find")){
			card.show(panel, "find");
			str="find";
		}
		
	}

}
