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
		
		/*//��ü ��ȸ
		vec=dao.selectAll();
		
		for(MemberVO list : vec) {
			System.out.println(list);
		}*/
		
		////////////////////////////////////
		
		/*//limit ��ȸ
		vec=dao.selectLimit(4,3);
		
		for(MemberVO list : vec) {
			System.out.println(list);
		}*/
	
		////////////////////////////////////
		
		/*//conut ��ȸ - ����..
		int re=dao.selectCount();
		
			System.out.println(re);
		*/
	
		////////////////////////////////////
		
		/*//�Ѱ� ��ȸ
		vo=dao.selectOne(3);
		System.out.println(vo);*/
	
		////////////////////////////////////
		
		//����
		
		/*vo.setName("������");
		vo.setId("ghswhgus");
		vo.setPasswd("12345");
		vo.setBirthday("1995-05-26");
		vo.setGender("����");
		vo.setPhone("0109013886");
		vo.setMail("infidelity@duam.net");
		
		result=dao.insert(vo);
		
		if(result>0) {
			System.out.println("���� �Է�");
		}*/
		
		
		///////////////////////////////////
		
		/*//����
		
		result=dao.update("passwd", "54321", 11);
		
		if(result>0) {
			System.out.println("���� ����");
		}*/
	
		///////////////////////////////////
		
		/*//����
		result=dao.delete(11);
		
		if(result>0) {
			System.out.println("���� ����");
		}*/
		
	}

}
