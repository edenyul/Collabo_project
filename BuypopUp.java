package order;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Color;

public class BuypopUp extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	public DefaultTableModel model; //표를 담기 위한 모델
	private JButton okButton, cancelButton;

	
//------------- DB -------------------------------------
	
		private OrderDAO dao=new OrderDAO();
		private OrderVO vo=new OrderVO();
		private Vector<OrderVO> vec=new Vector<>();
		private Vector<String> str=new Vector<>();
		private int result=0;
		
		private int[] a=new int[6]; //테이블 번호
		private int[] b=new int[6]; //선택 메뉴의 개수
		private int[] set=new int[6];
		private int H;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuypopUp dialog = new BuypopUp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuypopUp() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				String[] name= {"메뉴","가격","개수"};
				model=new DefaultTableModel(name,0);
				table.setModel(model);
				vec=dao.selectAll();
				
				//테이블을 가운데 정렬시키기 위한 소스코드
				DefaultTableCellRenderer render=new DefaultTableCellRenderer();
				render.setHorizontalAlignment(SwingConstants.CENTER);
				TableColumnModel colum=table.getColumnModel();
				for(int i=0; i<colum.getColumnCount(); i++) {
					colum.getColumn(i).setCellRenderer(render);
				}
				
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("\uC8FC\uBB38 \uB9AC\uC2A4\uD2B8\uB97C ");
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 16));
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("\uD655\uC778");
				lblNewLabel_1.setForeground(Color.RED);
				lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("\uD574 \uC8FC\uC138\uC694.");
				lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 16));
				panel.add(lblNewLabel_2);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("\uD655\uC778");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("\uCDE8\uC18C");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		Vector<Object> obj;
		//vector가 가지고 있는 데이터 jtable에 보여주기
		for(OrderVO list : vec) {
			obj=new Vector<>();
			
			obj.addElement(list.getMenu());
			obj.addElement(list.getPrice());
			obj.addElement(list.getNum());
			
			model.addRow(obj);
		}
		
	}//end 생성자
	
	
	
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		if(btn==okButton) { //결제하면
			dao.deleteAll();
			refresh();
			JOptionPane.showConfirmDialog(this, "결제 완료", "결제 완료", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}else { //취소하면			
			refresh();
			dispose();
			}
	}//end actionPerformed

	
//////////////테이블 DB관련 란////////////////////
	

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
}
