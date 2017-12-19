package order;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class OrderMain extends JFrame implements ActionListener, MouseListener, ItemListener{
	
	private JPanel contentPane;
	public JTable table; //구입한 상품 표
	public DefaultTableModel model; //표를 담기 위한 모델
	private JTextField txtNum; //주문수량
	private JTextField txtSum; //주문금액
	public JPanel panel_4;
	private JLabel lblset1, lblset2, lblset3, lblset4, lblset5, lblset6;
	//private int num=0; //테이블 개수 수량  체크

	private CardLayout card=new CardLayout(); //카드 레이아웃
	//선택취소 버튼, 
	private JButton btnckdel, btnAlldel, btnCash, btnCard;
	private JScrollPane scrollPane;
	private JSpinner spinner;
	private int spi1, spi2;
	
//---- 시그니처 부분 ------------------------------------------------------------------

	//재료 이미지를 보여줄 레이블
	private JLabel[] lblmake;
	//선택할 수 있는 재료 이름
	private JCheckBox chkmake[]=new JCheckBox[9];
	//선택할 수 있는 재료 이름
	private String bgName[]= {"패티 (+1000원)", "베이컨 (+700원)", "계란후라이 (+600원)", "치즈 (+500원)","양상추 (+600원)",
			"양파 (+400원)","버섯 (+500원)","토마토 (+500원)","피클 (+400원)"};
	private ImageIcon icon[]=new ImageIcon[9];

	private JTextField textField_1;
	
	//시그 재료 총합
	private int sigsum=3000;
	private JButton button_2;
	private int num1=1;//시그이름숫자 증가
	
//------------- DB -------------------------------------
	
	private OrderDAO dao=new OrderDAO();
	private OrderVO vo=new OrderVO();
	private Vector<OrderVO> vec=new Vector<>();
	//private Vector<String> str=new Vector<>();
	private int result=0;
	
	private int[] a=new int[6]; //테이블 번호
	private int[] b=new int[6]; //선택 메뉴의 개수
	private int[] set=new int[6];
	private int H;
	
//------------- ADVERTISEMENT --------------------------

	public JRadioButton rabtn[];	//라디오 버튼
	private JLabel lblNewLabel_2;		//사진을 붙일 라벨
	private int P;	//일정 시간마다 사진을 변경하기 위함
	private int num=0; //시간을 재기 위한 함수
	//private JLabel NewAmazingLabel;	//서브프레임에 띄울 사진을 붙일 라벨
	
	public String[] picture= {"m_1","m_2","m_3","m_4","m_5"};
		

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
		btnckdel.setForeground(Color.WHITE);
		btnckdel.setFont(new Font("새굴림", Font.BOLD, 12));
		btnckdel.setBackground(Color.DARK_GRAY);
		panel_15.add(btnckdel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_15.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("\uC218\uB7C9 \uC120\uD0DD");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_1.add(lblNewLabel_4);
		
		//////////////////스피너///////////////////////////
		
		spinner = new JSpinner();
		spinner.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if(spinner.isEnabled()) { //스피너가 활성화 되어 있다면
					
					spi1=(int) spinner.getValue(); //변하는 스피너의 값을 저장
					
					int row=table.getSelectedRow(); //선택한 테이블의 행의 값을 저장
					
					if(row!=-1) {
						result=dao.updateNum(spi1, row+1); //데이터 베이스의 값을 스피너와 동일하게 변경
						model.setValueAt(spi1, row, 3); //테이블도 스피너와 동일한 값을 가지도록 변경	
					}
				}//스피너 활성화 여부 if
				
			}
		});
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinner.setEnabled(false);
		panel_1.add(spinner);
		
		////////////////////////////////////////////////
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(new Color(255, 255, 204));
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
		panel_9.setBackground(new Color(255, 255, 204));
		pbuy.add(panel_9, BorderLayout.WEST);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uC8FC\uBB38\uC218\uB7C9 : ");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblNewLabel_1);
		
		txtNum = new JTextField();
		panel_9.add(txtNum);
		txtNum.setColumns(10);
		
		JLabel label = new JLabel("\uC8FC\uBB38\uAE08\uC561 : ");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label.setBackground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER); 
		panel_9.add(label);
		
		txtSum = new JTextField();
		txtSum.setColumns(10);
		panel_9.add(txtSum);
		
		JPanel panel_10 = new JPanel();
		pbuy.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnAlldel = new JButton("\uC804\uCCB4\uCDE8\uC18C");
		btnAlldel.setForeground(Color.WHITE);
		btnAlldel.setFont(new Font("새굴림", Font.BOLD, 16));
		btnAlldel.setBackground(Color.DARK_GRAY);
		panel_10.add(btnAlldel);
		
		btnCash = new JButton("\uD604\uAE08\uACB0\uC81C");
		btnCash.setFont(new Font("새굴림", Font.BOLD, 16));
		btnCash.setForeground(Color.WHITE);
		btnCash.setBackground(new Color(0, 51, 204));
		panel_10.add(btnCash);
		
		btnCard = new JButton("\uCE74\uB4DC\uACB0\uC81C");
		btnCard.setFont(new Font("새굴림", Font.BOLD, 16));
		btnCard.setForeground(Color.WHITE);
		btnCard.setBackground(new Color(255, 51, 51));
		panel_10.add(btnCard);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 204));
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("세트메뉴", null, panel_3, null);
		
		JPanel phambuger = new JPanel();
		phambuger.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("햄버거", null, phambuger, null);
		phambuger.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_26 = new JPanel();
		phambuger.add(panel_26, BorderLayout.SOUTH);
		
		JButton button = new JButton("\u25C0 \uC774\uC804");
		button.setBackground(new Color(255, 51, 51));
		button.setFont(new Font("굴림체", Font.BOLD, 12));
		button.setForeground(new Color(255, 255, 255));
		panel_26.add(button);
		
		JButton button_1 = new JButton("\uB2E4\uC74C \u25B6");
		button_1.setBackground(new Color(255, 51, 51));
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setFont(new Font("굴림체", Font.BOLD, 12));
		panel_26.add(button_1);
		
		JPanel pside = new JPanel();
		pside.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("사이드", null, pside, null);
		
		JPanel pdrink = new JPanel();
		pdrink.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("음료", null, pdrink, null);
		
		JPanel panel_20 = new JPanel();
		tabbedPane.addTab("시그니처", null, panel_20, null);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_21.setBackground(new Color(255, 255, 255));
		panel_20.add(panel_21, BorderLayout.NORTH);
		
		JLabel lblNewLable_5 = new JLabel("\uC6D0\uD558\uB294 \uC7AC\uB8CC\uB97C \uC120\uD0DD\uD558\uC138\uC694");
		lblNewLable_5.setForeground(new Color(153, 51, 0));
		lblNewLable_5.setFont(new Font("새굴림", Font.BOLD, 12));
		panel_21.add(lblNewLable_5);
		
		JPanel panel_6 = new JPanel();
		panel_20.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		//이미지 나오는 패널
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		panel_8.setLayout(new GridLayout(0,1,0,0));
		lblmake =new  JLabel[9];
		
		for(int i=0; i<lblmake.length; i++) { //공간을 만들어준다.
			lblmake[i] =new JLabel();
			lblmake[i].setBackground(new Color(255, 255, 255));
			panel_8.add(lblmake[i]);
		}		
		panel_6.add(panel_8);
		
		//체크 박스 넣는 패널
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(255, 255, 255));
		panel_11.setLayout(new GridLayout(0,1,0,0));
		//첫번째 패널에 체크박스  add
		for(int i=0;i<chkmake.length;i++) {
			//체크박스 객체 생성해서 배열에 담기
			chkmake[i]=new JCheckBox(bgName[i]);
			chkmake[i].setFont(new Font("새굴림", Font.BOLD, 12));
			chkmake[i].setForeground(new Color(153, 51, 0));
			chkmake[i].setBackground(new Color(255, 255, 255));
			
			panel_11.add(chkmake[i]);
			//생성된 체크박스를 text에  add
		}
		panel_6.add(panel_11);
		
		//이벤트 리스너 붙이기
		for(int i=0;i<chkmake.length;i++)
			chkmake[i].addItemListener(this);
		
		JPanel panel_7 = new JPanel();
		panel_20.add(panel_7, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_6 = new JLabel("\uC9C4\uD589 \uAE08\uC561 : ");
		panel_7.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_7.add(textField_1);
		
		button_2 = new JButton("\uC644\uC131");
		button_2.setFont(new Font("새굴림", Font.BOLD, 12));
		button_2.setForeground(new Color(255, 255, 255));
		button_2.setBackground(new Color(255, 51, 51));
		panel_7.add(button_2);
		
		JPanel ptitle = new JPanel();
		ptitle.setBackground(Color.ORANGE);
		ptitle.setForeground(Color.LIGHT_GRAY);
		contentPane.add(ptitle, BorderLayout.NORTH);
		
		JLabel lbltitle = new JLabel();
		lbltitle.setIcon(new ImageIcon(OrderMain.class.getResource("/order/main_img.png")));
		lbltitle.setForeground(Color.BLACK);
		lbltitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		ptitle.add(lbltitle);
		
		JPanel pbanner = new JPanel();
		pbanner.setBackground(Color.ORANGE);
		contentPane.add(pbanner, BorderLayout.SOUTH);
		pbanner.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_25 = new JPanel();
		panel_25.setBackground(Color.ORANGE);
		pbanner.add(panel_25, BorderLayout.SOUTH);
		
//------------- ADVERTISEMENT --------------------------
		
		//--/**인애*/
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(OrderMain.class.getResource("/burger/m_1.jpg")));
		pbanner.add(lblNewLabel_2, BorderLayout.CENTER);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg1) {
				Object obj=arg1.getSource();
				/** 3.광고 사진 클릭 시 자세한 내용이 표시된 사진 새 창 출력 */
				
				System.out.println("mouseClick");	//테스트
				
				//JDialog창 띄우기
				Winner2 win2=new Winner2();
				win2.setVisible(true);
				
				/////////////////////////////////////////////////
				///새창띄우기까지 완료///	
				
				
				JLabel lbl=win2.lbl;
				
				for(int i=0;i<picture.length;i++) {
					if(P==i) {	//P가 i번째 라면
						//이미지 띄우기 효과
						System.out.println("p값 "+P+"==========================");
						lbl.setIcon(new ImageIcon(Winner2.class.getResource("/burger/"+win2.winner[P]+".jpg")));
						
						//버튼 클릭 효과
						win2.start(P);
					}
				}
				/*//이미지 띄우기 효과
				System.out.println("p값 "+P+"==========================");
				lbl.setIcon(new ImageIcon(Winner2.class.getResource("/burger/"+win2.winner[P]+".jpg")));
				//버튼 클릭 효과
				win2.btn[P].setSelected(true);*/
			}
			
		});
//------------- ADVERTISEMENT --------------------------
	
		JLabel lblNewLabel_3 = new JLabel("     ");
		pbanner.add(lblNewLabel_3, BorderLayout.NORTH);
		
		String columnNames[]= {"번호","메뉴","가격","개수"}; //=> 목록명 줌
		model=new DefaultTableModel(columnNames,0);
		table.setModel(model); //=> 사용함을 선언함
		
		table.getTableHeader().setReorderingAllowed(false); //컬럼 이동 방지
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //다중 선택 방지
		
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
		panel_4.add(side.getContentPane(),"Pside"); //OrderSetSide의 화면과 번갈아 보일 수 있도록 설정
		
		
		lblset1 = new JLabel("");
		lblset1.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/setm1.jpg")));
		lblset1.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset1);
		
		lblset2 = new JLabel("");
		lblset2.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/setm2.jpg")));
		lblset2.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset2);
		
		lblset3 = new JLabel("");
		lblset3.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/setm3.jpg")));
		lblset3.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset3);
		
		lblset4 = new JLabel("");
		lblset4.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/setm4.jpg")));
		lblset4.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset4);
		
		lblset5 = new JLabel("/imgSet/setm5.jpg");
		lblset5.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset5);
		
		lblset6 = new JLabel("/imgSet/setm6.jpg");
		lblset6.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset6);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnMain = new JButton("\uBA54\uC778 \uD654\uBA74");
		btnMain.setFont(new Font("새굴림", Font.BOLD, 12));
		btnMain.setForeground(Color.WHITE);
		btnMain.setBackground(new Color(0, 51, 204));
		btnMain.addActionListener(new ActionListener() { //'메인 화면' 버튼이 클릭 되었을 시
			public void actionPerformed(ActionEvent e) {
				change("Pset"); //햄버거 세트창으로 다시 이동
			}
		});
		panel_5.add(btnMain);
		
/**------------- ADVERTISEMENT --------------------------*/
	
		ButtonGroup group=new ButtonGroup(); //중복 선택을 못하게 하기 위한 그룹선언.
		rabtn=new JRadioButton[5];	//라디오 버튼 총 5개 배열 선언
		
		for(int i=0; i<rabtn.length; i++) {
			rabtn[i]=new JRadioButton();
			rabtn[i].setText(String.valueOf(i+1)); //라디오 이름 
			panel_25.add(rabtn[i]);	//패널 1에 넣기
			group.add(rabtn[i]);	//그룹에 담기
			rabtn[i].addActionListener(this);	//액션 이벤트
			rabtn[i].setBackground(Color.ORANGE);//버튼 색 
		}
		
		rabtn[0].setSelected(true);
		
		
		Cal c=new Cal();	//초를 재기 위한 class 호출
		c.start();
		
		
/**------------- ADVERTISEMENT --------------------------	*/

		
		
		lblset1.addMouseListener(this);
		lblset2.addMouseListener(this);
		lblset3.addMouseListener(this);
		lblset4.addMouseListener(this);
		lblset5.addMouseListener(this);
		lblset6.addMouseListener(this);
		
		btnckdel.addActionListener(this);
		btnAlldel.addActionListener(this);
		button_2.addActionListener(this);
		
		table ta=new table(); //테이블 갱신 클래스
		ta.start(); 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		
		if(obj==btnckdel) { //선택 취소
			int row=table.getSelectedRow(); //선택한 테이블의 열
			row2=row;
			String mm=String.valueOf(model.getValueAt(row, 1));
			
			//테이블의 시작 : 0 , DB의 시작 : 1
			result=dao.delete(row+1); //선택한 해당 번호와 일치하는 DB내용 삭제
			model.removeRow(row);
			
			vec=dao.selectAll(); //번호를 바꾸기 위해 전체 검색
			
			String[] str=new String[vec.size()]; //번호를 바꾸기 위한 기준점을 저장할 배열
			int k=0; //str배열의 번호
			for(OrderVO list : vec) { 
				str[k]=list.getMenu(); //전체검색으로 나오는 모든 메뉴들을 배열에 저장
				k++; //배열번호 증가
			}
			
			for(int i=row; i<vec.size(); i++) { //삭제한 번호를 기준으로 DB의 끝까지
				result=dao.updateNo(i+1, str[i]); //DB의 번호를 차례로 바꿔나가기
			}
			
		/*	//햄버거 세트가 주문메뉴에 없을 때 세트의 음료나 사이드도 같이 없애기
			if(H==0){
				vec=dao.selectAll();
				for(OrderVO list : vec) {
					for(int i=0; i<vec.size(); i++) {
						if(vec.get(i).getMenu().indexOf("S")!=-1) { //(S)는 세트의 음료나 사이드라는 뜻.
							dao.delete(i+1);
							System.out.println(vec.get(i).getMenu());
							System.out.println(H);
						}
					}
				}
				change("Pset");
			}*/
			
			sideNum();
			refresh(); //바뀐내용을 갱신
			
			////////////////////////////////////
			for(int i=0; i<6; i++) {
				if(mm.equals("햄버거 세트"+(i+1))) {
					set[i]=0;
					b[i]=0;
					a[i]=0;
				}
			}
			
		}else if(obj==btnAlldel) { //전체 취소
			dao.deleteAll(); //데이터 베이스 정보 전체 삭제
			refresh(); // 테이블 정보 전체 초기화
			change("Pset"); //햄버거 선택화면으로 돌아가기
			spinner.setEnabled(false); //스피너 선택 창 다시 비활성화
			spinner.setValue(1); //스피너가 1의 값을 가지도록 설정
			
			for(int i=0; i<a.length; i++) { //햄버거 정보 값들 초기화
				a[i]=0;
				b[i]=0;
				set[i]=0;
			}//for
			
			
		}else if(obj==btnCash) { //현금 결제
			
		}else if(obj==btnCard) { //카드 결제
			
		}else if(obj==button_2) {//완성
			vec=dao.selectAll();
			
			vo.setNo(vec.size()+1);//사이즈 번호
			vo.setMenu("시그니처"+num1);
			vo.setPrice(Integer.parseInt(textField_1.getText()));
			vo.setNum(1);
			dao.insert(vo);
			num1++;
			//이전에 선택된거 해제
			for(int i=0; i<9; i++)
			lblmake[i].setIcon(null);
			
			textField_1.setText(null);
			//체크 해제
			for(int i=0; i<9; i++)
			chkmake[i].setSelected(false);
		}
		
	/**------------- ADVERTISEMENT --------------------------*/

		for(int i=0; i<rabtn.length;i++) {
			if(obj==rabtn[i]) {//해당 라디오 버튼이 클릭 되었을 경우
				lblNewLabel_2.setIcon(new ImageIcon(OrderMain.class.getResource("/burger/"+picture[i]+".jpg"))); //그에 해당하는 사진 붙이기
				P=i; //특정 시간이 되면 다음으로 변경하기 위해  현재의 사진이 무엇인지 저장.
				num=0;
			}
		}
		
		
		
	}//action listner
	
	
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
			click(4,"햄버거 세트5",5003);
			
		}else if(obj==lblset6) {
			click(5,"햄버거 세트6",1324);
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
	
	//사용하지 않는 메소드들
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
			
			vec=dao.selectAll();
			
			a[num]=vec.size(); //해당 메뉴의 현재 번호
			b[num]=1; //해당 메뉴의 개수를 저장
			set[num]=1; //해당 햄버거가 이미 한번 선택되었다고 알리는 수
			
		}else if(set[num]==1){ //어느 햄버거가 한번 이상 클릭 되었다면
			b[num]=vec.get(a[num]-1).getNum()+1; //해당 햄버거의 개수에 +1;
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
		
			try {
		
			while(true) {
				vec=dao.selectAll();
				int dbNum=vec.size(); //DB의 개수
				int sum = 0;
				int Nsum = 0;
				
				///////////////////////////// 테이블에 내용 붙이기 //////////////////////////////
				
					if(model.getRowCount()>dbNum) {
						//refresh();
						System.out.println("테이블 개수가 크다");
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
						
				////////////// 번호의 오류 /////////////////////////////////////////////////////////
					
					vec=dao.selectAll();
					if(vec.size()!=0 && vec.get(0).getNo()!=1) {
						dao.updateNo(1, vec.get(0).getMenu());
						
						for(int i=1; i<vec.size(); i++) {
							dao.updateNo(i+1, vec.get(i).getMenu());
						}
						
						refresh();
					}
					
					vec=dao.selectAll();
					String[] str1= {"햄버거 세트1","햄버거 세트2","햄버거 세트3","햄버거 세트4","햄버거 세트5","햄버거 세트6"};
					for(int i=0; i<vec.size(); i++) {
						for(int j=0; j<str1.length; j++) {
							if(vec.get(i).getMenu().equals(str1[j])) {
								a[j]=vec.get(i).getNo();
								break;
							}
						}
					}
				
				///////////////////// 세트메뉴 종합 삭제 ///////////////////////////////////////////////
				
					System.out.println(H);
					
				///////////////////////////////////////////////////////////////////////////
					
					if(table.getSelectedRow()==-1) {
						spinner.setValue(1);
						spinner.setEnabled(false);
					}
					
				/////////////////////////////////////////	
				sideNum();
				
				sleep(100);
					
			}//while
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//catch
		
		
		}//run
	
	}//thread class

	//테이블 다시 출력
	public void refresh() {
		Vector<Object> obj;
		vec=dao.selectAll();
		
		table.removeAll();
		
		String columnNames[]= {"번호","메뉴","가격","개수"};
		model = new DefaultTableModel(columnNames, 0);
		table.setModel(model);
		
		//vector가 가지고 있는 데이터 jtable에 보여주기
		for(OrderVO list : vec) {
			obj=new Vector<>();
			
			obj.addElement(list.getNo());
			obj.addElement(list.getMenu());
			obj.addElement(list.getPrice());
			obj.addElement(list.getNum());
			
			model.addRow(obj);
		}
	
		table.getTableHeader().setReorderingAllowed(false); //컬럼 이동 방지
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //다중 선택 방지
		
		//테이블을 가운데 정렬시키기 위한 소스코드
		DefaultTableCellRenderer render=new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel colum=table.getColumnModel();
		for(int i=0; i<colum.getColumnCount(); i++) {
			colum.getColumn(i).setCellRenderer(render);
		}
		
	}//refresh();

	private int row2, B2, S2;
	//선택된 세트의 개수보다 많은 음료나 사이드를 선택하지 못하게 만듦
   //햄버거 세트 선택 개수보다 음료나 사이드의 개수를 더 많이 선택하면 가격을 더 받기
	public void sideNum() {
		int row=table.getSelectedRow();
		
		vec=dao.selectAll();
		String[] sBeverage= {"(S)콜라L","(S)콜라M","(S)사이다L","(S)사이다M","(S)환타L","(S)환타M","(S)아메리카노L","(S)아메리카노M","",""};
		String[] sSide= {"(S)감자 튀김","(S)양파 튀김","(S)오징어 튀김","(S)치즈스틱","(S)치킨 너겟","","","","",""};
		
		int B=0, S=0; //음료 총 개수, 사이드 총 개수, 세트 총 개수
		H=0;
		for(OrderVO list : vec) {
			for(int i=0; i<10; i++) {
				if(list.getMenu().equals(sBeverage[i])) {//세트 음료 개수
					B+=list.getNum();
					B2+=1;
					break;
				}else if(list.getMenu().equals(sSide[i])) {//세트 사이드 메뉴 개수
					S+=list.getNum();
					S2+=1;
					break;
				}else if(list.getMenu().indexOf("세트")!=-1) {//세트 햄버거 개수
					H+=list.getNum();
					break;
				}
			}
		}
		
		//////////// 사이드 메뉴 //////////////////
		if(H<S && H!=0) {	
			side(sSide, row, "사이드 메뉴");
		///////////// 음료 //////////////////////
		}else if(H<B && H!=0) {
			side(sBeverage, row, "음료");
		}else if(H==0) {
			vec=dao.selectAll();
			if(vec.size()!=0) {
				for(OrderVO list : vec) {
					for(int i=0; i<vec.size(); i++) {
						if(vec.get(i).getMenu().indexOf("S")!=-1) { //(S)는 세트의 음료나 사이드라는 뜻.
							dao.delete(i+1);
						}
					}
				}
				change("Pset");
			}
		}
		
	}//sideNum
	
	public void side(String[] BS, int row, String sBS) {
		 int conform=0;
		 
		 for(String list : BS) {
			 String me=vec.get(vec.size()-1).getMenu();
			 if(me.indexOf("세트")==-1 && me.equals(list)) {
				conform=1;
				break;
			 }else if(me.indexOf("세트")==-1 && !me.equals(list)){
				conform=2;
			 }else {
				conform=3;
			 }
		 }
		 
		 vec=dao.selectAll();
		
		 if(conform==1) {
			 try {
				 if(spi1-1<=0) {
					 dao.delete(vec.get(vec.size()-1).getNo());
					 model.removeRow(vec.size()-1);	
					 
					 if(vec.size()!=vec.get(vec.size()-1).getNo()) {
						dao.delete(vec.get(vec.size()-1).getNo());
					}
				 }else {
					result=dao.updateNum(spi1-1, row+1);
					model.setValueAt(spi1-1, row, 3);
					spinner.setValue(spi1-1);
				 }
			 }catch(ArrayIndexOutOfBoundsException e){
				 model.removeRow(vec.size()-2);	
			 }
			
		 }else if(conform==2) {
			if(spi1-1>0) {
				try {
					result=dao.updateNum(spi1-1, row+1);
					model.setValueAt(spi1-1, row, 3);
					spinner.setValue(spi1-1);
				}catch(ArrayIndexOutOfBoundsException e) {
					for(int i=1; i<=vec.size(); i++) {
						dao.updateNum(1, i);
					}
					refresh();
				}
			}else {
				try {
					 dao.delete(vec.get(vec.size()).getNo());
					 model.removeRow(vec.size()-1);			 
				 }catch(ArrayIndexOutOfBoundsException e){
					 model.removeRow(vec.size()-2);	
				 }
			}
		
		 }else {
			if(row!=-1) {
				result=dao.updateNum(spi1-1, row+1);
				model.setValueAt(spi1-1, row, 3);
				spinner.setValue(spi1-1);
				refresh();
			}else {
				for(int i=1; i<=vec.size(); i++) {
					dao.updateNum(1, i);
				}
				refresh();
			}
			 
		 }//eles
		 
		 JOptionPane.showMessageDialog(contentPane, "세트보다 많은 "+sBS+"를 주문하실 수 없습니다.");
	}//side
	
	///////////////// 시그니처 //////////////////////
	
	@Override
	public void itemStateChanged(ItemEvent e) { //시그니처 
		JCheckBox check=(JCheckBox) e.getItem();
		
		int price[]= {1000, 700, 600, 500, 600, 400, 500, 500, 400};
		sigsum=0;
		for(int i=0;i<9;i++) {
				
			if(chkmake[i].isSelected()) { //선택되면 => 이미지
				lblmake[i].setIcon(new ImageIcon(OrderMain.class.getResource("/imgSig/"+bgName[i]+".png")));
					sigsum+=price[i];			
					textField_1.setText(sigsum+""); //넣어줄 값을 string
				
			}else {
			
				lblmake[i].setIcon(null);
				if(chkmake[i].isSelected()) {
					sigsum+=price[i];
				}
				textField_1.setText(sigsum+"");
				
			}//6ge
		}
		//기본 값인 번 3000원 
		if(sigsum!=0) {
			textField_1.setText(sigsum+3000+"");
		}
		System.out.println(sigsum);
	}//end item

	/**------------- ADVERTISEMENT --------------------------*/
	class Cal extends Thread{
		public void run() {
			
			Calendar cal=Calendar.getInstance();
			String sec=null;
			String[] picture= {"m_1","m_2","m_3","m_4","m_5"};	//사진이름
			
			while(true) {	//계속 돌림-슬라이드쇼
				try {
					cal=Calendar.getInstance();
					sec=(cal.get(Calendar.SECOND)<10?"0":"")+cal.get(Calendar.SECOND);
					
					sleep(1000); //1초마다 쉬기
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
		;
				num++; //1초가 지나면 num++
				
				if(num%5==0) { //5초가 지났을 경우
					if(P<5) {						
						P++;
						
						if(P==5) {
							P=0;
						}
						
						lblNewLabel_2.setIcon(new ImageIcon(OrderMain.class.getResource("/burger/"+picture[P]+".jpg"))); //다음 사진 라벨에 붙이기
						rabtn[P].setSelected(true);//버튼도 같이 다음으로 이동						
					}
				}
		
			}//while
		}//run
		
	}//cal
	
	
}