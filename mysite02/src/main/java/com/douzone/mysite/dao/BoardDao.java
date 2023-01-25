package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;


public class BoardDao {
	public  List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<BoardVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			String sql = "select b.no, b.title, b.contents, u.name, b.hit, b.reg_date from board b, user u where b.user_no = u.no order by g_no desc, o_no asc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String name = rs.getString(4);
				Long hit = rs.getLong(5);
				String reg_date = rs.getString(6);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setName(name);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public BoardVo findByNo(Long no) {
		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql =
				"select title, contents from board where no=?";
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new BoardVo();

				String title = rs.getString(1);
				String contents = rs.getString(2);

				vo.setTitle(title);
				vo.setContents(contents);
			}
		}
		catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	
	public void insert(BoardVo vo) {
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	         conn = getConnection();
	         
	         String sql = "insert into board values(null, ?, ?, ?, now(),?,?,?,?)";
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, vo.getTitle());
	         pstmt.setString(2, vo.getContents());
	         pstmt.setLong(3, vo.getHit());
	         pstmt.setLong(4, vo.getGroup_no());
	         pstmt.setLong(5, vo.getOrder_no());
	         pstmt.setLong(6, vo.getDepth());
	         pstmt.setLong(7, vo.getUser_no());
	         
	         pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         System.out.println("error:" + e);
	      } finally {
	         try {
	            if(pstmt != null) {
	               pstmt.close();
	            }
	            
	            if(conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	   }
	public void insertByContent(BoardVo vo) {
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	         conn = getConnection();
	         
	         String sql = "insert into board(title,contents,user_no) values(?,?,?)";
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, vo.getTitle());
	         pstmt.setString(2, vo.getContents());
	         
	         pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         System.out.println("error:" + e);
	      } finally {
	         try {
	            if(pstmt != null) {
	               pstmt.close();
	            }
	            
	            if(conn != null) {
	               conn.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	   }

	public void update(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

				String sql = "update board set title = ?, contents = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getTitle());
		        pstmt.setString(2, vo.getContents());
		
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.10.101:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return conn;
	}
}
