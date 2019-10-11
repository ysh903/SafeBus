package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.biz.user.service.UserService;
import spring.biz.user.vo.UserVO;

@Controller
public class LoginController {

	@Autowired
	UserService service;

	String result = "";

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginProc(UserVO vo, HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/plain; charset=UTF8");
		PrintWriter out;
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println("1");
		System.out.println(vo);
		System.out.println(id + "," + pw);
		UserVO user = service.login(id, pw);
		System.out.println(user);
		// UserVO user = service.login(vo.getUserid(), vo.getUserpwd());
		System.out.println("2");

		if (user != null) {
			request.getSession().setAttribute("User", user);
			request.getSession().setAttribute("login", user);

			
			try {
				out = response.getWriter();

				// Array list 안에 있는것을 json으로 만들어줌
				// 만들어진 json을 쏴주기만 하면 된다
				// ObjectMapper mapper = new ObjectMapper();
				// String json = mapper.writeValueAsString(user);

				result = "succes";
				out.print(result);
				out.flush();
				out.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		} else {
			request.setAttribute("msg", "로그인 정보를 다시입력하세요.");
			try {
				
				out = response.getWriter();
				result = "fail";
				out.print(result);
				out.flush();
				out.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

	}

	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		//HttpSession session = request.getSession();
		//if(session!=null)
		//	session.invalidate();
		
		response.setContentType("text/plain; charset=UTF8");
		PrintWriter out;
		
		request.getSession().invalidate();
		request.setAttribute("msg", "로그아웃되었습니다.");
		System.out.println("로그아웃 되었습니다. ");
		try {
			out = response.getWriter();

			// Array list 안에 있는것을 json으로 만들어줌
			// 만들어진 json을 쏴주기만 하면 된다
			// ObjectMapper mapper = new ObjectMapper();
			// String json = mapper.writeValueAsString(user);

			result = "logout";
			out.print(result);
			out.flush();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}

}
