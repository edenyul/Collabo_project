package userImpormation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Member { //������ ����� �����ϱ� ���� Ŭ����

	private String Mid; //�α��� �� ����� ���̵� ������ �������� ����
	
	public String getMid() {
		return Mid;
	}

	public void setMid(String mid) {
		Mid = mid;
	}
	
	public String Birthday() {//�ش� ����� �̸��� �����ֱ� ���� String return
		
		MemberDAO dao=new MemberDAO();
		Vector<MemberVO> vec=new Vector<>();
		SimpleDateFormat date=new SimpleDateFormat("MM-dd");
		String data=date.format(new Date()); 
		String str=null;
		
		vec=dao.selectAll();
		
		for(MemberVO list : vec) {
			if(list.getId().equals(Mid)) { //������ ���̽��� �α��ο� ������ ���̵� ��ġ�Ѵٸ�
				if(data.equals(list.getBirthday().substring(5, 10))) { //�α��� ������ ����� ���ϰ� ������ ��¥�� ���ٸ�
					str=list.getName()+"���� ������ ���� �մϴ�."; //���� ���� �޼��� ���
				}else {
					str=list.getName()+"�� ȯ���մϴ�!"; //�׳� ȯ���Ѵٴ� �޼��� ���
				}
			}
		}
		
		return str; //�޼��� �� ����
	}
	
}
