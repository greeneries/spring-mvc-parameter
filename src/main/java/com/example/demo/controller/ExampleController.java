package com.example.demo.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.vo.User;



// 이 애노테이션은 브라우저와 대화하는 역할임을 나타낸다.
@Controller
public class ExampleController {

	
	// 설정 조건을 모두 만족해야 메소드가 기동한다. (AND 연산)
	// value 속성으로 연동할 URI를 설정한다.
	// method 속성으로 연동할 HTTP 프로토콜 방식을 설정한다.
	// produces 속성으로 무엇을 응답하는지 설정한다. (결과를 이걸로 줄께..)
	// consumes 속성으로 무엇을 받는지 설정한다.
	@RequestMapping(value = {"/example1", "/ex1"}, 
			method = RequestMethod.GET, 
			produces = { "text/plain" }, // 내가 결과는 이걸로 줄께
			consumes = { "text/plain", "application/*", "text/html" }) // 클라이언트로부터 받는 데이터의 MIME 타입
	@ResponseBody  // 뷰를 거치지 않고 리턴값을 그대로 브라우저에게 전달한다.
	public String example1() {
		return "example1";
	}
	
	
	
	// URI가 example2 문자열이라면 메소드가 기동한다.
	// Model 객체는 컨트롤러에서 Dispatcher Servelet에게 데이터를 전달하는 용도의 객체다.
	// 서블릿처럼 HttpServletRequest, HttpServletResponse 
	// 객체를 파라미터로 받을 수 있다.
	// Locale 객체는 브라우저가 신고한 HTTP header정보에서 구현
	// 클라이언트의 지역정보가 담겨 있다.
	@GetMapping("/example2")
	public String example2(
			HttpServletRequest req, Model model,
			HttpSession session, Locale locale) {
		
		req.setAttribute("value1", 100);
		
		
		// Dispatcher Servlet에서 모델객체에 담긴 데이터를 HttpServletRequest로 옮겨 싫는다.
		// JSP에서 HttpServletRequest 객체에 담긴 값을 EL로 사용한다.
		model.addAttribute("value2", 200);
		
		// Session:
		// 브라우저가 접속하면 자동으로 SESSIONID를 쿠키로 발급한다.
		// 쿠키로 SESSIONID를 신고하면 발급하지 않고 그대로 사용한다.
		// 세션 객체는 사용할 때 만들어진다. req.getSession() 메소드가 
		// 호출될때 세션 객체가 이미 있으면 그대로 사용하고
		// 없으면 새로 만들어서 리턴한다.
		System.out.println(session == req.getSession());
		System.out.println(locale.getCountry() + "," + locale.getLanguage());
		
		//req나 model 둘다 example2.jsp에게 돌려준다.
		return "example2";
	}
	
	// 브라우저가 보내는 데이터를 받고자 할 때  vo를 만들어서 이용하거나 Map객체를 주로 사용한다.
	@PostMapping("/example3")
	public ModelAndView example3(
			Map<String, String> map, User user) {
		// HTML에서 name="id" 설정으로 사용되는 키 값 "id"와 일치하는
		// 멤버변수가 User 클래스안에 존재하는 경우 자동으로 값이 
		// User 객체의 멤버변수에 주입된다.
		System.out.println(map);
		System.out.println(user);
		
		// ModelAndView는 연동할 뷰와 전달할 데이터를 한 번에 
		// 취급하는 객체다.
		ModelAndView mav = new ModelAndView();
		mav.setViewName("example3");
		mav.addObject("map", map);
		mav.addObject("user", user);
		
		return mav;
	}
	

	
	
	// @RequestParam 으로 설정했는데 전달되는 파라미터가 없다면
	// 에러가 발생한다. required=false 설정으로 막을 수 있다.
	// @RequestParam은 파라미터를 낱개로 지정해서 보내고 싶을 때 사용한다. 
	@GetMapping("/example4") // @RequestMapping의 요청 방식 Get하고 동일
	@ResponseBody
	public String example4(
			HttpServletRequest req,
			@RequestParam(required=true) String id,
			@RequestParam(name="a", required=false, defaultValue="100") int age) {
		
		System.out.println(id);
		System.out.println(age);
		
		return req.getRequestURI();
	}
	
	// URI 부분에 {패스홀더} 설정을 하면
	// @PathVariable("패스홀더") 애노테이션을 사용하여
	// URI 패스홀더 자리에 문자열을 메소드에 파라미터로
	// 받을 수 있다.
	@GetMapping("/example5/{id}/devices/{no}")
	@ResponseBody
	public String example5(
			@PathVariable("id") String id,
			@PathVariable int no) {
		
		System.out.println(id);
		System.out.println(no);
		
		return "id = "+id + ", no = " + no;
	}
}