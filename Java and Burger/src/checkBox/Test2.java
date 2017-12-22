package checkBox;

import javax.swing.JOptionPane;

public class Test2 {

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "예제입니다1.");
		JOptionPane.showMessageDialog(null, "예제입니다2.","정보",JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "예제입니다3.","경고",JOptionPane.WARNING_MESSAGE);
		JOptionPane.showMessageDialog(null, "예제입니다4.","에러",JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null, "예제입니다5.","질문",JOptionPane.QUESTION_MESSAGE);
		JOptionPane.showMessageDialog(null, "예제입니다6.","아이콘",JOptionPane.PLAIN_MESSAGE);

		int i=JOptionPane.showConfirmDialog(null, "예제입니다7.","확인 취소",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if(i==0) {
			System.out.println("확인");
		}else {
			System.out.println("취소"+i);
		}
	
	}

}
