package order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Member { //생일인 사람을 구분하기 위한 클래스

	private String Mid; //로그인 한 사람의 아이디 정보를 가져오기 위함
	
	public String getMid() {
		return Mid;
	}

	public void setMid(String mid) {
		Mid = mid;
	}
	
	public void Birthday(JPanel th) {//해당 사람의 이름을 돌려주기 위한 String return
		
		MemberDAO dao=new MemberDAO();
		Vector<MemberVO> vec=new Vector<>();
		SimpleDateFormat date=new SimpleDateFormat("MM-dd");
		String data=date.format(new Date()); 
		String str=null;
		
		vec=dao.selectAll();
		
		for(MemberVO list : vec) {
			if(list.getId().equals(Mid)) { //데이터 베이스와 로그인에 성공한 아이디가 일치한다면
				if(data.equals(list.getBirthday().substring(5, 10))) { //로그인 성공한 사람의 생일과 오늘의 날짜가 같다면
					OrderMain order=new OrderMain();
					JOptionPane.showMessageDialog(th, (str=list.getName())+"님 생일을 축하 합니다!"); //생일 축하 메세지 출력
				}
			}
		}
		
		//return str; //메세지 값 리턴
	}
	
}
