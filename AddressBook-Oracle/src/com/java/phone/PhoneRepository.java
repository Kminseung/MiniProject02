package com.java.phone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneRepository implements PhoneDao {
	// 공통 코드인 접속 코드 로직을 분리
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "C##KMS", "7777");
		} catch(ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		}
		return conn;
	}
	
	public List<PhoneVo> getList() {
		List<PhoneVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT name, hp, tel FROM phone_book";
			rs = stmt.executeQuery(sql);
			
			while(rs.next() ) {
				String name = rs.getString("name");
				Long hp = rs.getLong("hp");
				Long tel = rs.getLong("tel");
				
				PhoneVo pVo = new PhoneVo(name, hp, tel);
				list.add(pVo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<PhoneVo> search(String name) {
		List<PhoneVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT name, hp, tel FROM phone_book WHERE name LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PhoneVo vo = new PhoneVo();
				vo.setName(rs.getString(1));
				vo.setHp(rs.getLong(2));
				vo.setTel(rs.getLong(3));
				list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
			
	public boolean insert(PhoneVo vo) {
		Connection conn = null;
		String sql = "INSERT INTO phone_book VALUES(seq_phone_book.NEXTVAL, ?, ?, ?)";
		int insertedCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  vo.getName());
			pstmt.setLong(2, vo.getHp());
			pstmt.setLong(3, vo.getTel());
			
			insertedCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return insertedCount == 1;
	}

	public boolean delete(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book WHERE name = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + name + "%");
			deletedCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return deletedCount == 1;
	}
}