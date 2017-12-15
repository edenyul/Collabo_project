package checkBox;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class JCheckBoxTest5 extends JFrame implements ItemListener{

	private JPanel contentPane;
	private JCheckBox ap, gr, oran;
	private JLabel lb1, lb2, lb3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCheckBoxTest5 frame = new JCheckBoxTest5();
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
	public JCheckBoxTest5() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		ap = new JCheckBox("Apple");
		panel_3.add(ap);
		
		gr = new JCheckBox("Grape");
		panel_3.add(gr);
		
		oran = new JCheckBox("Orange");
		panel_3.add(oran);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		lb1 = new JLabel("");
		panel_2.add(lb1);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		lb2 = new JLabel("");
		panel.add(lb2);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		lb3 = new JLabel("");
		panel_1.add(lb3);
		
		ap.addItemListener(this);
		gr.addItemListener(this);
		oran.addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox ch=(JCheckBox) e.getSource();
		
		if(ch.isSelected()) {
			if(ch==ap) {
				lb1.setIcon(new ImageIcon
						(JCheckBoxTest5.class.getResource("/checkBox/apple.gif")));
			}
			if(ch==gr) {
				lb2.setIcon(new ImageIcon
						(JCheckBoxTest5.class.getResource("/checkBox/grape.gif")));
			}
			if(ch==oran) {
				lb3.setIcon(new ImageIcon
						(JCheckBoxTest5.class.getResource("/checkBox/orange.gif")));
			}
		}else {
			if(!ap.isSelected()) {
				lb1.setIcon(null);
			}
			if(!gr.isSelected()) {
				lb2.setIcon(null);
			}
			if(!oran.isSelected()) {
				lb3.setIcon(null);
			}
		}
		
	}

}
