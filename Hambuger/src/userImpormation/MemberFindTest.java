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
	private CardLayout card=new CardLayout(); //�г��� �ٲٱ� ����
	private JPanel panel; //���� �г�
	private String str=""; //���� ���� â���� �����ϱ� ����
	private JButton cancelButton;
	//public int num=0; //?? ��� �ȳ�������

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
								cancelButton.setText("���ư���");
							}
						});
						logID.setHorizontalAlignment(SwingConstants.CENTER);
						logID.setFont(new Font("����", Font.BOLD, 18));
					}
					{
						JLabel logPasswd = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
						panel_2.add(logPasswd);
						logPasswd.addMouseListener(new MouseAdapter() {
							
							@Override
							public void mouseClicked(MouseEvent e) {
								change("passwdfind");
								cancelButton.setText("���ư���");
							}
						});
						logPasswd.setHorizontalAlignment(SwingConstants.CENTER);
						logPasswd.setFont(new Font("����", Font.BOLD, 18));
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
				cancelButton = new JButton("�ݱ�");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(str.equals("passwdfind")||str.equals("idfind")) { //���� ���̵�/�н����� ã�� â�� ���
							change("find"); //���� â���� �̵�
							cancelButton.setText("�ݱ�"); //��ư�� '�ݱ�'�� ����
						}else { //���� â�� ���
							dispose();	//���� ����					
						}
						
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	public void change(String nulls) { //����â���� ��â�� ����� �ʰ� �гθ� �ٲٱ� ���� �޼ҵ�
		
		MemberFindTest2 id=new MemberFindTest2(); //���̵� ã�� â�� ���� ���� ����
		MemberFindTest3 passwd=new MemberFindTest3(); //�н����� ã�� â�� ���� ���� ����
		
		panel.add(id.getContentPane(), "idfind"); //ī���гο� ���̵� ã�� â ���̱�
		panel.add(passwd.getContentPane(), "passwdfind"); //ī���гο� ��й�ȣ ã�� â ���̱�
		
		if(nulls.equals("idfind")) { //idfind�� �Է� ���� ���
			card.show(panel, "idfind"); //���̵� ã�� â���� ��ȯ
			str="idfind";				//����â�� ���̵� ã�� â�̶�� ǥ��
		}else if(nulls.equals("passwdfind")) {
			card.show(panel, "passwdfind");
			str="passwdfind";
		}else if(nulls.equals("find")){
			card.show(panel, "find");
			str="find";
		}
		
	}

}
