package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhonebookDAO;
import com.javaex.util.Webutil;
import com.javaex.vo.PersonVO;


@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	
	//필드
	private static final long serialVersionUID = 1L;
        
	//생성자 기본생성자 사용 --> 삭제해버림
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로직, 작동했는지 확인용
		System.out.println("pbc");
		
		//action 파라미터의 값이 뭔지 알아야 됨
		String action = request.getParameter("action");
		System.out.println(action); //업무 구분
		
		if("list".equals(action)) {
			
			System.out.println("리스트");
		
			//리스트////////////////////////////////////////////////////////////////
			
			//db 데이터 가져온다	--> list
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			
			List<PersonVO> personList = phonebookDAO.personSelect();
			System.out.println(personList);
			
			// html + list
			// 저 밑에 있는 list.jsp에게 후반일 html을 만들고 응답문서 만들어 보낸다
			
			//1)request 객체에 데이터를 넣어준다
			request.setAttribute("pList", personList);
			
			//2)list.jsp에 request 객체와 response 객체를 보낸다
			//*포워드
			
			Webutil.forward(request, response, "/WEB_INF/list.jsp");
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			*/
			
			//응답문서 바디 추가한다
		
		} else if("wform".equals(action)) {   // 등록폼 업무 (등록업무랑 구별할 것)
			
			System.out.println("등록폼");
			
			//등록폼을 응답해야 한다
			//1)DB관련 할일이 없다 - 안하면 된다
			
			//2)jsp에게 화면을 그리게 한다(포워드)
			//writeForm.jsp 포워드한다
			Webutil.forward(request, response, "/WEB_INF/writeForm.jsp");
			
			
			
		} else if("write".equals(action)) {  //등록 업무
			
			System.out.println("등록");
			//파라미터 3개 꺼내기
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVO personVO = new PersonVO(name, hp, company);
			System.out.println(personVO);
			
			
			
			//DAO를 통해서 저장시키기
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			phonebookDAO.personInsert(personVO);
			
			// 리다이렉트 list 요청해주세요
			// http://localhost:8080/phonebook2/pbc?action=list
			
			//response.sendRedirect("http://localhost:8080/phonebook2/pbc?action=list");		
			Webutil.redirect(request, response, "http://localhost:8080/phonebook2/pbc?action=list");
			
			/*
			//응답 (리스트) 하기 ------------------------------------------
			
			// -- DAO시켜서 데이터 가져오기
			List<PersonVO> personList = phonebookDAO.personSelect();
			
			//request의 객체에 데이터를 넣어준다
			request.setAttribute("pList", personList);
			
			//list.jsp에 request 객체와 respons 객체를 보낸다
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			
			//포워드
			rd.forward(request, response);
			*/
			
		} else if("delete".equals(action)) {
			System.out.println("삭제");
			
			//파라미터에서 no 값 꺼내온다
			int no = Integer.parseInt(request.getParameter("no"));
			
			//DAO를 통해서 no를 주고 삭제
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			phonebookDAO.personDelete(no);
			
			//리다이렉트 action=list
			//response.sendRedirect("http://localhost:8080/phonebook2/pbc?action=list");
			Webutil.redirect(request, response, "http://localhost:8080/phonebook2/pbc?action=list");
			
		} else if("uform".equals(action)) {
			System.out.println("수정폼");
			
			int no = Integer.parseInt(request.getParameter("no"));
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			PersonVO person = phonebookDAO.personSelectOne(no);
			
			request.setAttribute("person", person);
			
			
			Webutil.forward(request, response, "/WEB_INF/updateForm.jsp");
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/updateForm.jsp");
			rd.forward(request, response);
			*/
			
		} else if("update".equals(action)) {
			
			System.out.println("수정");
			
			//파라미터 4개 꺼내기
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int personId = Integer.parseInt(request.getParameter("person_id"));
			
			PersonVO personVO = new PersonVO(name, hp, company, personId);
			System.out.println(personVO);
			
			//DAO를 통해 수정시키기
			PhonebookDAO phonebookDAO = new PhonebookDAO();
			phonebookDAO.personUpdate(personVO);
			
			Webutil.redirect(request, response, "http://localhost:8080/phonebook2/pbc?action=list");
			//response.sendRedirect("http://localhost:8080/phonebook2/pbc?action=list");
			 
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
