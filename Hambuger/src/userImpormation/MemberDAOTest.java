package userImpormation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MemberDAOTest {

	public static void main(String[] args) {
		
		MemberDAO dao=new MemberDAO();
		int result=0;
		Vector<MemberVO> vec=new Vector<>();
		MemberVO vo=new MemberVO();
		
		/*//전체 조회
		vec=dao.selectAll();
		
		for(MemberVO list : vec) {
			System.out.println(list);
		}*/
		
		////////////////////////////////////
		
		/*//limit 조회
		vec=dao.selectLimit(4,3);
		
		for(MemberVO list : vec) {
			System.out.println(list);
		}*/
	
		////////////////////////////////////
		
		/*//conut 조회 - 보류..
		int re=dao.selectCount();
		
			System.out.println(re);
		*/
	
		////////////////////////////////////
		
		/*//한개 조회
		vo=dao.selectOne(3);
		System.out.println(vo);*/
	
		////////////////////////////////////
		
		//삽입
		
		/*vo.setName("가나다");
		vo.setId("ghswhgus");
		vo.setPasswd("12345");
		vo.setBirthday("1995-05-26");
		vo.setGender("남자");
		vo.setPhone("0109013886");
		vo.setMail("infidelity@duam.net");
		
		result=dao.insert(vo);
		
		if(result>0) {
			System.out.println("정상 입력");
		}*/
		
		
		///////////////////////////////////
		
		/*//수정
		
		result=dao.update("passwd", "54321", 11);
		
		if(result>0) {
			System.out.println("정상 수정");
		}*/
	
		///////////////////////////////////
		
		/*//삭제
		result=dao.delete(11);
		
		if(result>0) {
			System.out.println("정상 삭제");
		}*/
		
	}

}
