package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class BannerTest2 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JToggleButton btn[];
	private JLabel lbl;
	
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BannerTest2 frame = new BannerTest2();
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
	public BannerTest2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lbl = new JLabel("");
		contentPane.add(lbl, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		btn=new JToggleButton[5];
		ButtonGroup group=new ButtonGroup();
		for(int i=0; i<btn.length; i++) {
			btn[i]=new JToggleButton((i+1)+"번 사진");
			panel.add(btn[i]);
			group.add(btn[i]);
			btn[i].addActionListener(this);
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		int[] picture= {43,44,45,46,47}; //사진 이름들
		
		for(int i=0; i<btn.length; i++) {
			if(obj==btn[i]) {//해당 라디오 버튼이 클릭 되었을 경우
				lbl.setIcon(new ImageIcon(BannerTest2.class.getResource("/test/"+picture[i]+".jpg"))); //그에 해당하는 사진 붙이기
			}
		}
	}
	
	public void start(int P) {
		int[] picture= {43,44,45,46,47};
		lbl.setIcon(new ImageIcon(BannerTest2.class.getResource("/test/"+picture[P]+".jpg")));
		btn[P].isSelected();
	}
}
