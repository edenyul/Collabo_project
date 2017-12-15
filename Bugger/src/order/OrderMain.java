package order;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Button;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import java.awt.ScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;
import javax.swing.event.ChangeEvent;

public class OrderMain extends JFrame implements ActionListener, MouseListener{
	
	private JPanel contentPane;
	public JTable table; //구입한 상품 표
	public DefaultTableModel model; //표를 담기 위한 모델
	private JTextField txtNum; //주문수량
	private JTextField txtSum; //주문금액
	private JTextField textField;
	public JPanel panel_4;
	private JLabel lblset1, lblset2, lblset3, lblset4, lblset5, lblset6;
	private int num=0; //테이블 개수 수량  체크

	private CardLayout card=new CardLayout(); //카드 레이아웃
	//시그니처 라디오박스 : 번
	private JRadioButton rbBun;
	//시그니처 체크박스
	private JCheckBox ckBeef, ckBacon, ckEgg, ckCheese, ckLettuce, ckOnion, ckMushroom, ckTomato, ckPickles;
	//선택취소 버튼, 
	private JButton btnckdel, btnAlldel, btnCash, bntCard;
	private JScrollPane scrollPane;
	private JSpinner spinner;
	private int spi1, spi2;
	
//------------- DB -------------------------------------
	
	private OrderDAO dao=new OrderDAO();
	private OrderVO vo=new OrderVO();
	private Vector<OrderVO> vec=new Vector<>();
	private Vector<String> str=new Vector<>();
	private int result=0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderMain fraem=new OrderMain();
					fraem.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderMain() {
		dao.deleteAll();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 100	, 100);
		setSize(620	, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel ppick = new JPanel();
		panel_2.add(ppick, BorderLayout.CENTER);
		ppick.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		ppick.add(panel_15, BorderLayout.EAST);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		btnckdel = new JButton("\uC120\uD0DD \uCDE8\uC18C");
		panel_15.add(btnckdel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_15.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("\uC218\uB7C9 \uC120\uD0DD");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 12));
		panel_1.add(lblNewLabel_4);
		
		//////////////////스피너///////////////////////////
		
		spinner = new JSpinner();
		
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(spinner.isEnabled()) { //스피너가 활성화 되어 있다면
					
					spi1=(int) spinner.getValue(); //변하는 스피너의 값을 저장
					
					int row=table.getSelectedRow(); //선택한 테이블의 행의 값을 저장
					
					result=dao.updateNum(spi1, row+1); //데이터 베이스의 값을 스피너와 동일하게 변경
					model.setValueAt(spi1, row, 3); //테이블도 스피너와 동일한 값을 가지도록 변경
					
				}
				
			}
		});
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinner.setEnabled(false);
		panel_1.add(spinner);
		
		////////////////////////////////////////////////
		
		JPanel panel_16 = new JPanel();
		ppick.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_16.add(scrollPane, BorderLayout.CENTER);
		
		/////////////////////테이블 클릭/////////////////////
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//1. 사용자가 테이블을 클릭(선택)했을 시 스피너가 작동 될수 있도록 enable=true
				//2. 스피너가 활성화 되면서 해당 테이블의 개수와 동일한 값으로 변경되도록 설정
				
					if(table.isEnabled()) {
						spinner.setEnabled(true);
						
						int row=table.getSelectedRow(); //선택한 테이블의 행의 값
						int colum=Integer.parseInt(String.valueOf(table.getValueAt(row, 3))); //선택한 테이블의 열(개수) 값
						
						spinner.setValue(colum); //스피너의 값을 테이블과 동일한 값으로 변경
						
					}else {
						spinner.setValue(1); //테이블이 선택되어 있지 않다면 스피너의 값을 초기화
						spinner.setEnabled(false); //다시 비활성화 시키기
					}
				
				}
				
			
		});
		table.setShowVerticalLines(false);
		table.setPreferredScrollableViewportSize(new Dimension(200,130));
		scrollPane.setViewportView(table);
		
		////////////////////////////////////////////////////
		
		JPanel pbuy = new JPanel();
		panel_2.add(pbuy, BorderLayout.SOUTH);
		pbuy.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		pbuy.add(panel_9, BorderLayout.WEST);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uC8FC\uBB38\uC218\uB7C9 : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblNewLabel_1);
		
		txtNum = new JTextField();
		panel_9.add(txtNum);
		txtNum.setColumns(10);
		
		JLabel label = new JLabel("\uC8FC\uBB38\uAE08\uC561 : ");
		label.setHorizontalAlignment(SwingConstants.CENTER); 
		panel_9.add(label);
		
		txtSum = new JTextField();
		txtSum.setColumns(10);
		panel_9.add(txtSum);
		
		JPanel panel_10 = new JPanel();
		pbuy.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnAlldel = new JButton("\uC804\uCCB4\uCDE8\uC18C");
		panel_10.add(btnAlldel);
		
		btnCash = new JButton("\uD604\uAE08\uACB0\uC81C");
		panel_10.add(btnCash);
		
		bntCard = new JButton("\uCE74\uB4DC\uACB0\uC81C");
		panel_10.add(bntCard);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("세트메뉴", null, panel_3, null);
		
		JPanel phambuger = new JPanel();
		tabbedPane.addTab("햄버거", null, phambuger, null);
		phambuger.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_26 = new JPanel();
		phambuger.add(panel_26, BorderLayout.SOUTH);
		
		JButton button = new JButton("\u25C0 \uC774\uC804");
		panel_26.add(button);
		
		JButton button_1 = new JButton("\uB2E4\uC74C \u25B6");
		panel_26.add(button_1);
		
		JPanel pside = new JPanel();
		tabbedPane.addTab("사이드", null, pside, null);
		
		JPanel pdrink = new JPanel();
		tabbedPane.addTab("음료", null, pdrink, null);
		
		JPanel psigniture = new JPanel();
		tabbedPane.addTab("시그니처", null, psigniture, null);
		psigniture.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		psigniture.add(panel_12, BorderLayout.NORTH);
		
		JLabel label_1 = new JLabel("\uC6D0\uD558\uB294 \uC7AC\uB8CC\uB97C \uC120\uD0DD\uD558\uC138\uC694");
		panel_12.add(label_1);
		
		JPanel panel_13 = new JPanel();
		psigniture.add(panel_13, BorderLayout.EAST);
		panel_13.setLayout(new GridLayout(0, 2, 0, 0));
		
		rbBun = new JRadioButton("\uBC88 (+3000\uC6D0)");
		rbBun.setEnabled(false);
		rbBun.setSelected(true);
		panel_13.add(rbBun);
		
		ckBeef = new JCheckBox("\uD328\uD2F0 (+1000\uCC9C)");
		panel_13.add(ckBeef);
		
		ckBacon = new JCheckBox("\uBCA0\uC774\uCEE8 (+700\uC6D0)");
		panel_13.add(ckBacon);
		
		ckEgg = new JCheckBox("\uACC4\uB780\uD6C4\uB77C\uC774 (+600\uC6D0)");
		panel_13.add(ckEgg);
		
		ckCheese = new JCheckBox("\uCE58\uC988 (+500\uC6D0)");
		panel_13.add(ckCheese);
		
		ckLettuce = new JCheckBox("\uC591\uC0C1\uCD94 (+600\uC6D0)");
		panel_13.add(ckLettuce);
		
		ckOnion = new JCheckBox("\uC591\uD30C (+400\uC6D0)");
		panel_13.add(ckOnion);
		
		ckMushroom = new JCheckBox("\uBC84\uC12F");
		panel_13.add(ckMushroom);
		
		ckTomato = new JCheckBox("\uD1A0\uB9C8\uD1A0");
		panel_13.add(ckTomato);
		
		ckPickles = new JCheckBox("\uD53C\uD074");
		panel_13.add(ckPickles);
		
		JPanel panel_14 = new JPanel();
		psigniture.add(panel_14, BorderLayout.CENTER);
		panel_14.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel imgBunTop = new JLabel("");
		imgBunTop.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSig/Bun-Top.png")));
		imgBunTop.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(imgBunTop);
		
		JLabel img1 = new JLabel("");
		img1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img1);
		
		JLabel img2 = new JLabel("");
		img2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img2);
		
		JLabel img3 = new JLabel("");
		img3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img3);
		
		JLabel img4 = new JLabel("");
		img4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img4);
		
		JLabel img5 = new JLabel("");
		img5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img5);
		
		JLabel img6 = new JLabel("");
		img6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img6);
		
		JLabel img7 = new JLabel("");
		img7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img7);
		
		JLabel img8 = new JLabel("");
		img8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img8);
		
		JLabel img9 = new JLabel("");
		img9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img9);
		
		JLabel imgBunBottom = new JLabel("");
		imgBunBottom.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSig/Bun-Bottom.png")));
		imgBunBottom.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(imgBunBottom);
		
		JPanel panel_17 = new JPanel();
		psigniture.add(panel_17, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("\uC9C4\uD589 \uAE08\uC561 : ");
		panel_17.add(lblNewLabel);
		
		textField = new JTextField();
		panel_17.add(textField);
		textField.setColumns(10);
		
		JButton btmmake = new JButton("\uC644\uC131");
		panel_17.add(btmmake);
		
		JPanel panel_20 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_20, null);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_20.add(panel_21, BorderLayout.NORTH);
		
		JPanel panel_22 = new JPanel();
		panel_20.add(panel_22, BorderLayout.WEST);
		panel_22.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSig/Bun-Top.png")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_7);
		
		JLabel label_8 = new JLabel("");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_9);
		
		JLabel label_10 = new JLabel("");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_10);
		
		JLabel label_11 = new JLabel("");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_11);
		
		JLabel label_12 = new JLabel("");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_12);
		
		JPanel panel_23 = new JPanel();
		panel_20.add(panel_23, BorderLayout.CENTER);
		
		JPanel ptitle = new JPanel();
		ptitle.setForeground(Color.LIGHT_GRAY);
		contentPane.add(ptitle, BorderLayout.NORTH);
		
		JLabel lbltitle = new JLabel("\uBC84\uAC70 \uC564 \uC790\uBC14");
		lbltitle.setFont(new Font("굴림", Font.BOLD, 18));
		ptitle.add(lbltitle);
		
		JPanel pbanner = new JPanel();
		contentPane.add(pbanner, BorderLayout.SOUTH);
		pbanner.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_25 = new JPanel();
		pbanner.add(panel_25, BorderLayout.SOUTH);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		panel_25.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		panel_25.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		panel_25.add(rdbtnNewRadioButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(OrderMain.class.getResource("/imgBanner/banner1.png")));
		pbanner.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("     ");
		pbanner.add(lblNewLabel_3, BorderLayout.NORTH);
		
		String columnNames[]= {"번호","메뉴","가격","개수"}; //=> 목록명 줌
		model=new DefaultTableModel(columnNames,0);
		table.setModel(model); //=> 사용함을 선언함
		
		DefaultTableCellRenderer render=new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel colum=table.getColumnModel();
		for(int i=0; i<colum.getColumnCount(); i++) {
			colum.getColumn(i).setCellRenderer(render);
		}
		
		OrderSetSide side=new OrderSetSide();
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(card);
		
		JPanel pset = new JPanel();
		panel_4.add(pset, "Pset");
		pset.setBackground(Color.ORANGE);
		pset.setLayout(new GridLayout(0, 3, 5, 5));
		//panel_4.add(side.getContentPane(),"Pside"); //OrderSetSide의 화면과 번갈아 보일 수 있도록 설정
		
		
		lblset1 = new JLabel("");
		lblset1.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/set1.png")));
		lblset1.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset1);
		
		lblset2 = new JLabel("");
		lblset2.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/set2.png")));
		lblset2.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset2);
		
		lblset3 = new JLabel("");
		lblset3.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/set3.png")));
		lblset3.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset3);
		
		lblset4 = new JLabel("");
		lblset4.setIcon(new ImageIcon(OrderMain.class.getResource("/imgBanner/\uC81C\uBAA9 \uC5C6\uC74C-1.jpg")));
		lblset4.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset4);
		
		lblset5 = new JLabel("\"\uC900\uBE44 \uC911\uC778 \uBA54\uB274 \uC785\uB2C8\uB2E4.\"");
		lblset5.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset5);
		
		lblset6 = new JLabel("\"\uC900\uBE44 \uC911\uC778 \uBA54\uB274 \uC785\uB2C8\uB2E4.\"");
		lblset6.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset6);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnMain = new JButton("\uBA54\uC778 \uD654\uBA74");
		btnMain.addActionListener(new ActionListener() { //'메인 화면' 버튼이 클릭 되었을 시
			public void actionPerformed(ActionEvent e) {
				change("Pset"); //햄버거 세트창으로 다시 이동
			}
		});
		panel_5.add(btnMain);
		
		
		lblset1.addMouseListener(this);
		lblset2.addMouseListener(this);
		lblset3.addMouseListener(this);
		lblset4.addMouseListener(this);
		lblset5.addMouseListener(this);
		lblset6.addMouseListener(this);
		
		btnckdel.addActionListener(this);
		btnAlldel.addActionListener(this);
		
		table ta=new table(); //테이블 갱신 클래스
		ta.start(); 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		
		
		
		
		
	}
	private int[] a=new int[6]; //테이블 번호
	private int[] b=new int[6]; //선택 메뉴의 개수
	private int[] set=new int[6];
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj=e.getSource();
		
		vec=dao.selectAll();
		
		if(obj==lblset1) {
			click(0,"햄버거 세트1",5000);
			
		}else if(obj==lblset2) {
			click(1,"햄버거 세트2",5500);
			
		}else if(obj==lblset3) {
			click(2,"햄버거 세트3",4000);
			
		}else if(obj==lblset4) {
			click(3,"햄버거 세트4",3500);
			
		}else if(obj==lblset5) {
			//click(4,"햄버거 세트5",5003);
			
		}else if(obj==lblset6) {
			//click(5);
		}
		
	}
	
	//패널 체인지
	public void change(String changepanel) {
		
		if(changepanel.equals("Pset")) { 
			card.show(panel_4, "Pset"); //메인화면으로 전환하기
			
		}else if(changepanel.endsWith("Pside")) { 
			card.show(panel_4, "Pside"); //OrderSetSide화면으로 전환해 사용자가 음료와 사이드를 선택할 수 있도록 변경
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	////////////////클릭시 이벤트 관리//////////////////
	public void click(int num,String menu, int price) {
		
		change("Pside"); //사이드와 음료를 선택할 수 있도록 패널 변경
		
		if(set[num]==0) { //어느 햄버거를 처음으로 선택했다면 
			
			vo.setMenu(menu); //해당 메뉴의 이름
			vo.setNo(vec.size()+1); //해당 메뉴의 번호
			vo.setNum(1); //해당 메뉴의 개수
			vo.setPrice(price); //해당 메뉴의 가격
			result=dao.insert(vo); //위의 정보를 DB에 저장
			
			a[num]=vec.size()+1; //해당 메뉴의 현재 번호
			b[num]=1; //해당 메뉴의 개수를 저장
			set[num]=1; //해당 햄버거가 이미 한번 선택되었다고 알리는 수
			
		}else { //어느 햄버거가 한번 이상 클릭 되었다면
			b[num]+=1; //해당 햄버거의 개수에 +1;
			if(b[num]>10) { //만약 햄버거의 선택 개수가 10을 초과했을 시
				JOptionPane.showMessageDialog(contentPane, "같은 메뉴의 최대 주문 개수는 10개 입니다.");
				
			}else { //최대 개수를 초과하지 않았다면
				result=dao.updateNum(b[num], a[num]); //DB에 해당 햄버거의 개수 정보 갱신 
				refresh(); //테이블 정보 갱신
			}
		}
	}
	
	/////////////////////////////////////////////
	
	
	
	//////////////테이블 DB관련 란////////////////////
	
	//테이블 계속 갱신
	class table extends Thread {
		public void run() {
			int minus=0; //차이 계산
			int plus=1;  //돌릴 숫자
		
		try {
			while(true) {
				vec=dao.selectAll();
				int dbNum=vec.size(); //DB의 개수
				int sum = 0;
				int Nsum = 0;
				
				///////////////////////////// 테이블에 내용 붙이기 //////////////////////////////
				
					if(model.getRowCount()>dbNum) {
						System.out.println("테이블의 개수가 크다");
					}else if(model.getRowCount()==dbNum) {
						
					}else if(model.getRowCount()<dbNum) { //만약 DB보다 테이블의 개수가 적다면
		
						Vector<String> svec=new Vector<>(); //테이블에 부족한 DB의 내용을 뿌리기 
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getNo()));
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getMenu()));
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getPrice()));
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getNum()));
									
						model.addRow(svec);
						
					}
							
				//////////////////////////// 선택 총 개수, 선택 총 가격 ///////////////////////////////
					
					for(OrderVO list : vec) { //현재 DB의 모든 테이블의 정보를 돌려
						sum+=list.getPrice()*list.getNum(); //'가격*개수=총 가격' 정보를 저장
						Nsum+=list.getNum(); //현재 선택되어 있는 메뉴의 개수를 저장
					}
					
					txtNum.setText(Nsum+""); //총 개수 정보를 텍스트 필드에 보여주기
					txtSum.setText(sum+""); //총 가격 정보를 텍스트 필드에 보여주기 
					
				sleep(150);
=======
	private String hambugerset;
	private CardLayout card=new CardLayout(); //카드 레이아웃
	//시그니처 라디오박스 : 번
	private JRadioButton rbBun;
	//시그니처 체크박스
	private JCheckBox ckBeef, ckBacon, ckEgg, ckCheese, ckLettuce, ckOnion, ckMushroom, ckTomato, ckPickles;
	//선택취소 버튼, 
	private JButton btnckdel, btnAlldel, btnCash, bntCard;
	private JScrollPane scrollPane;
	private JSpinner spinner;
	private int spi1, spi2;
	
//------------- DB -------------------------------------
	
	private OrderDAO dao=new OrderDAO();
	private OrderVO vo=new OrderVO();
	private Vector<OrderVO> vec=new Vector<>();
	private Vector<String> str=new Vector<>();
	private int result=0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderMain fraem=new OrderMain();
					fraem.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderMain() {
		dao.deleteAll();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 100	, 100);
		setSize(620	, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel ppick = new JPanel();
		panel_2.add(ppick, BorderLayout.CENTER);
		ppick.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		ppick.add(panel_15, BorderLayout.EAST);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		btnckdel = new JButton("\uC120\uD0DD \uCDE8\uC18C");
		panel_15.add(btnckdel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_15.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("\uC218\uB7C9 \uC120\uD0DD");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 12));
		panel_1.add(lblNewLabel_4);
		
		//////////////////스피너///////////////////////////
		
		spinner = new JSpinner();
		
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(spinner.isEnabled()) {
					spi1=(int) spinner.getValue();
					
					int row=table.getSelectedRow();
					
					result=dao.updateNum(spi1, row+1);
					model.setValueAt(spi1, row, 3);
					
				}
				
			}
		});
		spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinner.setEnabled(false);
		panel_1.add(spinner);
		
		////////////////////////////////////////////////
		
		JPanel panel_16 = new JPanel();
		ppick.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_16.add(scrollPane, BorderLayout.CENTER);
		
		/////////////////////테이블 클릭/////////////////////
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//사용자가 테이블을 선택하고 해당테이블에서 spinner를 조작시 개수 변경
				
					if(table.isEnabled()) {
						spinner.setEnabled(true);
						
						int row=table.getSelectedRow();
						int colum=Integer.parseInt(String.valueOf(table.getValueAt(row, 3)));
						
						spinner.setValue(colum);
						spi2=(int) spinner.getValue();
					}else {
						spinner.setValue(1);
						spinner.setEnabled(false);
					}
				
				}
				
			
		});
		table.setShowVerticalLines(false);
		table.setPreferredScrollableViewportSize(new Dimension(200,130));
		scrollPane.setViewportView(table);
		
		////////////////////////////////////////////////////
		
		JPanel pbuy = new JPanel();
		panel_2.add(pbuy, BorderLayout.SOUTH);
		pbuy.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		pbuy.add(panel_9, BorderLayout.WEST);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uC8FC\uBB38\uC218\uB7C9 : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblNewLabel_1);
		
		txtNum = new JTextField();
		panel_9.add(txtNum);
		txtNum.setColumns(10);
		
		JLabel label = new JLabel("\uC8FC\uBB38\uAE08\uC561 : ");
		label.setHorizontalAlignment(SwingConstants.CENTER); 
		panel_9.add(label);
		
		txtSum = new JTextField();
		txtSum.setColumns(10);
		panel_9.add(txtSum);
		
		JPanel panel_10 = new JPanel();
		pbuy.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnAlldel = new JButton("\uC804\uCCB4\uCDE8\uC18C");
		panel_10.add(btnAlldel);
		
		btnCash = new JButton("\uD604\uAE08\uACB0\uC81C");
		panel_10.add(btnCash);
		
		bntCard = new JButton("\uCE74\uB4DC\uACB0\uC81C");
		panel_10.add(bntCard);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("세트메뉴", null, panel_3, null);
		
		JPanel phambuger = new JPanel();
		tabbedPane.addTab("햄버거", null, phambuger, null);
		phambuger.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_26 = new JPanel();
		phambuger.add(panel_26, BorderLayout.SOUTH);
		
		JButton button = new JButton("\u25C0 \uC774\uC804");
		panel_26.add(button);
		
		JButton button_1 = new JButton("\uB2E4\uC74C \u25B6");
		panel_26.add(button_1);
		
		JPanel pside = new JPanel();
		tabbedPane.addTab("사이드", null, pside, null);
		
		JPanel pdrink = new JPanel();
		tabbedPane.addTab("음료", null, pdrink, null);
		
		JPanel psigniture = new JPanel();
		tabbedPane.addTab("시그니처", null, psigniture, null);
		psigniture.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		psigniture.add(panel_12, BorderLayout.NORTH);
		
		JLabel label_1 = new JLabel("\uC6D0\uD558\uB294 \uC7AC\uB8CC\uB97C \uC120\uD0DD\uD558\uC138\uC694");
		panel_12.add(label_1);
		
		JPanel panel_13 = new JPanel();
		psigniture.add(panel_13, BorderLayout.EAST);
		panel_13.setLayout(new GridLayout(0, 2, 0, 0));
		
		rbBun = new JRadioButton("\uBC88 (+3000\uC6D0)");
		rbBun.setEnabled(false);
		rbBun.setSelected(true);
		panel_13.add(rbBun);
		
		ckBeef = new JCheckBox("\uD328\uD2F0 (+1000\uCC9C)");
		panel_13.add(ckBeef);
		
		ckBacon = new JCheckBox("\uBCA0\uC774\uCEE8 (+700\uC6D0)");
		panel_13.add(ckBacon);
		
		ckEgg = new JCheckBox("\uACC4\uB780\uD6C4\uB77C\uC774 (+600\uC6D0)");
		panel_13.add(ckEgg);
		
		ckCheese = new JCheckBox("\uCE58\uC988 (+500\uC6D0)");
		panel_13.add(ckCheese);
		
		ckLettuce = new JCheckBox("\uC591\uC0C1\uCD94 (+600\uC6D0)");
		panel_13.add(ckLettuce);
		
		ckOnion = new JCheckBox("\uC591\uD30C (+400\uC6D0)");
		panel_13.add(ckOnion);
		
		ckMushroom = new JCheckBox("\uBC84\uC12F");
		panel_13.add(ckMushroom);
		
		ckTomato = new JCheckBox("\uD1A0\uB9C8\uD1A0");
		panel_13.add(ckTomato);
		
		ckPickles = new JCheckBox("\uD53C\uD074");
		panel_13.add(ckPickles);
		
		JPanel panel_14 = new JPanel();
		psigniture.add(panel_14, BorderLayout.CENTER);
		panel_14.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel imgBunTop = new JLabel("");
		imgBunTop.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSig/Bun-Top.png")));
		imgBunTop.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(imgBunTop);
		
		JLabel img1 = new JLabel("");
		img1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img1);
		
		JLabel img2 = new JLabel("");
		img2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img2);
		
		JLabel img3 = new JLabel("");
		img3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img3);
		
		JLabel img4 = new JLabel("");
		img4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img4);
		
		JLabel img5 = new JLabel("");
		img5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img5);
		
		JLabel img6 = new JLabel("");
		img6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img6);
		
		JLabel img7 = new JLabel("");
		img7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img7);
		
		JLabel img8 = new JLabel("");
		img8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img8);
		
		JLabel img9 = new JLabel("");
		img9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(img9);
		
		JLabel imgBunBottom = new JLabel("");
		imgBunBottom.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSig/Bun-Bottom.png")));
		imgBunBottom.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(imgBunBottom);
		
		JPanel panel_17 = new JPanel();
		psigniture.add(panel_17, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("\uC9C4\uD589 \uAE08\uC561 : ");
		panel_17.add(lblNewLabel);
		
		textField = new JTextField();
		panel_17.add(textField);
		textField.setColumns(10);
		
		JButton btmmake = new JButton("\uC644\uC131");
		panel_17.add(btmmake);
		
		JPanel panel_20 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_20, null);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_20.add(panel_21, BorderLayout.NORTH);
		
		JPanel panel_22 = new JPanel();
		panel_20.add(panel_22, BorderLayout.WEST);
		panel_22.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSig/Bun-Top.png")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_7);
		
		JLabel label_8 = new JLabel("");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_9);
		
		JLabel label_10 = new JLabel("");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_10);
		
		JLabel label_11 = new JLabel("");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_11);
		
		JLabel label_12 = new JLabel("");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(label_12);
		
		JPanel panel_23 = new JPanel();
		panel_20.add(panel_23, BorderLayout.CENTER);
		
		JPanel ptitle = new JPanel();
		ptitle.setForeground(Color.LIGHT_GRAY);
		contentPane.add(ptitle, BorderLayout.NORTH);
		
		JLabel lbltitle = new JLabel("\uBC84\uAC70 \uC564 \uC790\uBC14");
		lbltitle.setFont(new Font("굴림", Font.BOLD, 18));
		ptitle.add(lbltitle);
		
		JPanel pbanner = new JPanel();
		contentPane.add(pbanner, BorderLayout.SOUTH);
		pbanner.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_25 = new JPanel();
		pbanner.add(panel_25, BorderLayout.SOUTH);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		panel_25.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		panel_25.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		panel_25.add(rdbtnNewRadioButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(OrderMain.class.getResource("/imgBanner/banner1.png")));
		pbanner.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("     ");
		pbanner.add(lblNewLabel_3, BorderLayout.NORTH);
		
		String columnNames[]= {"번호","메뉴","가격","개수"}; //=> 목록명 줌
		model=new DefaultTableModel(columnNames,0);
		table.setModel(model); //=> 사용함을 선언함
		
		DefaultTableCellRenderer render=new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel colum=table.getColumnModel();
		for(int i=0; i<colum.getColumnCount(); i++) {
			colum.getColumn(i).setCellRenderer(render);
		}
		
		OrderSetSide side=new OrderSetSide();
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(card);
		
		JPanel pset = new JPanel();
		panel_4.add(pset, "Pset");
		pset.setBackground(Color.ORANGE);
		pset.setLayout(new GridLayout(0, 3, 5, 5));
		//panel_4.add(side.getContentPane(),"Pside");
		
		
		lblset1 = new JLabel("");
		lblset1.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/set1.png")));
		lblset1.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset1);
		
		lblset2 = new JLabel("");
		lblset2.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/set2.png")));
		lblset2.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset2);
		
		lblset3 = new JLabel("");
		lblset3.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/set3.png")));
		lblset3.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset3);
		
		lblset4 = new JLabel("");
		lblset4.setIcon(new ImageIcon(OrderMain.class.getResource("/imgBanner/\uC81C\uBAA9 \uC5C6\uC74C-1.jpg")));
		lblset4.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset4);
		
		lblset5 = new JLabel("\"\uC900\uBE44 \uC911\uC778 \uBA54\uB274 \uC785\uB2C8\uB2E4.\"");
		lblset5.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset5);
		
		lblset6 = new JLabel("\"\uC900\uBE44 \uC911\uC778 \uBA54\uB274 \uC785\uB2C8\uB2E4.\"");
		lblset6.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset6);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnMain = new JButton("\uBA54\uC778 \uD654\uBA74");
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change("Pset");
			}
		});
		panel_5.add(btnMain);
		
		
		lblset1.addMouseListener(this);
		lblset2.addMouseListener(this);
		lblset3.addMouseListener(this);
		lblset4.addMouseListener(this);
		lblset5.addMouseListener(this);
		lblset6.addMouseListener(this);
		
		table ta=new table();
		ta.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//체크박스 생성
		JCheckBox chk = (JCheckBox) e.getSource();
		
		
	}
	private int[] a=new int[6]; //테이블 번호
	private int[] b=new int[6]; //선택 메뉴의 개수
	private int[] set=new int[6];
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj=e.getSource();
		OrderSetSide side=new OrderSetSide();
		vec=dao.selectAll();
		
		if(obj==lblset1) {
			click(0,"햄버거 세트1",5000);
			
		}else if(obj==lblset2) {
			click(1,"햄버거 세트2",5500);
			
		}else if(obj==lblset3) {
			click(2,"햄버거 세트3",4000);
			
		}else if(obj==lblset4) {
			click(3,"햄버거 세트4",3500);
			
		}else if(obj==lblset5) {
			//click(4,"햄버거 세트5",5003);
			
		}else if(obj==lblset6) {
			//click(5);
		}
		
	}
	
	//패널 체인지
	public void change(String changepanel) {
		
		if(changepanel.equals("Pset")) {
			card.show(panel_4, "Pset");
		}else if(changepanel.endsWith("Pside")) {
			card.show(panel_4, "Pside");
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	////////////////클릭시 이벤트 관리//////////////////
	public void click(int num,String menu, int price) {
		change("Pside");
		if(set[num]==0) {
			vo.setMenu(menu);
			vo.setNo(vec.size()+1);
			vo.setNum(1);
			vo.setPrice(price);
			result=dao.insert(vo);
			
			a[num]=vec.size()+1;
			b[num]=1;
			set[num]=1;
		}else {
			b[num]+=1;
			if(b[num]>10) {
				JOptionPane.showMessageDialog(contentPane, "같은 메뉴의 최대 주문 개수는 10개 입니다.");
			}else {
				result=dao.updateNum(b[num], a[num]);
				refresh();
			}
		}
	}
	
	/////////////////////////////////////////////
	
	
	
	//////////////테이블 DB관련 란////////////////////
	
	//테이블 계속 갱신
	class table extends Thread {
		public void run() {
			int minus=0; //차이 계산
			int plus=1;  //돌릴 숫자
		
		try {
			while(true) {
				vec=dao.selectAll();
				int dbNum=vec.size();
	
					if(model.getRowCount()>dbNum) {
						System.out.println("테이블의 개수가 크다");
					}else if(model.getRowCount()==dbNum) {
						
					}else if(model.getRowCount()<dbNum) {
		
						Vector<String> svec=new Vector<>();
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getNo()));
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getMenu()));
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getPrice()));
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getNum()));
									
						model.addRow(svec);
						System.out.println("확인");	
					}
							
				
					
				sleep(100);

			}//while
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}//run()	
	}//class
	
	//테이블 다시 출력
	public void refresh() {
		Vector<Object> obj;
		vec=dao.selectAll();
		
		String columnNames[]= {"번호","메뉴","가격","개수"};
		model = new DefaultTableModel(columnNames, 0);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		//vector가 가지고 있는 데이터 jtable에 보여주기
		for(OrderVO list : vec) {
			obj=new Vector<>();
			
			obj.addElement(list.getNo());
			obj.addElement(list.getMenu());
			obj.addElement(list.getPrice());
			obj.addElement(list.getNum());
			
			model.addRow(obj);
		}
	
		DefaultTableCellRenderer render=new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel colum=table.getColumnModel();
		for(int i=0; i<colum.getColumnCount(); i++) {
			colum.getColumn(i).setCellRenderer(render);
		}
		
	}//refresh();
	
}
