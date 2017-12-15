package userImpormation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MemberFindTest2 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tName; //�̸�
	private JTextField tYear; //�⵵
	private JTextField tDay; //��
	private JTextField tMail; //����
	private JComboBox cMonth; //��
	private JButton btnFind; //ã�� ��ư

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberFindTest2 frame = new MemberFindTest2();
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
	public MemberFindTest2() {
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
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		tYear = new JTextField();
		tYear.setColumns(10);
		panel_2.add(tYear);
		
		JLabel label_1 = new JLabel("\uB144");
		panel_2.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 4, 0, 0));
		
		cMonth = new JComboBox();
		cMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		panel_3.add(cMonth);
		
		JLabel label_2 = new JLabel("\uC6D4");
		panel_3.add(label_2);
		
		tDay = new JTextField();
		tDay.setColumns(10);
		panel_3.add(tDay);
		
		JLabel label_3 = new JLabel("\uC77C");
		panel_3.add(label_3);
		
		JLabel label_4 = new JLabel("\uC774\uBA54\uC77C");
		panel_1.add(label_4);
		
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
			String bir=tYear.getText()+"-"+cMonth.getSelectedItem()+"-"+tDay.getText();
			String mail=tMail.getText();
			
			ArrayList<String> id=new ArrayList<>(); //���̵� ������ ���� ��� �ش��ϴ� ��� ���̵� ��� ����
			
			if(name.equals("")||bir.equals("")||mail.equals("")) { //���� ��ĭ�� ������ ���
				JOptionPane.showMessageDialog(contentPane, "��ĭ�� ä�� �ּ���.");
			}else { //ĭ�� ��� ä���� ���� ���
			
				for(MemberVO list : vec) {
					if(name.equals(list.getName())) { //������ ���̽��� �̸��� �ִٸ�
						if(bir.equals(list.getBirthday())) { //������ ���̽��� �ִ� ������ ������ ���ٸ�
							if(mail.equals(list.getMail())) { //������ ���̽��� ������ ������ ���ٸ�
								id.add(list.getId()); //�ش� ������ ��ġ�ϴ� ID�� ��� �迭�� ���
							}
						}
					}
				}
				
				if(id.size()==0) { //�迭�� ��� ������ ���ٸ�
					JOptionPane.showMessageDialog(contentPane, "�ش� ������ ��ġ�ϴ� ���̵� �����ϴ�.");
				}else {
					StringBuffer str=new StringBuffer();
					int a=1; //���̵��� ����
					for(String list : id) { //�迭�� ��� ���̵� ��� ���
						str.append(a+". "+list+"\r\n"); //JOption���� ����ϱ� ���� String�� ���
						a++;
					}
					int result=JOptionPane.showConfirmDialog(contentPane, "�ش� ������ ��ġ�ϴ� ���̵� �Դϴ�.\n"+str, "���̵� ã��", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
				
					if(result==0) { //���̵� Ȯ�� ���� �� ��� �ʵ� �ʱ�ȭ
						tName.setText("");
						tYear.setText("");
						cMonth.setSelectedIndex(0);
						tDay.setText("");
						tMail.setText("");
						
						dispose(); //�������� �ϰ� �;����� ������ ����..
					}
				}
			}
		}
		
	}

}
