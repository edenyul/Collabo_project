package checkBox;

import javax.swing.JOptionPane;

public class Test2 {

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "�����Դϴ�1.");
		JOptionPane.showMessageDialog(null, "�����Դϴ�2.","����",JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "�����Դϴ�3.","���",JOptionPane.WARNING_MESSAGE);
		JOptionPane.showMessageDialog(null, "�����Դϴ�4.","����",JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null, "�����Դϴ�5.","����",JOptionPane.QUESTION_MESSAGE);
		JOptionPane.showMessageDialog(null, "�����Դϴ�6.","������",JOptionPane.PLAIN_MESSAGE);

		int i=JOptionPane.showConfirmDialog(null, "�����Դϴ�7.","Ȯ�� ���",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if(i==0) {
			System.out.println("Ȯ��");
		}else {
			System.out.println("���"+i);
		}
	
	}

}
