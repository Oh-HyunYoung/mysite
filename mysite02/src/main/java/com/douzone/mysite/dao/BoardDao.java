package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;


public class BoardDao {
	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<BoardVo>();
		//int hit = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

//		BoardVo vo = new BoardVo();
		
		try {
			conn = getConnection();

			String sql = "select b.no, b.title, b.contents, u.name, b.hit, b.reg_date, b.g_no, b.o_no, b.depth, b.user_no from board b, user u where b.user_no = u.no order by g_no desc, o_no asc";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
//			if(rs.next()) {
//				hit = rs.getInt(5);
//				hit++;
//				
//			}
//			
//			
//			pstmt.setLong(1, vo.getNo());
//			pstmt.setString(2, vo.getTitle());
//			pstmt.setString(3, vo.getContents());
//			pstmt.setString(4, vo.getName());
//			pstmt.setInt(5, hit);
//			pstmt.setString(6, vo.getReg_date());
//			pstmt.setLong(7, vo.getDepth());
//			pstmt.setLong(8, vo.getGroup_no());
//			pstmt.setLong(9, vo.getOrder_no());
//			pstmt.setLong(10, vo.getDepth());
//			pstmt.setLong(11, vo.getUser_no());
//			
//			pstmt.executeUpdate();
//			result.add(vo);
			
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String name = rs.getString(4);
				int hit = rs.getInt(5);
				String reg_date = rs.getString(6);
				Long group_no = rs.getLong(7);
				Long order_no = rs.getLong(8);
				Long depth = rs.getLong(9);
				Long user_no = rs.getLong(10);
				
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setName(name);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public BoardVo findByNo(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from board where no=?";
		BoardVo vo = new BoardVo();

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				int hit = rs.getInt(4);
				String reg_date = rs.getString(5);
				Long group_no = rs.getLong(6);
				Long order_no = rs.getLong(7);
				Long depth = rs.getLong(8);
				Long user_no = rs.getLong(9);

				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setReg_date(reg_date);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);

			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
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
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
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

			String sql = "insert into board(no,title,contents,reg_date,user_no) values(null,?,?,now(),?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUser_no());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
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

			String sql = "update board set title = ?, contents = ? where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteByNo(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete from board where no =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
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
