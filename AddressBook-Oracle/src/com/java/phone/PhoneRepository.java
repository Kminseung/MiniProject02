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
			
			String sql = "SELECT * FROM phone_book ORDER BY id";
			rs = stmt.executeQuery(sql);
			
			while(rs.next() ) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String tel = rs.getString("tel");
				
				PhoneVo pVo = new PhoneVo(id, name, hp, tel);
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
	
	public boolean insert(PhoneVo vo) {
		Connection conn = null;
		String sql = "INSERT INTO phone_book VALUES(seq_phone_book.NEXTVAL, ?, ?, ?)";
		int insertedCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
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
	
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
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
	
	public List<PhoneVo> search(String name) {
		List<PhoneVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT * FROM phone_book WHERE name LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PhoneVo pVo = new PhoneVo();
				pVo.setId(rs.getLong(1));
				pVo.setName(rs.getString(2));
				pVo.setHp(rs.getString(3));
				pVo.setTel(rs.getString(4));
				list.add(pVo);
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
}