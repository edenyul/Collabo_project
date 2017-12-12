package test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.spi.CalendarDataProvider;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;

public class BannerTest implements ActionListener{

	private JPanel contentPane;
	private JRadioButton[] rabtn; //라디오 버튼
	private JLabel lblNewLabel; //사진을 붙일 라벨
	private  int P=0, P2=0; //일정 시간마다 사진을 변경하기 위함
	private int num=0; //일정 시간을 재기 위함
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new BannerTest();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BannerTest() {
		JFrame j=new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		j.setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BannerTest3 ba=new BannerTest3();
				ba.start(P2);
				ba.setVisible(true);
			}
		});
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		ButtonGroup group=new ButtonGroup(); //중복 선택을 못하게 하기 위한 그룹선언.
		rabtn=new JRadioButton[5]; //라디오 버튼은 총 5개!
		
		for(int i=0; i<rabtn.length; i++) {
			rabtn[i]=new JRadioButton();
			rabtn[i].setText(String.valueOf(i+1)); //라디오 이름 
			panel_1.add(rabtn[i]); // 패널1에 붙이기
			group.add(rabtn[i]); //그룹에 담기
			rabtn[i].addActionListener(this); //액션 모션
		}
		lblNewLabel.setIcon(new ImageIcon(BannerTest.class.getResource("/test/43.jpg")));
		rabtn[0].setSelected(true);
		
		
		j.setVisible(true);
		
		Cal c=new Cal(); //초를 재기 위한 class
		c.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj=arg0.getSource();
		int[] picture= {43,44,45,46,47}; //사진 이름들
		
		
		for(int i=0; i<rabtn.length; i++) {
			if(obj==rabtn[i]) {//해당 라디오 버튼이 클릭 되었을 경우
				lblNewLabel.setIcon(new ImageIcon(BannerTest.class.getResource("/test/"+picture[i]+".jpg"))); //그에 해당하는 사진 붙이기
				P=i; //특정 시간이 되면 다음으로 변경하기 위해  현재의 사진이 무엇인지 저장.
				P2=P;
				num=0;
			}
		}

	}
	
	class Cal extends Thread{
		public void run() {
			
			Calendar cal=Calendar.getInstance();
			String sec=null;
			int[] picture= {43,44,45,46,47}; //사진 이름들
			
			while(true) { //계속 돌림
				try {

				cal=Calendar.getInstance();
				sec = (cal.get(Calendar.SECOND)<10?"0":"")+cal.get(Calendar.SECOND);
				
				sleep(1000); //1초마다 쉬어가기
				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(sec+" "+num);
				num++; //1초가 지나면 num++
				
				if(num%5==0) { //5초가 지났을 경우
					P=P+1; //다음 사진 선택
					P2=P;
					if(lblNewLabel.getIcon()!=null) {//라벨지에 사진이 보여지고 있다면
						lblNewLabel.setIcon(new ImageIcon(BannerTest.class.getResource("/test/"+picture[P]+".jpg"))); //다음 사진 라벨에 붙이기
						rabtn[P].setSelected(true);//버튼도 같이 다음으로 이동
					}
				}
				
				if(P==4) {//5번째 사진이 선택되어 있다면
					P=-1; //다시 1번째 사진으로 이동
				}
				System.out.println("잘했당");
			
			}
			
		}
	}
	
}
