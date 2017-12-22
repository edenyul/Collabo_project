package order;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
import javax.swing.JTextField;

public class BuypopCash extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	public DefaultTableModel model; //ǥ�� ��� ���� ��
	private JButton okButton, cancelButton;
	public boolean sayme; //Ŭ�� ���� �˱�
	
	//-------- �ݾ� �� �ϱ� ----------------------
	private int all=0, how=0;
	
	
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
		private JTextField txtgive;
		
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			BuypopCash dialog = new BuypopCash();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public BuypopCash() {
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
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				{
					JLabel lblNewLabel = new JLabel("\uC8FC\uBB38 \uB9AC\uC2A4\uD2B8\uB97C ");
					panel_1.add(lblNewLabel);
					lblNewLabel.setFont(new Font("����", Font.BOLD, 16));
				}
				{
					JLabel lblNewLabel_1 = new JLabel("\uD655\uC778");
					panel_1.add(lblNewLabel_1);
					lblNewLabel_1.setForeground(Color.RED);
					lblNewLabel_1.setFont(new Font("����", Font.BOLD, 18));
				}
				{
					JLabel lblNewLabel_2 = new JLabel("\uD574 \uC8FC\uC138\uC694.");
					panel_1.add(lblNewLabel_2);
					lblNewLabel_2.setFont(new Font("����", Font.BOLD, 16));
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				{
					JLabel lblNewLabel_3 = new JLabel("\uC9C0\uAE09\uD55C \uB3C8 : ");
					panel_1.add(lblNewLabel_3);
				}
				{
					txtgive = new JTextField();
					panel_1.add(txtgive);
					txtgive.setColumns(10);
				}
				{
					JLabel lblNewLabel_4 = new JLabel("\uC6D0");
					panel_1.add(lblNewLabel_4);
				}
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
	
		//�Է� ���� �ݾ�
		if(btn==okButton) { //�����ϸ�
			how=Integer.parseInt(txtgive.getText());
			all=dao.allprice();
		
			if( all>how || how%10!=0 || (how-all)<0 ) {
				JOptionPane.showMessageDialog(this, "�Է��� �ݾ��� �ٽ� Ȯ���ϼ���.\n(���� �����մϴ�.)","���� Ȯ�� ��û",JOptionPane.QUESTION_MESSAGE,null);
			}else {
				int leftm=0;
				leftm=how-all;
				dao.deleteAll();
				refresh();
				sayme=true;
				say();
				JOptionPane.showConfirmDialog(this, "���� �Ϸ�\n�Ž����� : "+leftm+"�� �Դϴ�.", "���� �Ϸ�", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				//JFrame main=getMainF();
				MemberLoginTest login=new MemberLoginTest();
				
				dispose();
				login.order.setVisible(false);
				
				login.setVisible(true);
				
				
			}
		}else { //����ϸ�			
			refresh();
			dispose();
			sayme=false;
			say();
			}
	}//end actionPerformed
	
	public boolean say() { //��ư ���� ���� �˸�
		return sayme;
		
	}
	
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