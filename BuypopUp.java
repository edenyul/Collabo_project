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
	public DefaultTableModel model; //ǥ�� ��� ���� ��
	private JButton okButton, cancelButton;

	
//------------- DB -------------------------------------
	
		private OrderDAO dao=new OrderDAO();
		private OrderVO vo=new OrderVO();
		private Vector<OrderVO> vec=new Vector<>();
		private Vector<String> str=new Vector<>();
		private int result=0;
		
		private int[] a=new int[6]; //���̺� ��ȣ
		private int[] b=new int[6]; //���� �޴��� ����
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
				String[] name= {"�޴�","����","����"};
				model=new DefaultTableModel(name,0);
				table.setModel(model);
				vec=dao.selectAll();
				
				//���̺��� ��� ���Ľ�Ű�� ���� �ҽ��ڵ�
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
				lblNewLabel.setFont(new Font("����", Font.BOLD, 16));
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("\uD655\uC778");
				lblNewLabel_1.setForeground(Color.RED);
				lblNewLabel_1.setFont(new Font("����", Font.BOLD, 18));
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("\uD574 \uC8FC\uC138\uC694.");
				lblNewLabel_2.setFont(new Font("����", Font.BOLD, 16));
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
		//vector�� ������ �ִ� ������ jtable�� �����ֱ�
		for(OrderVO list : vec) {
			obj=new Vector<>();
			
			obj.addElement(list.getMenu());
			obj.addElement(list.getPrice());
			obj.addElement(list.getNum());
			
			model.addRow(obj);
		}
		
	}//end ������
	
	
	
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		if(btn==okButton) { //�����ϸ�
			dao.deleteAll();
			refresh();
			JOptionPane.showConfirmDialog(this, "���� �Ϸ�", "���� �Ϸ�", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}else { //����ϸ�			
			refresh();
			dispose();
			}
	}//end actionPerformed

	
//////////////���̺� DB���� ��////////////////////
	

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
}
