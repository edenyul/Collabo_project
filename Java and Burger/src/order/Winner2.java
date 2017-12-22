package order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class Winner2 extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	//�ܺ� Ŭ������ �����Ҳ��� public�̳� public static
	public JToggleButton btn[];
	public JLabel lbl;
	private JPanel panel;
	
	private JRadioButton radi[]=new JRadioButton[5];
	//���� �ڵ� ���ÿ� �ѱ� �̹��� - �̰� ���� â�̴ϱ� ���⿡ �ִ� �̺�Ʈ�ϱ� ���⼭ ����
	private int W;
	
	private String[] pic;
	
	public String[] winner= {"PC_1","PC_2","PC_3","PC_4","PC_5"};
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Winner2 dialog = new Winner2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Winner2() {
		setBounds(100, 100, 500, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setBackground(Color.orange);
		{
			lbl = new JLabel(""); //�󺧿� �ؽ�Ʈ ���ֱ�
			contentPanel.add(lbl, BorderLayout.CENTER);
			
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setBackground(Color.orange);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setBackground(Color.orange);
			/*{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}*/
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setBackground(new Color(225,51,51));
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setFont(new Font("����ҽ�üS", Font.PLAIN, 16));
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		btn=new JToggleButton[5];
		ButtonGroup group=new ButtonGroup();
		for(int i=0; i<btn.length; i++) {
			btn[i]=new JToggleButton((i+1)+"�� ����");
			panel.add(btn[i]);
			group.add(btn[i]);
			btn[i].addActionListener(this);
			btn[i].setBackground(new Color(225,51,51));
			btn[i].setForeground(Color.WHITE);
			btn[i].setFont(new Font("����ҽ�üS", Font.PLAIN, 16));
		}
		

/**		//Ŭ���� �޼ҵ� ���� - Advertisement5���� ������
		PP p=new PP();
		p.pp(W);*/
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		//�̰� �ܼ��� ��ư�� ���� ���̴� �̺�Ʈ
		Object obj=e.getSource();
		String[] picture= {"PC_1","PC_2","PC_3","PC_4","PC_5"}; //���� �̸���
		
		for(int i=0; i<btn.length; i++) {
			if(obj==btn[i]) {//�ش� ���� ��ư�� Ŭ�� �Ǿ��� ���
				lbl.setIcon(new ImageIcon(Winner2.class.getResource("/burger/"+picture[i]+".jpg"))); //�׿� �ش��ϴ� ���� ���̱�
			//}
		}
		}
		
		
	}
	
	
	
	//������ �ð������� �ٲ�� �����忡 �Ѱ��� P�ִ� �޼ҵ� ����
	public void start(int P) {
		String[] picture= {"PC_1","PC_2","PC_3","PC_4","PC_5"};
		lbl.setIcon(new ImageIcon(Winner2.class.getResource("/burger/"+picture[P]+".jpg")));
		btn[P].setSelected(true);;
	}
	
	

}
