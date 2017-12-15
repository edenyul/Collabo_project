package order;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class OrderSetSide extends JFrame implements MouseListener{

	private JPanel contentPane;
	private JLabel[] lblSide;
	private JLabel[] lblBeverage;
	private CardLayout card=new CardLayout();
	private String stBever, stSide;
	private int iBever, ISide, spiNum=1, no, result=0;
	private Vector<OrderVO> vec=new Vector<>();
	private OrderDAO dao=new OrderDAO();
	private OrderVO vo=new OrderVO();
	
	

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSetSide frame = new OrderSetSide();
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
	public OrderSetSide() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(card);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		panel_3.add(separator, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_5.add(panel_10, BorderLayout.NORTH);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblnull4 = new JLabel("  ");
		panel_10.add(lblnull4);
		
		JLabel label_1 = new JLabel("beverage");
		panel_10.add(label_1);
		
		JLabel lblnull5 = new JLabel("  ");
		panel_5.add(lblnull5, BorderLayout.WEST);
		
		JLabel lblnull6 = new JLabel("  ");
		panel_5.add(lblnull6, BorderLayout.EAST);
		
		JLabel lblnull7 = new JLabel("  ");
		panel_5.add(lblnull7, BorderLayout.SOUTH);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new GridLayout(0, 5, 0, 0));
		
		lblBeverage=new JLabel[10];
		
		for(int i=0; i<lblBeverage.length; i++) {
			lblBeverage[i]=new JLabel("음료"+i);
			panel_7.add(lblBeverage[i]);
			lblBeverage[i].addMouseListener(this);
			//lblBeverage[i].setIcon(new ImageIcon(OrderSetSide.class.getSimpleName("//"+name+".jpg")));
		}
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSeparator separator_2 = new JSeparator();
		panel_9.add(separator_2);
		
		JLabel label = new JLabel("sideMenu");
		panel_9.add(label);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new GridLayout(0, 5, 0, 0));
		
		lblSide=new JLabel[10];
		for(int i=0; i<lblSide.length; i++) {
			lblSide[i]=new JLabel("햄버거"+i);
			panel_8.add(lblSide[i]);
			lblSide[i].addMouseListener(this);
			//lblSide[i].setIcon(new ImageIcon(OrderSetSide.class.getSimpleName("//"+name+".jpg")));
		}
		
		JLabel lblnull8 = new JLabel("  ");
		panel_6.add(lblnull8, BorderLayout.SOUTH);
		
		JLabel lblnull9 = new JLabel("  ");
		panel_6.add(lblnull9, BorderLayout.WEST);
		
		JLabel lblnull10 = new JLabel("  ");
		panel_6.add(lblnull10, BorderLayout.EAST);
		setSize(620,560);
	}
	private int[] a=new int[10];
	private int[] b=new int[10];
	
	public void mouseClicked(java.awt.event.MouseEvent e) {
		Object obj=e.getSource();
		vec=dao.selectAll();
		
		//음료
		String[] sBeverage= {"콜라L","콜라M","사이다L","사이다M","환타L","환타M","아메리카노L","아메리카노M","",""};
		int[] iBeverage= {500, 0, 500, 0, 500, 0, 700, 200, 0, 0};
		
		for(int i=0; i<lblBeverage.length; i++) {
			if(obj==lblBeverage[i]) {
				no=vec.size()+1;
				stBever=sBeverage[i];
				iBever=iBeverage[i];
				
				update(no,stBever,iBever,1, i, a, stBever);
			}else {//if
				
			}
			
		}//for
		
	
		
		//사이드
		String[] sSide= {"감자 튀김","양파 튀김","오징어 튀김","치즈스틱","치킨 너겟","","","","",""};
		int[] iSide= {0, 500, 500, 700, 700, 0, 0, 0, 0, 0};
		
		for(int i=0; i<lblSide.length; i++) {
			if(obj==lblSide[i]) {
				no=vec.size()+1;
				stSide=sSide[i];
				ISide=iSide[i];
				
				update(no, stSide, ISide, 1, i, b, stSide);
			}
		}
		
		if(vo.getMenu()!=null) {
			
		}
	}
	
	public void update(int no, String menu, int price, int num, int i, int[] ab, String menu2) {
		vec=dao.selectAll();
		
		if(ab[i]==0 && !menu2.equals("")) {
			vo.setNo(no);
			vo.setMenu(menu);
			vo.setNum(num);
			vo.setPrice(price);
			
			dao.insert(vo);
			ab[i]=1;
		}else if(ab[i]==1 && !menu2.equals("")){
			JOptionPane.showMessageDialog(contentPane, "음료수의 개수 변경은 밑의 버튼을 조작해 주세요.");
		}else {
			JOptionPane.showMessageDialog(contentPane, "해당 메뉴는 준비 중 입니다.");
		}
	
	}

	
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {}
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {}
	

}
