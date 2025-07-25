package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVO;

public class PhonebookDAO {

    //필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/phonebook_db";
	private String id = "phonebook";
	private String pw = "phonebook";

    //생성자
	public void phonebookDAO() {
		
	}

    //메소드gs
    //자원정리 메소드 - 공통
	// DB연결 메소드
	private void connect() { // 메인에서는 사용하지 못함

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}

    //전체리스트 가져오기
	public List<PersonVO> personSelect() {
		System.out.println("personSelect()");
		List<PersonVO> personList = new ArrayList<PersonVO>();
		
		this.connect();
		
		try {
			
			//SQL문 준비
			String query = "";
			query += " select person_id, ";
			query += " 		  name, ";
			query += " 		  hp, ";
			query += " 		  company ";
			query += " from person ";
			query += " order by person_id asc ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVO personVO = new PersonVO(personId, name, hp, company);
				
				personList.add(personVO);
			}
			
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
		
		
		this.close();
		return personList;
	}
	
	
	public int personInsert(PersonVO personVO) {
		int count = -1;
		
		System.out.println("personInsert()");
		this.connect();
		
		try {
			
			//SQL문 준비
			String query = "";
			query += " insert into person ";
			query += " values(null, ?, ?, ?) ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVO.getName());
			pstmt.setString(2, personVO.getHp());
			pstmt.setString(3, personVO.getCompany());
			
			//실행
			count = pstmt.executeUpdate();
			
			//결과처리
			System.out.println(count + "건이 저장되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		this.close();
		return count;
	}
	
	
	public int personDelete(int no) {
		System.out.println("personDelete");
		int count = -1;
		this.connect();
		
		try {
			
			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건 삭제되었습니다.");
			
			
			
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
		
		
		
		this.close();
		return count;
	}
	
	public int personUpdate(PersonVO personVO) {
		int count = -1;
		
		this.connect();
		
		
		
		try {
			
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += " hp = ?, ";
			query += " company = ? ";
			query += " where person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVO.getName());
			pstmt.setString(2, personVO.getHp());
			pstmt.setString(3, personVO.getCompany());
			pstmt.setInt(4, personVO.getPersonId());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 수정되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		
		this.close();
		return count;
	}
	
	public PersonVO personSelectOne(int no) {
		PersonVO personVO = null;
		
		this.connect();
		
		try {
			
			String query = "";
			query += " select person_id, ";
			query += " 		  name, ";
			query += " 		  hp, ";
			query += " 		  company ";
			query += " from person ";
			query += " where person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			int id = rs.getInt("person_id");
			String name = rs.getString("name");
			String hp = rs.getString("hp");
			String company = rs.getString("company");
			
			personVO = new PersonVO(id, name, hp, company);
			
				
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		this.close();
		
		return personVO;
	}
	
}	
	
	
	
	
	


