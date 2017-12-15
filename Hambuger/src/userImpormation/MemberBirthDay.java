
package userImpormation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class MemberBirthDay {//생일 테스트 클래스

	public static void main(String[] args) {
		
		SimpleDateFormat date=new SimpleDateFormat("MM-dd"); 
		String str=date.format(new Date()); 
		
		MemberDAO dao=new MemberDAO();
		Vector<MemberVO> vec=new Vector<>();
		
		vec=dao.selectAll(); 
		for(MemberVO list : vec) { 
			if(list.getBirthday().substring(5, 10).equals(str)) { 
			}
			System.out.println(list);
		}
		
	}
	
}