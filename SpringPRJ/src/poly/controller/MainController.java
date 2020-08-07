package poly.controller;

import static poly.util.CmmUtil.nvl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.UserDTO;
import poly.service.IUserService;

@Controller
public class MainController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "UserService")
	IUserService userService;

	@RequestMapping(value = "index")
	public String Index() {

		return "/index";
	}

	@RequestMapping(name = "The/TheLogin")
	public String TheLogin() {
		log.info("TheLogin 시작");
		log.info("TheLogin 종료");
		return "/The/TheLogin";
	}

	@RequestMapping(value = "The/TheLoginProc")
	public String TheLoginProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info("/The/TheLoginProc 시작");
		String id = nvl(request.getParameter("id"));
		String pwd = nvl(request.getParameter("pwd"));

		log.info("id :" + id);
		log.info("pwd :" + pwd);

		UserDTO tDTO = new UserDTO();

		tDTO.setUser_id(id);
		tDTO.setUser_pwd(pwd);

		tDTO = userService.getUserInfo(tDTO);
		log.info("uDTO null? : " + (tDTO == null));

		String msg = "";
		String url = "";
		if (tDTO == null) {
			msg = "로그인 실패";
		} else {
			log.info("tDTO.User_id : " + tDTO.getUser_id());
			log.info("tDTO.User_name : " + tDTO.getUser_email());
			msg = "로그인 성공";
			session.setAttribute("user_id", tDTO.getUser_id());
			session.setAttribute("user_name", tDTO.getUser_email());
		}

		url = "/index.do";

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info("The/TheLoginProc 종료");

		return "/redirect";
	}

	@RequestMapping(value = "The/TheLogout")
	public String TheLogout(HttpSession session, Model model) throws Exception {

		log.info("/The/TheLogout 시작");

		String msg = "";
		String url = "";

		msg = "로그아웃 성공";

		url = "/index.do";
		session.invalidate();

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		log.info("/The/TheLogout 종료");

		return "/redirect";
	}
	
	@RequestMapping(value = "/hello/text")
	public String Hello() {
		
		return "/hello";
	}
	
	@ResponseBody
	@RequestMapping(value = "/hello/hello")
	public boolean Hello(HttpServletRequest request) {
		String value = request.getParameter("id");
		
		if(value.equals("헬로")) {
			return true;
		}
		
		return false;
	}

	@RequestMapping(value = "The/TheSignUp")
	public String TheSignUp() {

		log.info("TheSignUp 시작");

		log.info("TheSignUp 종료");

		return "/The/TheSignup";
	}

	@RequestMapping(value = "The/TheSignUpProc", method = RequestMethod.GET)
	public String TheSignUpProc(HttpServletRequest request, ModelMap model) {

		log.info("/The/TheSignUpProc 시작");
		
		log.info("request.getParameter 시작");
		
		String user_id = request.getParameter("id");
		String user_pwd = request.getParameter("pwd");
		String user_email = request.getParameter("email");
		String user_gender = request.getParameter("gender");
		String user_age = request.getParameter("age");
		String[] user_interest = request.getParameterValues("interest");
		
		
		log.info("request.getParameter 종료");
		
		log.info("user_id : " + user_id);
		log.info("user_pwd : " + user_pwd);
		log.info("user_name : " + user_email);
		log.info("user_gender : " + user_gender);
		
		// 매우중요!! - 콤마로 조인
		String interests = String.join(",", user_interest);
		log.info("interest : " + interests);
		
		
		
		UserDTO tDTO = new UserDTO();
		log.info("tDTO.set 시작");
		tDTO.setUser_id(user_id);
		tDTO.setUser_pwd(user_pwd);
		tDTO.setUser_email(user_email);
		tDTO.setUser_gender(user_gender);
		tDTO.setUser_age(user_age);
		tDTO.setUser_interest(interests);
		
		log.info("tDTO.set 종료");
		log.info("tDTO" + tDTO);

		log.info("TheService.TheSignUp 시작");
		int res = userService.UserSignUp(tDTO);
		log.info("TheService.TheSignUp 종료");
		log.info("res : " + res);

		String msg;
		String url = "/The/TheLogin.do";

		if (res > 0) {
			msg = "회원가입에 성공했습니다.";
		} else {
			msg = "회원가입에 실패했습니다.";
		}

		log.info("model.addAttribute 시작");
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		log.info("model.addAttribute 종료");
		
		log.info("TheSignUpProc 종료");

		return "/redirect";
	}

	@ResponseBody
    @RequestMapping(value="/The/idCheck", method = RequestMethod.POST)
    public int idCheck(HttpServletRequest request) throws Exception {
        log.info("idCheck 시작");
        
        String userId = request.getParameter("userId");
        
        log.info("TheService.idCheck 시작");
        UserDTO idCheck = userService.idCheck(userId);
        log.info("TheService.idCheck 종료");
        
        int result=0;
        
        log.info("if 시작");
        if(idCheck!=null) result=1;
        
        log.info("result : " + result);
        log.info("if 종료");
        
        log.info("idCheck 종료");
        return result;
    }

	@ResponseBody
	@RequestMapping(value="/The/emailCheck", method = RequestMethod.POST)
	public int emailCheck(HttpServletRequest request) throws Exception{
		log.info("emailCheck 시작");
		
		String userEmail = request.getParameter("userEmail");
		log.info("TheService.emailCheck 시작");
        UserDTO emailCheck = userService.emailCheck(userEmail);
        log.info("TheService.emailCheck 종료");
        
        int res = 0;
        
        log.info("if 시작");
        if(emailCheck!=null) res=1;
        
        log.info("result : " + res);
        log.info("if 종료");
        
        log.info("emailCheck 종료");
        return res;
	}
	
}
