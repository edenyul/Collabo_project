package order;

import java.util.Vector;

public class OrderDBTest {

	public static void main(String[] args) {
	
		OrderVO vo=new OrderVO();
		OrderDAO dao=new OrderDAO();
		Vector<OrderVO> vec=new Vector<>();
		int result=0; 
		
		//삽입
		/*vo.setMenu("햄버거");
		vo.setNo(1);
		vo.setNum(1);
		vo.setPrice(1000);
		result=dao.insert(vo);
				
		if(result>0) {
			System.out.println("성공");
		}*/
		
		/*//한개 검색
		vo=dao.selectOne(1);
		System.out.println(vo.toString());*/
		
	/*	//수정
		result=dao.updateNum(10,0);
		if(result>0) {
			System.out.println("성공");
		}*/
	
		/*//전체 검색
		vec=dao.selectAll();
		
		for(OrderVO list : vec) {
			System.out.println(list);
		}*/
		
		result=dao.delete(1);
		
		if(result>0) {
			System.out.println("성공");
		}
	}

}
