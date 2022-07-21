package com.haeun.ch2901;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haeun.ch2901.dao.IDao;
import com.haeun.ch2901.dto.ContentDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
//	ContentDao dao;
	
	@Autowired	//외부에서 객체를 생성해서 주입
	
	//servlet.context.xml에 생성해놓은 bean 초기화
	private SqlSession sqlSession;
	
//	@Autowired
//	public void setDao(ContentDao dao) {
//		this.dao = dao;
//	}
	
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		
		return "writeForm";
	}
	
	@RequestMapping(value = "write")
	public String write(HttpServletRequest request) {
		
		//request객체로 파라미터 값 전달
		String mwriter = request.getParameter("mwriter");
		String mcontent = request.getParameter("mcontent");
		
		//sqlSession(IDao 클래스로 만든 객체)에서 mapper 가져오기
		IDao idao = sqlSession.getMapper(IDao.class);
		
		//IDao에서 만든 writeDao 호출
		idao.writeDao(mwriter, mcontent);
		
//		dao.writeDao(mwriter, mcontent);
		
		return "redirect:list";
		//새로고침 - 요청을 다시 돌려서 보여줌
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
		//sqlSession(IDao 클래스로 만든 객체)에서 mapper 가져오기
		IDao idao = sqlSession.getMapper(IDao.class);
		
		//IDao에서 만든 ArrayList 호출
		ArrayList<ContentDto> dtos = idao.listDao();
		
		//list란 이름으로 dtos(list)를  model 객체에 실어서 보여줌
		model.addAttribute("list", dtos);
		
		return "list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request) {
		
		IDao idao = sqlSession.getMapper(IDao.class);
		
		idao.deleteDao(request.getParameter("mid"));
		
		return "redirect:list";
	}
}
