package order;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	public JTable table; //������ ��ǰ ǥ
	public DefaultTableModel model; //ǥ�� ��� ���� ��
	private JTextField txtNum; //�ֹ�����
	private JTextField txtSum; //�ֹ��ݾ�
	public JPanel panel_4;
	private JLabel lblset1, lblset2, lblset3, lblset4, lblset5, lblset6;
	private JLabel[] hamburgerM, beverageM, SideM;

	private CardLayout card=new CardLayout(); //ī�� ���̾ƿ�
	//������� ��ư, 
	private JButton btnckdel, btnAlldel, btnCash, btnCard;
	private JScrollPane scrollPane;
	private JSpinner spinner;
	private int spi1, spi2;
	
	private int[] HsetM=new int[8];
	private int[] SsetM=new int[10];
	private int[] BsetM=new int[10];

//---- �ñ״�ó �κ� ------------------------------------------------------------------

	//��� �̹����� ������ ���̺�
	private JLabel[] lblmake;
	//������ �� �ִ� ��� �̸�
	private JCheckBox chkmake[]=new JCheckBox[9];
	//������ �� �ִ� ��� �̸�
	private String bgName[]= {"��Ƽ (+1000��)", "������ (+700��)", "����Ķ��� (+600��)", "ġ�� (+500��)","����� (+600��)",
			"���� (+400��)","���� (+500��)","�丶�� (+500��)","��Ŭ (+400��)"};
	private ImageIcon icon[]=new ImageIcon[9];

	private JTextField textField_1;
	
	//�ñ� ��� ����
	private int sigsum=3000;
	private JButton button_2;
	private int num1=1;//�ñ��̸����� ����
	
//------------- DB -------------------------------------
	
	private OrderDAO dao=new OrderDAO();
	private OrderVO vo=new OrderVO();
	private Vector<OrderVO> vec=new Vector<>();
	private int result=0;
	
	private int[] a=new int[6]; //���̺� ��ȣ
	private int[] b=new int[6]; //���� �޴��� ����
	private int[] set=new int[6];
	private int H;
	
//------------- ADVERTISEMENT --------------------------

	public JRadioButton rabtn[];	//���� ��ư
	private JLabel lblNewLabel_2;		//������ ���� ��
	private int P;	//���� �ð����� ������ �����ϱ� ����
	private int num=0; //�ð��� ��� ���� �Լ�
	
	public String[] picture= {"m_1","m_2","m_3","m_4","m_5"};
		
	private JTabbedPane tabbedPane;
	
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fraem=new OrderMain();
					fraem.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public OrderMain() {
		
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
		btnckdel.setFont(new Font("������", Font.BOLD, 12));
		btnckdel.setBackground(Color.DARK_GRAY);
		panel_15.add(btnckdel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_15.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("\uC218\uB7C9 \uC120\uD0DD");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("���� ����", Font.BOLD, 12));
		panel_1.add(lblNewLabel_4);
		
		//////////////////���ǳ�///////////////////////////
		
		spinner = new JSpinner();
		spinner.setFont(new Font("���� ����", Font.BOLD, 12));
		
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if(spinner.isEnabled()) { //���ǳʰ� Ȱ��ȭ �Ǿ� �ִٸ�
					
					spi1=(int) spinner.getValue(); //���ϴ� ���ǳ��� ���� ����
					
					int row=table.getSelectedRow(); //������ ���̺��� ���� ���� ����
					
					if(row!=-1) {
						result=dao.updateNum(spi1, row+1); //������ ���̽��� ���� ���ǳʿ� �����ϰ� ����
						model.setValueAt(spi1, row, 3); //���̺��� ���ǳʿ� ������ ���� �������� ����	
					}
				}//���ǳ� Ȱ��ȭ ���� if
				
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
		
		/////////////////////���̺� Ŭ��/////////////////////
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//1. ����ڰ� ���̺��� Ŭ��(����)���� �� ���ǳʰ� �۵� �ɼ� �ֵ��� enable=true
				//2. ���ǳʰ� Ȱ��ȭ �Ǹ鼭 �ش� ���̺��� ������ ������ ������ ����ǵ��� ����
				
					if(table.isEnabled()) {
						spinner.setEnabled(true);
						
						int row=table.getSelectedRow(); //������ ���̺��� ���� ��
						int colum=Integer.parseInt(String.valueOf(table.getValueAt(row, 3))); //������ ���̺��� ��(����) ��
						
						spinner.setValue(colum); //���ǳ��� ���� ���̺��� ������ ������ ����
						
					}else {
						spinner.setValue(1); //���̺��� ���õǾ� ���� �ʴٸ� ���ǳ��� ���� �ʱ�ȭ
						spinner.setEnabled(false); //�ٽ� ��Ȱ��ȭ ��Ű��
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
		lblNewLabel_1.setFont(new Font("���� ����", Font.BOLD, 12));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblNewLabel_1);
		
		txtNum = new JTextField();
		panel_9.add(txtNum);
		txtNum.setColumns(10);
		
		JLabel label = new JLabel("\uC8FC\uBB38\uAE08\uC561 : ");
		label.setFont(new Font("���� ����", Font.BOLD, 12));
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
		btnAlldel.setFont(new Font("������", Font.BOLD, 16));
		btnAlldel.setBackground(Color.DARK_GRAY);
		panel_10.add(btnAlldel);
		
		btnCash = new JButton("\uD604\uAE08\uACB0\uC81C");
		btnCash.setFont(new Font("������", Font.BOLD, 16));
		btnCash.setForeground(Color.WHITE);
		btnCash.setBackground(new Color(0, 51, 204));
		panel_10.add(btnCash);
		
		btnCard = new JButton("\uCE74\uB4DC\uACB0\uC81C");
		btnCard.setFont(new Font("������", Font.BOLD, 16));
		btnCard.setForeground(Color.WHITE);
		btnCard.setBackground(new Color(255, 51, 51));
		panel_10.add(btnCard);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 204));
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("��Ʈ�޴�", null, panel_3, null);
		
		JPanel panel_13 = new JPanel();
		panel_13.setToolTipText("");
		tabbedPane.addTab("\uD584\uBC84\uAC70", null, panel_13, null);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_13.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel phambuger = new JPanel();
		phambuger.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(phambuger);
		phambuger.setLayout(new GridLayout(0, 4, 0, 0));
		
		hamburgerM = new JLabel[8];
		for(int i=0; i<hamburgerM.length; i++) {
			hamburgerM[i]=new JLabel("");
			hamburgerM[i].setIcon(new ImageIcon(OrderMain.class.getResource("/imgBugger/set"+(i+1)+".jpg")));
			phambuger.add(hamburgerM[i]);
			hamburgerM[i].addMouseListener(this);
		}
		
		JPanel panel_14 = new JPanel();
		tabbedPane.addTab("\uC0AC\uC774\uB4DC", null, panel_14, null);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_14.add(scrollPane_2, BorderLayout.CENTER);
		
		JPanel pside = new JPanel();
		pside.setBackground(Color.WHITE);
		scrollPane_2.setViewportView(pside);
		pside.setLayout(new GridLayout(0, 3, 0, 0));
		
		SideM = new JLabel[10];
		for(int i=0; i<SideM.length; i++) {
			SideM[i]=new JLabel("");
			SideM[i].setIcon(new ImageIcon(OrderMain.class.getResource("/imgside/Bsid"+(i+1)+".jpg")));
			pside.add(SideM[i]);
			SideM[i].addMouseListener(this);
		}
		
		JPanel panel_17 = new JPanel();
		tabbedPane.addTab("\uC74C\uB8CC", null, panel_17, null);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_17.add(scrollPane_3, BorderLayout.CENTER);
		
		JPanel pdrink = new JPanel();
		pdrink.setBackground(Color.WHITE);
		scrollPane_3.setViewportView(pdrink);
		pdrink.setLayout(new GridLayout(0, 3, 0, 0));
		
		beverageM = new JLabel[10];
		for(int i=0; i<SideM.length-1; i++) {
			beverageM[i]=new JLabel("");
			beverageM[i].setIcon(new ImageIcon(OrderMain.class.getResource("/imgBeverage/Bdrl"+(i+1)+".jpg")));
			pdrink.add(beverageM[i]);
			beverageM[i].addMouseListener(this);
		}
		
		JPanel panel_20 = new JPanel();
		tabbedPane.addTab("�ñ״�ó", null, panel_20, null);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_21.setBackground(new Color(255, 255, 255));
		panel_20.add(panel_21, BorderLayout.NORTH);
		
		JLabel lblNewLable_5 = new JLabel("\uC6D0\uD558\uB294 \uC7AC\uB8CC\uB97C \uC120\uD0DD\uD558\uC138\uC694");
		lblNewLable_5.setForeground(new Color(153, 51, 0));
		lblNewLable_5.setFont(new Font("������", Font.BOLD, 12));
		panel_21.add(lblNewLable_5);
		
		JPanel panel_6 = new JPanel();
		panel_20.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		//�̹��� ������ �г�
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		panel_8.setLayout(new GridLayout(0,1,0,0));
		lblmake =new  JLabel[9];
		
		for(int i=0; i<lblmake.length; i++) { //������ ������ش�.
			lblmake[i] =new JLabel();
			lblmake[i].setBackground(new Color(255, 255, 255));
			panel_8.add(lblmake[i]);
		}		
		panel_6.add(panel_8);
		
		//üũ �ڽ� �ִ� �г�
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(255, 255, 255));
		panel_11.setLayout(new GridLayout(0,1,0,0));
		//ù��° �гο� üũ�ڽ�  add
		for(int i=0;i<chkmake.length;i++) {
			//üũ�ڽ� ��ü �����ؼ� �迭�� ���
			chkmake[i]=new JCheckBox(bgName[i]);
			chkmake[i].setFont(new Font("������", Font.BOLD, 12));
			chkmake[i].setForeground(new Color(153, 51, 0));
			chkmake[i].setBackground(new Color(255, 255, 255));
			
			panel_11.add(chkmake[i]);
			//������ üũ�ڽ��� text��  add
		}
		panel_6.add(panel_11);
		
		//�̺�Ʈ ������ ���̱�
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
		button_2.setFont(new Font("������", Font.BOLD, 12));
		button_2.setForeground(new Color(255, 255, 255));
		button_2.setBackground(new Color(255, 51, 51));
		panel_7.add(button_2);
		
		JPanel ptitle = new JPanel();
		ptitle.setBackground(Color.ORANGE);
		ptitle.setForeground(Color.LIGHT_GRAY);
		contentPane.add(ptitle, BorderLayout.NORTH);
		ptitle.setLayout(new BorderLayout(0, 0));
		
		JLabel lbltitle = new JLabel();
		lbltitle.setIcon(new ImageIcon(OrderMain.class.getResource("/order/main_img.png")));
		lbltitle.setForeground(Color.BLACK);
		lbltitle.setFont(new Font("���� ����", Font.BOLD, 18));
		ptitle.add(lbltitle);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.ORANGE);
		panel_12.setForeground(Color.ORANGE);
		ptitle.add(panel_12, BorderLayout.NORTH);
		panel_12.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("\uB85C\uADF8\uC544\uC6C3");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dao.deleteAll();
				refresh();
				dispose();
				
				MemberLoginTest login=new MemberLoginTest();
				login.setVisible(true);
			}
		});
		lblNewLabel.setForeground(new Color(139, 0, 0));
		lblNewLabel.setFont(new Font("����", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_12.add(lblNewLabel);
		
		JPanel pbanner = new JPanel();
		pbanner.setBackground(Color.ORANGE);
		contentPane.add(pbanner, BorderLayout.SOUTH);
		pbanner.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_25 = new JPanel();
		panel_25.setBackground(Color.ORANGE);
		pbanner.add(panel_25, BorderLayout.SOUTH);
		
//------------- ADVERTISEMENT --------------------------
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(OrderMain.class.getResource("/burger/m_1.jpg")));
		pbanner.add(lblNewLabel_2, BorderLayout.CENTER);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg1) {
				/** 3.���� ���� Ŭ�� �� �ڼ��� ������ ǥ�õ� ���� �� â ��� */
				
				System.out.println("mouseClick");	//�׽�Ʈ
				
				//JDialogâ ����
				Winner2 win2=new Winner2();
				win2.setVisible(true);
				
				/////////////////////////////////////////////////
				///��â������� �Ϸ�///	
				
				
				JLabel lbl=win2.lbl;
				
				for(int i=0;i<picture.length;i++) {
					if(P==i) {	//P�� i��° ���
						//�̹��� ���� ȿ��
						System.out.println("p�� "+P+"==========================");
						lbl.setIcon(new ImageIcon(Winner2.class.getResource("/burger/"+win2.winner[P]+".jpg")));
						
						//��ư Ŭ�� ȿ��
						win2.start(P);
					}
				}
		
			}
			
		});
//------------- ADVERTISEMENT --------------------------
	
		JLabel lblNewLabel_3 = new JLabel("     ");
		pbanner.add(lblNewLabel_3, BorderLayout.NORTH);
		
		String columnNames[]= {"��ȣ","�޴�","����","����"}; //=> ��ϸ� ��
		model=new DefaultTableModel(columnNames,0);
		table.setModel(model); //=> ������� ������
		
		table.getTableHeader().setReorderingAllowed(false); //�÷� �̵� ����
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //���� ���� ����
		
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
		panel_4.add(side.getContentPane(),"Pside"); //OrderSetSide�� ȭ��� ������ ���� �� �ֵ��� ����
		
		
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
		
		lblset5 = new JLabel("");
		lblset5.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/setm5.jpg")));
		lblset5.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset5);
		
		lblset6 = new JLabel("");
		lblset6.setIcon(new ImageIcon(OrderMain.class.getResource("/imgSet/setm6.jpg")));
		lblset6.setHorizontalAlignment(SwingConstants.CENTER);
		pset.add(lblset6);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnMain = new JButton("\uBA54\uC778 \uD654\uBA74");
		btnMain.setFont(new Font("������", Font.BOLD, 12));
		btnMain.setForeground(Color.WHITE);
		btnMain.setBackground(new Color(0, 51, 204));
		btnMain.addActionListener(new ActionListener() { //'���� ȭ��' ��ư�� Ŭ�� �Ǿ��� ��
			public void actionPerformed(ActionEvent e) {
				change("Pset"); //�ܹ��� ��Ʈâ���� �ٽ� �̵�
			}
		});
		panel_5.add(btnMain);
		
/**------------- ADVERTISEMENT --------------------------*/
	
		ButtonGroup group=new ButtonGroup(); //�ߺ� ������ ���ϰ� �ϱ� ���� �׷켱��.
		rabtn=new JRadioButton[5];	//���� ��ư �� 5�� �迭 ����
		
		for(int i=0; i<rabtn.length; i++) {
			rabtn[i]=new JRadioButton();
			rabtn[i].setText(String.valueOf(i+1)); //���� �̸� 
			panel_25.add(rabtn[i]);	//�г� 1�� �ֱ�
			group.add(rabtn[i]);	//�׷쿡 ���
			rabtn[i].addActionListener(this);	//�׼� �̺�Ʈ
			rabtn[i].setBackground(Color.ORANGE);//��ư �� 
		}
		
		rabtn[0].setSelected(true);
		
		
		Cal c=new Cal();	//�ʸ� ��� ���� class ȣ��
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
		
		btnCard.addActionListener(this);
		btnCash.addActionListener(this);
		
		table ta=new table(); //���̺� ���� Ŭ����
		ta.start(); 

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		
		//���� ����ȭ �ϱ� ���� ����
		BuypopUp pop = new BuypopUp();
		BuypopCash cpop = new BuypopCash();
		
		if(obj==btnckdel) { //���� ���
			if(table.getSelectedRow()!=-1) {
				int row=table.getSelectedRow(); //������ ���̺��� ��
			
				String mm=String.valueOf(model.getValueAt(row, 1));
				
				//���̺��� ���� : 0 , DB�� ���� : 1
				result=dao.delete(row+1); //������ �ش� ��ȣ�� ��ġ�ϴ� DB���� ����
				model.removeRow(row);
				
				vec=dao.selectAll(); //��ȣ�� �ٲٱ� ���� ��ü �˻�
				
				String[] str=new String[vec.size()]; //��ȣ�� �ٲٱ� ���� �������� ������ �迭
				int k=0; //str�迭�� ��ȣ
				for(OrderVO list : vec) { 
					str[k]=list.getMenu(); //��ü�˻����� ������ ��� �޴����� �迭�� ����
					k++; //�迭��ȣ ����
				}
				
				for(int i=row; i<vec.size(); i++) { //������ ��ȣ�� �������� DB�� ������
					result=dao.updateNo(i+1, str[i]); //DB�� ��ȣ�� ���ʷ� �ٲ㳪����
				}
				
				sideNum();
				refresh(); //�ٲﳻ���� ����
				
				////////////////////////////////////
				String[] str1= {"���� �ִϾ� ��Ʈ","ġ�� ���� �ִϾ� ��Ʈ","�Ұ��� ġ�� ���� ��Ʈ","��ġŲ ���� ��Ʈ","ġ�� ���� ��Ʈ","�Ұ��� ���� ��Ʈ"};
				String[] sBeverage= {"�ݶ�L","�ݶ�R","���̴�L","���̴�R","ȯŸ ������L","ȯŸ ������R","�Ƹ޸�ī��","���̽� �Ƹ޸�ī��","������",""};
				String[] sSide= {"����ġ ������","���ڳ� ������ 3����","���ڳ� ������ 6����","ġ�� ������","��Ͼ� ��","�ٴ�ŷ 2����","�ٴ�ŷ 4����","�ʰ�ŷ 4����","�ʰ�ŷ 6����","Ʈ���� ������"};
				String[] shamburger= {"��ŷ","���� ������ũ ����","ġŲ ����","���� �ִϾ�","ġ�� ����","����� ������ũ ����","����� ����","�Ұ��� ġ�� ����"};
				
				
				for(int i=0; i<6; i++) {
					if(mm.equals(str1[i])) {
						set[i]=0;
						b[i]=0;
						a[i]=0;
					}
				}
				
				for(int i=0; i<10; i++) {
					if(mm.equals(sBeverage[i])) {
						BsetM[i]=0;
						break;
					}else if(mm.equals(sSide[i])) {
						SsetM[i]=0;
					}
				}
				for(int i=0; i<8; i++) {
					if(mm.equals(shamburger[i])) {
						HsetM[i]=0;
					}
				}
				
				if(vec.size()!=0 && vec.get(0).getNo()!=1) {
					dao.updateNo(1, vec.get(0).getMenu());
					
					for(int i=1; i<vec.size(); i++) {
						dao.updateNo(i+1, vec.get(i).getMenu());
					}
					refresh();
				}
				
			}else {
				JOptionPane.showMessageDialog(contentPane, "������ ����� ������ �ּ���");
			}
		}else if(obj==btnAlldel) { //��ü ���
			dao.deleteAll(); //������ ���̽� ���� ��ü ����
			refresh(); // ���̺� ���� ��ü �ʱ�ȭ
			spinner.setEnabled(false); //���ǳ� ���� â �ٽ� ��Ȱ��ȭ
			spinner.setValue(1); //���ǳʰ� 1�� ���� �������� ����
			
			for(int i=0; i<a.length; i++) { //�ܹ��� ���� ���� �ʱ�ȭ
				a[i]=0;
				b[i]=0;
				set[i]=0;
			}//for
			for(int i=0; i<10; i++) {
				BsetM[i]=0;
				SsetM[i]=0;
			}
			for(int i=0; i<8; i++) {
				HsetM[i]=0;
			}
			num1=1;
			
			change("Pset"); //�ܹ��ż�Ʈ ȭ������ ������
			
		}else if(obj==btnCash) { //���� ����
			System.out.println(txtSum.getText());
			if(Integer.parseInt(txtNum.getText())==0) { //�ֹ� �����Ѱ� ���� ������
				JOptionPane.showMessageDialog(this, "�ֹ� �� ���� �����մϴ�.");
				System.out.println(txtNum.getText());
			}else {
				cpop.setVisible(true);

			}
			//Dialog���� ���� ����
			if(pop.say()==true) {
				dao.selectAll();
				dao.deleteAll(); //������ ���̽� ���� ��ü ����
				refresh(); // ���̺� ���� ��ü �ʱ�ȭ
				change("Pset"); //�ܹ��� ����ȭ������ ���ư���
				spinner.setEnabled(false); //���ǳ� ���� â �ٽ� ��Ȱ��ȭ
				spinner.setValue(1); //���ǳʰ� 1�� ���� �������� ����
				
				for(int i=0; i<a.length; i++) { //�ܹ��� ���� ���� �ʱ�ȭ
					a[i]=0;
					b[i]=0;
					set[i]=0;
				}//for
			}
				
		}else if(obj==btnCard) { //ī�� ����
			
			if(Integer.parseInt(txtNum.getText())==0) { //�ֹ� �����Ѱ� ���� ������
				JOptionPane.showMessageDialog(this, "�ֹ� �� ���� �����մϴ�.");
			}else {
				pop.setVisible(true);				
			}
			
			//Dialog���� ���� ����
			if(pop.say()==true) {
				dao.selectAll();
				dao.deleteAll(); //������ ���̽� ���� ��ü ����
				refresh(); // ���̺� ���� ��ü �ʱ�ȭ
				change("Pset"); //�ܹ��� ����ȭ������ ���ư���
				spinner.setEnabled(false); //���ǳ� ���� â �ٽ� ��Ȱ��ȭ
				spinner.setValue(1); //���ǳʰ� 1�� ���� �������� ����
				
				for(int i=0; i<a.length; i++) { //�ܹ��� ���� ���� �ʱ�ȭ
					a[i]=0;
					b[i]=0;
					set[i]=0;
				}//for
				
			}else {
				
			}
			
		}else if(obj==button_2) {//�ϼ�
			vec=dao.selectAll();
			
			vo.setNo(vec.size()+1);//������ ��ȣ
			vo.setMenu("�ñ״�ó"+num1);
			vo.setPrice(Integer.parseInt(textField_1.getText()));
			vo.setNum(1);
			dao.insert(vo);
			num1++;
			//������ ���õȰ� ����
			for(int i=0; i<9; i++)
			lblmake[i].setIcon(null);
			
			textField_1.setText(null);
			//üũ ����
			for(int i=0; i<9; i++)
			chkmake[i].setSelected(false);
		}
		
	/**------------- ADVERTISEMENT --------------------------*/

		for(int i=0; i<rabtn.length;i++) {
			if(obj==rabtn[i]) {//�ش� ���� ��ư�� Ŭ�� �Ǿ��� ���
				lblNewLabel_2.setIcon(new ImageIcon(OrderMain.class.getResource("/burger/"+picture[i]+".jpg"))); //�׿� �ش��ϴ� ���� ���̱�
				P=i; //Ư�� �ð��� �Ǹ� �������� �����ϱ� ����  ������ ������ �������� ����.
				num=0;
			}
		}
		
		
		
	}//action listner
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj=e.getSource();
		
		vec=dao.selectAll();
		
		if(obj==lblset1) {
			click(0,"���� �ִϾ� ��Ʈ",6500);
			
		}else if(obj==lblset2) {
			click(1,"ġ�� ���� �ִϾ� ��Ʈ",6800);
			
		}else if(obj==lblset3) {
			click(2,"�Ұ��� ġ�� ���� ��Ʈ",8800);
			
		}else if(obj==lblset4) {
			click(3,"��ġŲ ���� ��Ʈ",7400);
			
		}else if(obj==lblset5) {
			click(4,"ġ�� ���� ��Ʈ",5500);
			
		}else if(obj==lblset6) {
			click(5,"�Ұ��� ���� ��Ʈ",5600);
		}
		
		//---------------------------------
		
		//���� �޴�, ����
		String[] sBeverage= {"�ݶ�L","�ݶ�R","���̴�L","���̴�R","ȯŸ ������L","ȯŸ ������R","�Ƹ޸�ī��","���̽� �Ƹ޸�ī��","������",""};
		int[] ibeverage= {1900, 1700, 1900, 1700, 1900, 1700, 1600, 1600, 2100, 0};
		
		//���̵� �޴�, ����
		String[] sSide= {"����ġ ������","���ڳ� ������ 3����","���ڳ� ������ 6����","ġ�� ������","��Ͼ� ��","�ٴ�ŷ 2����","�ٴ�ŷ 4����","�ʰ�ŷ 4����","�ʰ�ŷ 6����","Ʈ���� ������"};
		int[] iside= {2200, 3100, 5800, 2000, 2100, 2300, 4100, 2100, 3100, 2200};
		
		//�ܹ��� �޴�, ����
		String[] shamburger= {"��ŷ","���� ������ũ ����","ġŲ ����","���� �ִϾ�","ġ�� ����","����� ������ũ ����","����� ����","�Ұ��� ġ�� ����"};
		int[] ihamburger= {6300, 7100, 4800, 4400, 6600, 8000, 6900, 6600};
	
		for(int i=0; i<8; i++) {
			if(obj==hamburgerM[i]) {
				clickM(i, shamburger[i], ihamburger[i], HsetM);
				break;
			}
		}
		
		for(int i=0; i<10; i++) {
			if(obj==SideM[i]) {
				clickM(i, sSide[i], iside[i], SsetM);
				break;
			}
		}
		
		for(int i=0; i<10; i++) {
			if(obj==beverageM[i]) {
				clickM(i, sBeverage[i], ibeverage[i], BsetM);
				break;
			}
		}
		
	}//action
	
	//�г� ü����
	public void change(String changepanel) {
		
		if(changepanel.equals("Pset")) { 
			card.show(panel_4, "Pset"); //����ȭ������ ��ȯ�ϱ�
			
		}else if(changepanel.endsWith("Pside")) { 
			card.show(panel_4, "Pside"); //OrderSetSideȭ������ ��ȯ�� ����ڰ� ����� ���̵带 ������ �� �ֵ��� ����
		}
		
	}
	
	//������� �ʴ� �޼ҵ��
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	
	////////////////Ŭ���� �̺�Ʈ ����//////////////////
	public void click(int num,String menu, int price) {
		
		change("Pside"); //���̵�� ���Ḧ ������ �� �ֵ��� �г� ����
		
		if(set[num]==0) { //��� �ܹ��Ÿ� ó������ �����ߴٸ� 
			
			vo.setMenu(menu); //�ش� �޴��� �̸�
			vo.setNo(vec.size()+1); //�ش� �޴��� ��ȣ
			vo.setNum(1); //�ش� �޴��� ����
			vo.setPrice(price); //�ش� �޴��� ����
			result=dao.insert(vo); //���� ������ DB�� ����
			
			vec=dao.selectAll();
			
			a[num]=vec.size(); //�ش� �޴��� ���� ��ȣ
			b[num]=1; //�ش� �޴��� ������ ����
			set[num]=1; //�ش� �ܹ��Ű� �̹� �ѹ� ���õǾ��ٰ� �˸��� ��
			
		}else if(set[num]==1){ //��� �ܹ��Ű� �ѹ� �̻� Ŭ�� �Ǿ��ٸ�
			b[num]=vec.get(a[num]-1).getNum()+1; //�ش� �ܹ����� ������ +1;
			if(b[num]>10) { //���� �ܹ����� ���� ������ 10�� �ʰ����� ��
				JOptionPane.showMessageDialog(contentPane, "���� �޴��� �ִ� �ֹ� ������ 10�� �Դϴ�.");
				
			}else { //�ִ� ������ �ʰ����� �ʾҴٸ�
				result=dao.updateNum(b[num], a[num]); //DB�� �ش� �ܹ����� ���� ���� ���� 
				refresh(); //���̺� ���� ����
			}
		}
	}
	//-----
	public void clickM(int i, String menu, int price, int[] HSM) {

		if(HSM[i]==0) { //��� �ܹ��Ÿ� ó������ �����ߴٸ� 
			
			vo.setMenu(menu); //�ش� �޴��� �̸�
			vo.setNo(vec.size()+1); //�ش� �޴��� ��ȣ
			vo.setNum(1); //�ش� �޴��� ����
			vo.setPrice(price); //�ش� �޴��� ����
			result=dao.insert(vo); //���� ������ DB�� ����
			
			vec=dao.selectAll();
			
			HSM[i]=1; //�ش� �ܹ��Ű� �̹� �ѹ� ���õǾ��ٰ� �˸��� ��
			
		}else if(HSM[i]==1){ //��� �ܹ��Ű� �ѹ� �̻� Ŭ�� �Ǿ��ٸ�
			JOptionPane.showMessageDialog(contentPane, "���� ������ �ؿ��� ������ �ּ���");
		}
		
	}
	
	
	/////////////////////////////////////////////
	

	//////////////���̺� DB���� ��////////////////////
	
	//���̺� ��� ����
	class table extends Thread {
		public void run() {
		
			try {
		
			while(true) {
				vec=dao.selectAll();
				int dbNum=vec.size(); //DB�� ����
				int sum = 0;
				int Nsum = 0;
				
				///////////////////////////// ���̺��� ���� ���̱� //////////////////////////////
				
					if(model.getRowCount()>dbNum) {
						System.out.println("���̺� ������ ũ��");
						refresh();
					}else if(model.getRowCount()==dbNum) {
						
					}else if(model.getRowCount()<dbNum) { //���� DB���� ���̺��� ������ ���ٸ�
		
						Vector<String> svec=new Vector<>(); //���̺��� ������ DB�� ������ �Ѹ��� 
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getNo()));
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getMenu()));
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getPrice()));
						svec.addElement(String.valueOf(vec.get(vec.size()-1).getNum()));
									
						model.addRow(svec);
						
					}
							
				//////////////////////////// ���� �� ����, ���� �� ���� ///////////////////////////////
					
					for(OrderVO list : vec) { //���� DB�� ��� ���̺��� ������ ����
						sum+=list.getPrice()*list.getNum(); //'����*����=�� ����' ������ ����
						Nsum+=list.getNum(); //���� ���õǾ� �ִ� �޴��� ������ ����
					}
					
					txtNum.setText(Nsum+""); //�� ���� ������ �ؽ�Ʈ �ʵ忡 �����ֱ�
					txtSum.setText(sum+""); //�� ���� ������ �ؽ�Ʈ �ʵ忡 �����ֱ� 
						
				////////////// ��ȣ�� ���� /////////////////////////////////////////////////////////
					
					/*if(vec.size()!=0 && vec.get(0).getNo()!=1) {
						dao.updateNo(1, vec.get(0).getMenu());
						
						for(int i=1; i<vec.size(); i++) {
							dao.updateNo(i+1, vec.get(i).getMenu());
							refresh();
						}
					}*/
					
					String[] str1= {"���� �ִϾ� ��Ʈ","ġ�� ���� �ִϾ� ��Ʈ","�Ұ��� ġ�� ���� ��Ʈ","��ġŲ ���� ��Ʈ","ġ�� ���� ��Ʈ","�Ұ��� ���� ��Ʈ"};
					for(int i=0; i<vec.size(); i++) {
						for(int j=0; j<str1.length; j++) {
							if(vec.get(i).getMenu().equals(str1[j])) {
								a[j]=vec.get(i).getNo();
								break;
							}
						}
					}
					
				///////////////////////////////////////////////////////////////////////////
					
					if(table.getSelectedRow()==-1) {
						spinner.setValue(1);
						spinner.setEnabled(false);
					}
					if(vec.size()==0) {
						for(int i=0; i<a.length; i++) { //�ܹ��� ���� ���� �ʱ�ȭ
							a[i]=0;
							b[i]=0;
							set[i]=0;
						}//for
						for(int i=0; i<10; i++) {
							BsetM[i]=0;
							SsetM[i]=0;
						}
						for(int i=0; i<8; i++) {
							HsetM[i]=0;
						}
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

	//���̺� �ٽ� ���
	public void refresh() {
		Vector<Object> obj;
		vec=dao.selectAll();
		
		String columnNames[]= {"��ȣ","�޴�","����","����"};
		model = new DefaultTableModel(columnNames, 0);
		table.setModel(model);
		
		//���̺��� ��� ���Ľ�Ű�� ���� �ҽ��ڵ�
		DefaultTableCellRenderer render=new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel colum=table.getColumnModel();
		for(int i=0; i<colum.getColumnCount(); i++) {
			colum.getColumn(i).setCellRenderer(render);
		}

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //���� ���� ����
		table.getTableHeader().setReorderingAllowed(false); //�÷� �̵� ����

		//vector�� ������ �ִ� ������ jtable�� �����ֱ�
		for(OrderVO list : vec) {
			obj=new Vector<>();
			
			obj.addElement(list.getNo());
			obj.addElement(list.getMenu());
			obj.addElement(list.getPrice());
			obj.addElement(list.getNum());
			
			model.addRow(obj);
		}
	
		
		
	}//refresh();

	//���õ� ��Ʈ�� �������� ���� ���ᳪ ���̵带 �������� ���ϰ� ����
	public void sideNum() {
		int row=table.getSelectedRow();
		
		vec=dao.selectAll();
		
		String[] sBeverage= {"(S)�ݶ�L","(S)�ݶ�R","(S)���̴�L","(S)���̴�R","(S)ȯŸ ������L","(S)ȯŸ ������R","(S)�Ƹ޸�ī��","(S)���̽� �Ƹ޸�ī��","������",""};
		String[] sSide= {"(S)����ġ ������","(S)���ڳ� ������ 3����","(S)���ڳ� ������ 6����","(S)ġ�� ������","(S)��Ͼ� ��","(S)�ٴ�ŷ 2����","(S)�ٴ�ŷ 4����","(S)�ʰ�ŷ 4����","(S)�ʰ�ŷ 6����","(S)Ʈ���� ������"};
		
		int B=0, S=0; //���� �� ����, ���̵� �� ����, ��Ʈ �� ����
		H=0;
		for(OrderVO list : vec) {
			for(int i=0; i<10; i++) {
				if(list.getMenu().equals(sBeverage[i])) {//��Ʈ ���� ����
					B+=list.getNum();
					break;
				}else if(list.getMenu().equals(sSide[i])) {//��Ʈ ���̵� �޴� ����
					S+=list.getNum();
					break;
				}else if(list.getMenu().indexOf("��Ʈ")!=-1) {//��Ʈ �ܹ��� ����
					H+=list.getNum();
					break;
				}
			}
		}
		
		//////////// ���̵� �޴� //////////////////
		if(H<S && H!=0) {	
			side(sSide, row, "���̵� �޴�");
			
		///////////// ���� //////////////////////
		}else if(H<B && H!=0) {
			side(sBeverage, row, "����");
		
		}else if(H==0) {
			int conform=0;
			
			if(vec.size()!=0) {
				for(OrderVO list : vec) {
					for(int i=0; i<vec.size(); i++) {
						if(vec.get(i).getMenu().indexOf("S")!=-1) { //(S)�� ��Ʈ�� ���ᳪ ���̵��� ��.
							dao.delete(i+1);
							conform=1;
						}
					}
				}
				if(conform==1) {
					refresh();
					conform=0;
				}
				
				change("Pset");
			}
		}
		
	}//sideNum
	
	public void side(String[] BS, int row, String sBS) {
		 int conform=0;
		 
		 for(String list : BS) {
			 String me=vec.get(vec.size()-1).getMenu();
			 if(me.indexOf("��Ʈ")==-1 && me.equals(list)) {
				conform=1;
				break;
			 }else if(me.indexOf("��Ʈ")==-1 && !me.equals(list)){
				conform=2;
			 }else {
				conform=3;
			 }
		 }
		 
		// vec=dao.selectAll();
		
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
			System.out.println("2");
			
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
			System.out.println("3");
		 }//eles
		 
		 JOptionPane.showMessageDialog(contentPane, "��Ʈ���� ���� "+sBS+"�� �ֹ��Ͻ� �� �����ϴ�.");
	}//side
	
	///////////////// �ñ״�ó //////////////////////
	
	@Override
	public void itemStateChanged(ItemEvent e) { //�ñ״�ó 
		JCheckBox check=(JCheckBox) e.getItem();
		
		int price[]= {1000, 700, 600, 500, 600, 400, 500, 500, 400};
		sigsum=0;
		for(int i=0;i<9;i++) {
				
			if(chkmake[i].isSelected()) { //���õǸ� => �̹���
				lblmake[i].setIcon(new ImageIcon(OrderMain.class.getResource("/imgSig/"+bgName[i]+".png")));
					sigsum+=price[i];			
					textField_1.setText(sigsum+""); //�־��� ���� string
				
			}else {
			
				lblmake[i].setIcon(null);
				if(chkmake[i].isSelected()) {
					sigsum+=price[i];
				}
				textField_1.setText(sigsum+"");
				
			}//6ge
		}
		//�⺻ ���� �� 3000�� 
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
			String[] picture= {"m_1","m_2","m_3","m_4","m_5"};	//�����̸�
			
			while(true) {	//��� ����-�����̵��
				try {
					cal=Calendar.getInstance();
					sec=(cal.get(Calendar.SECOND)<10?"0":"")+cal.get(Calendar.SECOND);
					
					sleep(1000); //1�ʸ��� ����
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
		;
				num++; //1�ʰ� ������ num++
				
				if(num%5==0) { //5�ʰ� ������ ���
					if(P<5) {						
						P++;
						
						if(P==5) {
							P=0;
						}
						
						lblNewLabel_2.setIcon(new ImageIcon(OrderMain.class.getResource("/burger/"+picture[P]+".jpg"))); //���� ���� �󺧿� ���̱�
						rabtn[P].setSelected(true);//��ư�� ���� �������� �̵�						
					}
				}
		
			}//while
		}//run
		
	}//cal

	public void allReset() {
		dao.deleteAll(); //������ ���̽� ���� ��ü ����
		refresh(); // ���̺� ���� ��ü �ʱ�ȭ
		change("Pset"); //�ܹ��� ����ȭ������ ���ư���
		spinner.setEnabled(false); //���ǳ� ���� â �ٽ� ��Ȱ��ȭ
		spinner.setValue(1); //���ǳʰ� 1�� ���� �������� ����
		
		for(int i=0; i<a.length; i++) { //�ܹ��� ���� ���� �ʱ�ȭ
			a[i]=0;
			b[i]=0;
			set[i]=0;
		}//for
		for(int i=0; i<10; i++) {
			BsetM[i]=0;
			SsetM[i]=0;
		}
		for(int i=0; i<8; i++) {
			HsetM[i]=0;
		}
		for(int i=0; i<9; i++) {
			chkmake[i].setSelected(false);
		}
		
		tabbedPane.setSelectedIndex(0);
	}
	
}//ordermain