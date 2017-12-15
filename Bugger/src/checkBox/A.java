package checkBox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class A extends JFrame implements ItemListener {

	private JPanel contentPane;
	//과일 이미지 보여줄 패널
	private JLabel lblfruit[]=new JLabel[3];
	//선택할 수 있는 과일 이름
	private JCheckBox chkfruit[]=new JCheckBox[3];
	private String fruitName[]= {"apple","grape","orange"};
	private ImageIcon icon[]=new ImageIcon[3];
	private JPanel panel,panel_1,panel_2,panel_3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A frame = new A();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public A() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel=new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		//첫번째 패널에 체크박스  add
		for(int i=0;i<chkfruit.length;i++) {
			//체크박스 객체 생성해서 배열에 담기
			chkfruit[i]=new JCheckBox(fruitName[i]);
			//생성된 체크박스를 패널에  add
			panel.add(chkfruit[i]);
			//JLabel 객체 생성해서 배열에 담기
			lblfruit[i]=new JLabel();
			icon[i]=new ImageIcon(JCheckBoxTest3.class.getResource("/checkbox/"+fruitName[i]+".gif"));
		}
		
		
		JPanel panel_1 = new JPanel();//사과
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0,1));
		panel_1.add(lblfruit[0]);
		
		JPanel panel_2 = new JPanel();//포도
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0,1));
		panel_2.add(lblfruit[1]);
		
		JPanel panel_3 = new JPanel();//오렌지
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(0,1));
		panel_3.add(lblfruit[2]);
		
		//이벤트 리스너 붙이기
		for(int i=0;i<chkfruit.length;i++)
			chkfruit[i].addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox check=(JCheckBox) e.getItem();
		
		for(int i=0;i<3;i++) {
			if(check==(chkfruit[i])) {
				if(e.getStateChange()==ItemEvent.SELECTED)
					lblfruit[i].setIcon(icon[i]);
				else
					lblfruit[i].setIcon(null);
			}
		}
	}

}
