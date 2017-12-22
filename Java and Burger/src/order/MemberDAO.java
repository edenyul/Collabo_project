package order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MemberDAO {

	//connection
	public Connection getcon() {
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/javadb?useSSL=true";
			con=DriverManager.getConnection(url, "root", "12345");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//close
	public void close(Connection con, PreparedStatement prmt) {
		try {
			if(prmt!=null) {
				prmt.close();
			}else if(con!=null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void close(Connection con, PreparedStatement prmt, ResultSet re) {
		try {
			if(re!=null) {
				re.close();
			}else if(prmt!=null) {
				prmt.close();
			}else if(con!=null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//생성
	public int insert(MemberVO vo) {
		Connection con=getcon();
		PreparedStatement prmt=null;
		int result=0;
		
		String sql="insert into member(name, id, passwd, birthday, gender, phone, mail)"
				+"values(?, ?, ?, ?, ?, ?, ?)";
		try {
			prmt=con.prepareStatement(sql);
		
			prmt.setString(1, vo.getName());
			prmt.setString(2, vo.getId());
			prmt.setString(3, vo.getPasswd());
			prmt.setString(4, vo.getBirthday());
			prmt.setString(5, vo.getGender());
			prmt.setString(6, vo.getPhone());
			prmt.setString(7, vo.getMail());
			
			result=prmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt);
		}
		
		return result;
	}
	
	//삭제
	public int delete(int no) {
		Connection con=getcon();
		PreparedStatement prmt=null;
		int result=0;
		
		String sql="delete from member where no=?";
		try {
			prmt=con.prepareStatement(sql);
		
			prmt.setInt(1, no);
			
			result=prmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt);
		}
		
		return result;
	}
	
	//변경
	public int update(String item, String content, int no) {
		Connection con=getcon();
		PreparedStatement prmt=null;
		int result=0; 
		
		String sql="update member set "+item+"=? where no=?";
		try {
			prmt=con.prepareStatement(sql);
		
			prmt.setString(1, content);
			prmt.setInt(2, no);
			
			result=prmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt);
		}
		
		return result;
	}
	
	//전체 조회
	public Vector<MemberVO> selectAll() {
		Connection con=getcon();
		PreparedStatement prmt=null;
		ResultSet re=null;
		Vector<MemberVO> vec=new Vector<>();
		
		String sql="Select * from member";
		try {
			prmt=con.prepareStatement(sql);
		
			re=prmt.executeQuery();
			
			while(re.next()) {
				MemberVO vo=new MemberVO();
				
				vo.setNo(re.getInt(1));
				vo.setName(re.getString(2));
				vo.setId(re.getString(3));
				vo.setPasswd(re.getString(4));
				vo.setBirthday(re.getString(5));
				vo.setGender(re.getString(6));
				vo.setPhone(re.getString(7));
				vo.setMail(re.getString(8));
				
				vec.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt, re);
		}
		
		return vec;
	}
	
	//한개 조회
	public MemberVO selectOne(int no) {
		Connection con=getcon();
		PreparedStatement prmt=null;
		ResultSet re=null;
		MemberVO vo=new MemberVO();
		
		String sql="Select * from member where no=?";
		try {
			prmt=con.prepareStatement(sql);
		
			prmt.setInt(1, no);
			
			re=prmt.executeQuery();
			
			if(re.next()) {
				vo.setNo(re.getInt(1));
				vo.setName(re.getString(2));
				vo.setId(re.getString(3));
				vo.setPasswd(re.getString(4));
				vo.setBirthday(re.getString(5));
				vo.setGender(re.getString(6));
				vo.setPhone(re.getString(7));
				vo.setMail(re.getString(8));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt, re);
		}
		
		return vo;
	}
	
/*	//카운트 조회
	public int selectCount() {
		Connection con=getcon();
		PreparedStatement prmt=null;
		ResultSet re=null;
		int result=0;
		
		String sql="Select count(*) from member";
		try {
			prmt=con.prepareStatement(sql);
			
			re=prmt.executeQuery();
			
			result=re.get;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt, re);
		}
		
		return result;
	}*/
	
	//리미트 조회 
	public Vector<MemberVO> selectLimit(int start, int num) {
		Connection con=getcon();
		PreparedStatement prmt=null;
		ResultSet re=null;
		Vector<MemberVO> vec=new Vector<>();
		
		String sql="select * from member limit ?, ?";
		try {
			prmt=con.prepareStatement(sql);
				
			prmt.setInt(1, start);
			prmt.setInt(2, num);
		
			re=prmt.executeQuery();
			
			while(re.next()) {
				MemberVO vo=new MemberVO();
				
				vo.setNo(re.getInt(1));
				vo.setName(re.getString(2));
				vo.setId(re.getString(3));
				vo.setPasswd(re.getString(4));
				vo.setBirthday(re.getString(5));
				vo.setGender(re.getString(6));
				vo.setPhone(re.getString(7));
				vo.setMail(re.getString(8));
				
				vec.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt, re);
		}
		
		return vec;
	}
	
}
