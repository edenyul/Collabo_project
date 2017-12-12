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
	private JRadioButton[] rabtn; //���� ��ư
	private JLabel lblNewLabel; //������ ���� ��
	private  int P=0, P2=0; //���� �ð����� ������ �����ϱ� ����
	private int num=0; //���� �ð��� ��� ����
	
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
		
		ButtonGroup group=new ButtonGroup(); //�ߺ� ������ ���ϰ� �ϱ� ���� �׷켱��.
		rabtn=new JRadioButton[5]; //���� ��ư�� �� 5��!
		
		for(int i=0; i<rabtn.length; i++) {
			rabtn[i]=new JRadioButton();
			rabtn[i].setText(String.valueOf(i+1)); //���� �̸� 
			panel_1.add(rabtn[i]); // �г�1�� ���̱�
			group.add(rabtn[i]); //�׷쿡 ���
			rabtn[i].addActionListener(this); //�׼� ���
		}
		lblNewLabel.setIcon(new ImageIcon(BannerTest.class.getResource("/test/43.jpg")));
		rabtn[0].setSelected(true);
		
		
		j.setVisible(true);
		
		Cal c=new Cal(); //�ʸ� ��� ���� class
		c.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj=arg0.getSource();
		int[] picture= {43,44,45,46,47}; //���� �̸���
		
		
		for(int i=0; i<rabtn.length; i++) {
			if(obj==rabtn[i]) {//�ش� ���� ��ư�� Ŭ�� �Ǿ��� ���
				lblNewLabel.setIcon(new ImageIcon(BannerTest.class.getResource("/test/"+picture[i]+".jpg"))); //�׿� �ش��ϴ� ���� ���̱�
				P=i; //Ư�� �ð��� �Ǹ� �������� �����ϱ� ����  ������ ������ �������� ����.
				P2=P;
				num=0;
			}
		}

	}
	
	class Cal extends Thread{
		public void run() {
			
			Calendar cal=Calendar.getInstance();
			String sec=null;
			int[] picture= {43,44,45,46,47}; //���� �̸���
			
			while(true) { //��� ����
				try {

				cal=Calendar.getInstance();
				sec = (cal.get(Calendar.SECOND)<10?"0":"")+cal.get(Calendar.SECOND);
				
				sleep(1000); //1�ʸ��� �����
				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(sec+" "+num);
				num++; //1�ʰ� ������ num++
				
				if(num%5==0) { //5�ʰ� ������ ���
					P=P+1; //���� ���� ����
					P2=P;
					if(lblNewLabel.getIcon()!=null) {//������ ������ �������� �ִٸ�
						lblNewLabel.setIcon(new ImageIcon(BannerTest.class.getResource("/test/"+picture[P]+".jpg"))); //���� ���� �󺧿� ���̱�
						rabtn[P].setSelected(true);//��ư�� ���� �������� �̵�
					}
				}
				
				if(P==4) {//5��° ������ ���õǾ� �ִٸ�
					P=-1; //�ٽ� 1��° �������� �̵�
				}
				System.out.println("���ߴ�");
			
			}
			
		}
	}
	
}
