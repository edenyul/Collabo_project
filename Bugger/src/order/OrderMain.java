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
import java.util.Vector;

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
	//private int num=0; //���̺� ���� ����  üũ

	private CardLayout card=new CardLayout(); //ī�� ���̾ƿ�
	//������� ��ư, 
	private JButton btnckdel, btnAlldel, btnCash, btnCard;
	private JScrollPane scrollPane;
	private JSpinner spinner;
	private int spi1, spi2;
	
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
		
//------------- DB -------------------------------------
	
	private OrderDAO dao=new OrderDAO();
	private OrderVO vo=new OrderVO();
	private Vector<OrderVO> vec=new Vector<>();
	//private Vector<String> str=new Vector<>();
	private int result=0;
	
	private int[] a=new int[6]; //���̺� ��ȣ
	private int[] b=new int[6]; //���� �޴��� ����
	private int[] set=new int[6];
	private int H;

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
		lblNewLabel_4.setFont(new Font("����", Font.BOLD, 12));
		panel_1.add(lblNewLabel_4);
		
		//////////////////���ǳ�///////////////////////////
		
		spinner = new JSpinner();
		
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				if(spinner.isEnabled()) { //���ǳʰ� Ȱ��ȭ �Ǿ� �ִٸ�
					
					spi1=(int) spinner.getValue(); //���ϴ� ���ǳ��� ���� ����
					
					int row=table.getSelectedRow(); //������ ���̺��� ���� ���� ����
					
					result=dao.updateNum(spi1, row+1); //������ ���̽��� ���� ���ǳʿ� �����ϰ� ����
					model.setValueAt(spi1, row, 3); //���̺� ���ǳʿ� ������ ���� �������� ����	
					
				}//���ǳ� Ȱ��ȭ ���� if
				
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
						
						spinner.setValue(colum); //���ǳ��� ���� ���̺�� ������ ������ ����
						
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
		
		btnCard = new JButton("\uCE74\uB4DC\uACB0\uC81C");
		panel_10.add(btnCard);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("��Ʈ�޴�", null, panel_3, null);
		
		JPanel phambuger = new JPanel();
		tabbedPane.addTab("�ܹ���", null, phambuger, null);
		phambuger.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_26 = new JPanel();
		phambuger.add(panel_26, BorderLayout.SOUTH);
		
		JButton button = new JButton("\u25C0 \uC774\uC804");
		panel_26.add(button);
		
		JButton button_1 = new JButton("\uB2E4\uC74C \u25B6");
		panel_26.add(button_1);
		
		JPanel pside = new JPanel();
		tabbedPane.addTab("���̵�", null, pside, null);
		
		JPanel pdrink = new JPanel();
		tabbedPane.addTab("����", null, pdrink, null);
		
		JPanel panel_20 = new JPanel();
		tabbedPane.addTab("�ñ״�ó", null, panel_20, null);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_20.add(panel_21, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("\uC6D0\uD558\uB294 \uC7AC\uB8CC\uB97C \uC120\uD0DD\uD558\uC138\uC694");
		panel_21.add(lblNewLabel_5);
		
		JPanel panel_6 = new JPanel();
		panel_20.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		//�̹��� ������ �г�
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(new GridLayout(0,1,0,0));
		lblmake =new  JLabel[9];
		
		for(int i=0; i<lblmake.length; i++) { //������ ������ش�.
			lblmake[i] =new JLabel();
			panel_8.add(lblmake[i]);
		}		
		panel_6.add(panel_8);
		
		//üũ �ڽ� �ִ� �г�
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(new GridLayout(0,1,0,0));
		//ù��° �гο� üũ�ڽ�  add
		for(int i=0;i<chkmake.length;i++) {
			//üũ�ڽ� ��ü �����ؼ� �迭�� ���
			chkmake[i]=new JCheckBox(bgName[i]);
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
		
		JButton button_2 = new JButton("\uC644\uC131");
		panel_7.add(button_2);
		
		JPanel ptitle = new JPanel();
		ptitle.setForeground(Color.LIGHT_GRAY);
		contentPane.add(ptitle, BorderLayout.NORTH);
		
		JLabel lbltitle = new JLabel("\uBC84\uAC70 \uC564 \uC790\uBC14");
		lbltitle.setFont(new Font("����", Font.BOLD, 18));
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
		btnMain.addActionListener(new ActionListener() { //'���� ȭ��' ��ư�� Ŭ�� �Ǿ��� ��
			public void actionPerformed(ActionEvent e) {
				change("Pset"); //�ܹ��� ��Ʈâ���� �ٽ� �̵�
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
		
		table ta=new table(); //���̺� ���� Ŭ����
		ta.start(); 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		
		if(obj==btnckdel) { //���� ���
			int row=table.getSelectedRow(); //������ ���̺��� ��
			row2=row;
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
				
				if(list.getMenu().indexOf("��Ʈ")!=-1) {//��Ʈ �ܹ��� ����
					H+=list.getNum();
					break;
				}else {
					H=0;
				}
			}
			
			for(int i=row; i<vec.size(); i++) { //������ ��ȣ�� �������� DB�� ������
				dao.updateNo(i+1, str[i]); //DB�� ��ȣ�� ���ʷ� �ٲ㳪����
			}
			
			//�ܹ��� ��Ʈ�� �ֹ��޴��� ���� �� ��Ʈ�� ���ᳪ ���̵嵵 ���� ���ֱ�
			if(H==0){
				for(OrderVO list : vec) {
					vec=dao.selectAll();
					for(int i=0; i<vec.size(); i++) {
						if(vec.get(i).getMenu().indexOf("S")!=-1) { //(S)�� ��Ʈ�� ���ᳪ ���̵��� ��.
							dao.delete(i+1);
						}
					}
				}
				change("Pset");
			}
			
			
			sideNum();
			refresh(); //�ٲﳻ���� ����
			
			////////////////////////////////////
			for(int i=0; i<6; i++) {
				if(mm.equals("�ܹ��� ��Ʈ"+(i+1))) {
					set[i]=0;
					b[i]=0;
					a[i]=0;
				}
			}
			
		}else if(obj==btnAlldel) { //��ü ���
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
			
			
		}else if(obj==btnCash) { //���� ����
			
		}else if(obj==btnCard) { //ī�� ����
			
		}
		
		
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj=e.getSource();
		
		vec=dao.selectAll();
		
		if(obj==lblset1) {
			click(0,"�ܹ��� ��Ʈ1",5000);
			
		}else if(obj==lblset2) {
			click(1,"�ܹ��� ��Ʈ2",5500);
			
		}else if(obj==lblset3) {
			click(2,"�ܹ��� ��Ʈ3",4000);
			
		}else if(obj==lblset4) {
			click(3,"�ܹ��� ��Ʈ4",3500);
			
		}else if(obj==lblset5) {
			//click(4,"�ܹ��� ��Ʈ5",5003);
			
		}else if(obj==lblset6) {
			//click(5);
		}
		
	}
	
	//�г� ü����
	public void change(String changepanel) {
		
		if(changepanel.equals("Pset")) { 
			card.show(panel_4, "Pset"); //����ȭ������ ��ȯ�ϱ�
			
		}else if(changepanel.endsWith("Pside")) { 
			card.show(panel_4, "Pside"); //OrderSetSideȭ������ ��ȯ�� ����ڰ� ����� ���̵带 ������ �� �ֵ��� ����
		}
		
	}
	
	//������� �ʴ� �޼ҵ��
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	
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
				
				///////////////////////////// ���̺� ���� ���̱� //////////////////////////////
				
					if(model.getRowCount()>dbNum) {
						//refresh();
						System.out.println("���̺� ������ ũ��");
					}else if(model.getRowCount()==dbNum) {
						
					}else if(model.getRowCount()<dbNum) { //���� DB���� ���̺��� ������ ���ٸ�
		
						Vector<String> svec=new Vector<>(); //���̺� ������ DB�� ������ �Ѹ��� 
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
					
					vec=dao.selectAll();
					if(vec.size()!=0 && vec.get(0).getNo()!=1) {
						dao.updateNo(1, vec.get(0).getMenu());
						
						for(int i=1; i<vec.size(); i++) {
							dao.updateNo(i+1, vec.get(i).getMenu());
						}
						
						refresh();
					}
					
					vec=dao.selectAll();
					String[] str1= {"�ܹ��� ��Ʈ1","�ܹ��� ��Ʈ2","�ܹ��� ��Ʈ3","�ܹ��� ��Ʈ4"};
					for(int i=0; i<vec.size(); i++) {
						for(int j=0; j<str1.length; j++) {
							if(vec.get(i).getMenu().equals(str1[j])) {
								a[j]=vec.get(i).getNo();
								break;
							}
						}
					}
					
				//////////////////////////////////////////////////////////////////////////////
				
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

	//���̺� �ٽ� ���
	public void refresh() {
		Vector<Object> obj;
		vec=dao.selectAll();
		
		table.removeAll();
		
		String columnNames[]= {"��ȣ","�޴�","����","����"};
		model = new DefaultTableModel(columnNames, 0);
		table.setModel(model);
		
		//vector�� ������ �ִ� ������ jtable�� �����ֱ�
		for(OrderVO list : vec) {
			obj=new Vector<>();
			
			obj.addElement(list.getNo());
			obj.addElement(list.getMenu());
			obj.addElement(list.getPrice());
			obj.addElement(list.getNum());
			
			model.addRow(obj);
		}
	
		table.getTableHeader().setReorderingAllowed(false); //�÷� �̵� ����
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //���� ���� ����
		
		//���̺��� ��� ���Ľ�Ű�� ���� �ҽ��ڵ�
		DefaultTableCellRenderer render=new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel colum=table.getColumnModel();
		for(int i=0; i<colum.getColumnCount(); i++) {
			colum.getColumn(i).setCellRenderer(render);
		}
		
	}//refresh();

	private int row2, B2, S2;
	//���õ� ��Ʈ�� �������� ���� ���ᳪ ���̵带 �������� ���ϰ� ����
   //�ܹ��� ��Ʈ ���� �������� ���ᳪ ���̵��� ������ �� ���� �����ϸ� ������ �� �ޱ�
	public void sideNum() {
		int row=table.getSelectedRow();
		
		vec=dao.selectAll();
		String[] sBeverage= {"(S)�ݶ�L","(S)�ݶ�M","(S)���̴�L","(S)���̴�M","(S)ȯŸL","(S)ȯŸM","(S)�Ƹ޸�ī��L","(S)�Ƹ޸�ī��M","",""};
		String[] sSide= {"(S)���� Ƣ��","(S)���� Ƣ��","(S)��¡�� Ƣ��","(S)ġ�ƽ","(S)ġŲ �ʰ�","","","","",""};
		
		int B=0, S=0; //���� �� ����, ���̵� �� ����, ��Ʈ �� ����
		H=0;
		for(OrderVO list : vec) {
			for(int i=0; i<10; i++) {
				if(list.getMenu().equals(sBeverage[i])) {//��Ʈ ���� ����
					B+=list.getNum();
					B2+=1;
					break;
				}else if(list.getMenu().equals(sSide[i])) {//��Ʈ ���̵� �޴� ����
					S+=list.getNum();
					S2+=1;
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
		}//if
		
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
			 System.out.println("1");
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
	
	}//side
	
	///////////////// �ñ״�ó //////////////////////
	
	@Override
	public void itemStateChanged(ItemEvent e) { //�ñ״�ó 
		JCheckBox check=(JCheckBox) e.getItem();
		
		for(int i=0;i<9;i++) {
			if(chkmake[i].isSelected()) { //���õǸ� => �̹���
				lblmake[i].setIcon(new ImageIcon(OrderMain.class.getResource("/imgSig/"+bgName[i]+".png")));
			}else {
				lblmake[i].setIcon(null);
			}
		}
	}//end item

}