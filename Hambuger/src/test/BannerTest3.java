package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class BannerTest3 extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JToggleButton btn[];
	private JLabel lbl;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BannerTest3 dialog = new BannerTest3();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BannerTest3() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			lbl = new JLabel("New label");
			contentPanel.add(lbl, BorderLayout.CENTER);
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			/*{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}*/
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		btn=new JToggleButton[5];
		ButtonGroup group=new ButtonGroup();
		for(int i=0; i<btn.length; i++) {
			btn[i]=new JToggleButton((i+1)+"번 사진");
			panel.add(btn[i]);
			group.add(btn[i]);
			btn[i].addActionListener(this);
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		int[] picture= {43,44,45,46,47}; //사진 이름들
		
		for(int i=0; i<btn.length; i++) {
			if(obj==btn[i]) {//해당 라디오 버튼이 클릭 되었을 경우
				lbl.setIcon(new ImageIcon(BannerTest2.class.getResource("/test/"+picture[i]+".jpg"))); //그에 해당하는 사진 붙이기
			}
		}
	}
	
	public void start(int P) {
		int[] picture= {43,44,45,46,47};
		lbl.setIcon(new ImageIcon(BannerTest2.class.getResource("/test/"+picture[P]+".jpg")));
		btn[P].setSelected(true);;
	}
}
