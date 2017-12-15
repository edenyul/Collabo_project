package userImpormation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class MemberFindTest3 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tName;
	private JTextField tId;
	private JTextField tMail;
	private JButton btnFind;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberFindTest3 frame = new MemberFindTest3();
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
	public MemberFindTest3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnFind = new JButton("    \uCC3E\uAE30    ");
		panel.add(btnFind);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("\uC774\uB984");
		panel_1.add(label);
		
		tName = new JTextField();
		tName.setColumns(10);
		panel_1.add(tName);
		
		JLabel label_1 = new JLabel("\uC544\uC774\uB514");
		panel_1.add(label_1);
		
		tId = new JTextField();
		tId.setColumns(10);
		panel_1.add(tId);
		
		JLabel label_2 = new JLabel("\uC774\uBA54\uC77C");
		panel_1.add(label_2);
		
		tMail = new JTextField();
		tMail.setColumns(10);
		panel_1.add(tMail);
		
		btnFind.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton) e.getSource();
		MemberDAO dao=new MemberDAO();
		Vector<MemberVO> vec=new Vector<>();
		
		if(btnFind==btn) {
			vec=dao.selectAll();
			
			String name=tName.getText();
			String id=tId.getText();
			String mail=tMail.getText();
			
			String passwd=null;
			
			if(name.equals("")||id.equals("")||mail.equals("")) {
				JOptionPane.showMessageDialog(contentPane, "빈칸을 채워 주세요.");
			}else {
			
				for(MemberVO list : vec) {
					if(name.equals(list.getName())) {
						if(id.equals(list.getId())) {
							if(mail.equals(list.getMail())) {
								passwd=list.getPasswd();
							}
						}
					}
				}
				
				if(passwd==null) {
					JOptionPane.showMessageDialog(contentPane, "해당 정보와 일치하는 비밀번호가 없습니다.");
				}else {
					int result=JOptionPane.showConfirmDialog(contentPane, "해당 정보와 일치하는 비밀번호 입니다.\n【 "+passwd+" 】", "비밀번호 찾기", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
				
					if(result==0) {
						tName.setText("");
						tId.setText("");
						tMail.setText("");
					}
				}
			}
		}
	}
		
}
