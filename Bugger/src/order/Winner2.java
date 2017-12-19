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
	//외부 클래스와 공유할꺼면 public이나 public static
	public JToggleButton btn[];
	public JLabel lbl;
	private JPanel panel;
	
	private JRadioButton radi[]=new JRadioButton[5];
	//사진 자동 선택에 넘길 이미지 - 이건 여기 창이니까 여기에 넣는 이벤트니까 여기서 구현
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
			lbl = new JLabel(""); //라벨에 텍스트 없애기
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
				cancelButton.setFont(new Font("양재소슬체S", Font.PLAIN, 16));
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
			btn[i]=new JToggleButton((i+1)+"번 광고");
			panel.add(btn[i]);
			group.add(btn[i]);
			btn[i].addActionListener(this);
			btn[i].setBackground(new Color(225,51,51));
			btn[i].setForeground(Color.WHITE);
			btn[i].setFont(new Font("양재소슬체S", Font.PLAIN, 16));
		}
		

/**		//클래스 메소드 실행 - Advertisement5에서 구현함
		PP p=new PP();
		p.pp(W);*/
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		//이건 단순히 버튼에 사진 붙이는 이벤트
		Object obj=e.getSource();
		String[] picture= {"PC_1","PC_2","PC_3","PC_4","PC_5"}; //사진 이름들
		
		for(int i=0; i<btn.length; i++) {
			if(obj==btn[i]) {//해당 라디오 버튼이 클릭 되었을 경우
				lbl.setIcon(new ImageIcon(Winner2.class.getResource("/burger/"+picture[i]+".jpg"))); //그에 해당하는 사진 붙이기
			//}
		}
		}
		
		
	}
	
	
	
	//사진이 시간지나면 바뀌는 스레드에 넘겨줄 P주는 메소드 구현
	public void start(int P) {
		String[] picture= {"PC_1","PC_2","PC_3","PC_4","PC_5"};
		lbl.setIcon(new ImageIcon(Winner2.class.getResource("/burger/"+picture[P]+".jpg")));
		btn[P].setSelected(true);;
	}
	
	

}
