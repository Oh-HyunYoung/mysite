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
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select b.no, b.title, b.contents, u.name, b.hit, b.reg_date, b.g_no, b.o_no, b.depth, b.user_no from board b, user u where b.user_no = u.no order by g_no desc, o_no asc";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();



			while (rs.next()) {
				BoardVo vo = new BoardVo();

				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String name = rs.getString(4);
				Long hit = rs.getLong(5);
				String reg_date = rs.getString(6);
				Long group_no = rs.getLong(7);
				Long order_no = rs.getLong(8);
				Long depth = rs.getLong(9);
				Long user_no = rs.getLong(10);

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
				Long hit = rs.getLong(4);
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

			String sql = "insert into board values(null, ?, ?, 0, now(),?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getGroup_no());
			pstmt.setLong(4, vo.getOrder_no());
			pstmt.setLong(5, vo.getDepth());
			pstmt.setLong(6, vo.getUser_no());

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

	public void updatehit(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set hit = hit+1 where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
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

	public void updateNo(Long gno, Long ono) {
		List<BoardVo> result = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();

			String sql = "select * from board where g_no =? and o_no > ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, gno);
			pstmt.setLong(2, ono);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				BoardVo vo = new BoardVo();

				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				Long hit = rs.getLong(4);
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
				vo.setOrder_no(order_no+1);
				vo.setDepth(depth);
				vo.setUser_no(user_no);

				result.add(vo);
			}
			
			for(BoardVo vos:result) {
				String sql1 = "update board set o_no = ? where no=?";
				pstmt = conn.prepareStatement(sql1);

				pstmt.setLong(1, vos.getOrder_no());
				pstmt.setLong(2, vos.getNo());
				pstmt.executeUpdate();
				
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
	}

	public void insertByContent(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board(no,title,contents,reg_date) values(null,?,?,now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());

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

	public Long maxgno() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Long result = 0L;

		try {
			conn = getConnection();

			String sql = "select max(g_no) from board";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				result =  rs.getLong(1);
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
	
	public List<BoardVo> searchList(String text) {
		List<BoardVo> search = new ArrayList<BoardVo>();
		Connection conn = null;
		ResultSet rs = null;

		String sql ="select b.no, b.title, b.contents, u.name, b.hit, b.reg_date, b.g_no, b.o_no, b.depth, b.user_no from board b, user u where title";
		try {
			conn = getConnection();
			
			if(text != null && !text.equals("")) {
				sql +=" LIKE '%"+text.trim()+"%' order by g_no desc, o_no asc";

			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String name = rs.getString(4);
				Long hit = rs.getLong(5);
				String reg_date = rs.getString(6);
				Long group_no = rs.getLong(7);
				Long order_no = rs.getLong(8);
				Long depth = rs.getLong(9);
				Long user_no = rs.getLong(10);

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

				search.add(vo);
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
		return search;

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
