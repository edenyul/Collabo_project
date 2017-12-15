package order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class OrderDAO {

	//생성
	public Connection getCon() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
			String url="jdbc:mysql://localhost:3306/javadb?useSSL=true";
			con=DriverManager.getConnection(url,"root","12345");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//닫기
	public void close(Connection con, PreparedStatement prmt, ResultSet re) {
		try {
		
			if(con!=null) {
				con.close();
			}else if(prmt!=null) {
				prmt.close();
			}else if(re!=null) {
				re.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void close(Connection con, PreparedStatement prmt) {
		try {
		
			if(con!=null) {
				con.close();
			}else if(prmt!=null) {
				prmt.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//입력
	public int insert(OrderVO vo) {
		Connection con=getCon();
		PreparedStatement prmt=null;
		int result=0;
		
		String sql="insert into hambugertbl(no, menu, price, num)"
				+ "values(?, ?, ?, ?)";
		try {
			prmt=con.prepareStatement(sql);
		
			prmt.setInt(1, vo.getNo());
			prmt.setString(2, vo.getMenu());
			prmt.setInt(3, vo.getPrice());
			prmt.setInt(4, vo.getNum());
			
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
		Connection con=getCon();
		PreparedStatement prmt=null;
		int result=0; 
		
		String sql="delete from hambugertbl where no=?";
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
	public int deleteAll() {
		Connection con=getCon();
		PreparedStatement prmt=null;
		int result=0;
		
		String sql="truncate table hambugertbl";
		try {
			prmt=con.prepareStatement(sql);
		
			result=prmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt);
		}
		
		return result;
	}
	
	//수정
	public int updateNum(int num, int no) {
		Connection con=getCon();
		PreparedStatement prmt=null;
		int result=0; 
		
		String sql="update hambugertbl set num=? where no=?";
		try {
			prmt=con.prepareStatement(sql);
		
			prmt.setInt(1, num);
			prmt.setInt(2, no);
			
			result=prmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt);
		}
		
		return result;
	}
	
	//검색 All
	public Vector<OrderVO> selectAll() {
		Connection con=getCon();
		PreparedStatement prmt=null;
		ResultSet re=null;
		Vector<OrderVO> vec=new Vector<>();
		OrderVO vo;
		
		String sql="select * from hambugertbl";
		try {
			prmt=con.prepareStatement(sql);
		
			re=prmt.executeQuery();
			
			while(re.next()) {
				vo=new OrderVO();
				
				vo.setNo(re.getInt(1));
				vo.setMenu(re.getString(2));
				vo.setPrice(re.getInt(3));
				vo.setNum(re.getInt(4));
				
				vec.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt, re);
		}
		
		return vec;
	}
	
	//검색 One
	public OrderVO selectOne(int no) {
		Connection con=getCon();
		PreparedStatement prmt=null;
		ResultSet re=null;
		OrderVO vo=new  OrderVO();
		
		String sql="select * from hambugertbl where no=?";
		try {
			prmt=con.prepareStatement(sql);
			
			prmt.setInt(1, no);
			
			re=prmt.executeQuery();
			
			if(re.next()) {
				vo.setNo(re.getInt(1));
				vo.setMenu(re.getString(2));
				vo.setPrice(re.getInt(3));
				vo.setNum(re.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, prmt, re);
		}
		
		return vo;
	}
	
}
