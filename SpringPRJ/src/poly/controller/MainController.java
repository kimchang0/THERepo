package poly.controller;

import static poly.util.CmmUtil.nvl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.UserDTO;
import poly.service.IMailService;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Controller
public class MainController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "UserService")
	IUserService userService;

	@Resource(name = "MailService")
	IMailService MailService;
	
	@RequestMapping(value = "index")
	public String Index() {

		return "/index";
	}

	@RequestMapping(name = "The/TheLogin")
	public String TheLogin(HttpSession session) {
		log.info("TheLogin 시작");
		session.invalidate();
		log.info("TheLogin 종료");
		return "/The/TheLogin";
	}

	@RequestMapping(value = "The/TheLoginProc")
	public String TheLoginProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info("/The/TheLoginProc 시작");
		String id = nvl(request.getParameter("id"));
		String pwd = nvl(EncryptUtil.enHashSHA256(request.getParameter("pwd")));

		log.info("id :" + id);
		log.info("pwd :" + EncryptUtil.enHashSHA256(pwd));

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
			log.info("tDTO.User_email : " + tDTO.getUser_email());
			msg = "로그인 성공";
			session.setAttribute("user_id", tDTO.getUser_id());
			session.setAttribute("user_email", tDTO.getUser_email());
			
			int res = MailService.loginCheck(tDTO);
			if(res == 1) {
				log.info("로그인 확인메일 발송 성공");
			} else {
				log.info("로그인 메일확인 발송 실패");
			}
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

	@RequestMapping(value = "The/TheSignUpProc", method = RequestMethod.POST)
	public String TheSignUpProc(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {

		log.info("/The/TheSignUpProc 시작");
		
		log.info("request.getParameter 시작");
		
		String user_id = request.getParameter("id");
		String user_pwd = nvl(request.getParameter("pwd"));
		String user_gender = request.getParameter("gender");
		String user_age = request.getParameter("age");
		String[] user_interest = request.getParameterValues("interest");
		
		
		log.info("request.getParameter 종료");
		
		log.info("user_id : " + user_id);
		log.info("user_pwd : " + user_pwd);
		log.info("user_gender : " + user_gender);
		
		// 매우중요!! - 콤마로 조인
		String interests = String.join(",", user_interest);
		log.info("interest : " + interests);
		
		String HashEnc = EncryptUtil.enHashSHA256(user_pwd);
		
		UserDTO tDTO = new UserDTO();
		log.info("tDTO.set 시작");
		tDTO.setUser_id(user_id);
		tDTO.setUser_pwd(HashEnc);
		tDTO.setUser_gender(user_gender);
		tDTO.setUser_age(user_age);
		tDTO.setUser_interest(interests);
		
		log.info("tDTO.set 종료");
		log.info("tDTO" + tDTO);
		
		session.setAttribute("user_id", tDTO.getUser_id());
		log.info("sessionSet user_id : " + session.getAttribute("user_id"));
		
		log.info("TheService.TheSignUp 시작");
		int res = userService.UserSignUp(tDTO);
		log.info("TheService.TheSignUp 종료");
		log.info("res : " + res);

		String msg;
		String url = "/The/TheEmailCertify.do";

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
        if(idCheck!=null) { 
        	result=1;
        }
        log.info("result : " + result);
        log.info("if 종료");
        
        log.info("idCheck 종료");
        return result;
    }

	@ResponseBody
	@RequestMapping(value="/The/emailCheck", method = RequestMethod.POST)
	public int emailCheck(HttpServletRequest request) throws Exception{
		log.info(this.getClass().getName() + "emailCheck 시작");
		
		String userEmail = EncryptUtil.encAES128CBC(request.getParameter("userEmail"));
		log.info("TheService.emailCheck 시작");
        UserDTO emailCheck = userService.emailCheck(userEmail);
        log.info("TheService.emailCheck 종료");
        
        int res = 0;
        
        log.info("if 함수 시작");
        if(emailCheck!=null) res=1;
        
        log.info("result : " + res);
        log.info("if 함수 종료");
        
        log.info("emailCheck 종료");
        return res;
	}
	
	 public String RandomNum() {
	    	StringBuffer buffer = new StringBuffer();
	    	for(int i = 0; i < 6; i++) {
	    		int n = (int)(Math.random() * 10);
	    		buffer.append(n);
	    		
	    	}
	    	return buffer.toString();
	 }
	 	
	 	@RequestMapping(value = "/The/TheEmailCertify")
		public String EmailCertify() {

			log.info("/The/TheEmailCertify 시작");

			log.info("/The/TheEmailCertify 종료");

			return "/The/TheEmailCertify";
		}
	 
	 	@ResponseBody
		@RequestMapping(value="/The/TheEmailCertifyProc", method = RequestMethod.POST)
		public int TheEmailCertify(HttpServletRequest request, HttpSession session) throws Exception{
			log.info("/The/TheEmailCertify 시작");
	        
	    	int result = 0;
	    	String email = EncryptUtil.encAES128CBC(request.getParameter("email"));
	    	log.info("email : " + EncryptUtil.decAES128CBC(email));
	    	String authNum = "";
	    	
	    	authNum = RandomNum();
	    	log.info("authNum : " + authNum);
	    	
	    	String user_id = (String)session.getAttribute("user_id");
	    	log.info("user_id : " + user_id);
	    	
	    	
			
	    	UserDTO uDTO = new UserDTO();
			uDTO.setUser_email(email);
			uDTO.setUser_authNum(authNum);
			uDTO.setUser_id(user_id);
			log.info("setUser_authNum : " + uDTO.getUser_authNum());
			log.info("setUser_email : " + uDTO.getUser_email());
			log.info("setUser_id : " + uDTO.getUser_id());
			
			int res = MailService.doSendMail(uDTO);
			
			if (res == 1) {
				log.info(this.getClass().getName() + "mail.sendMail success");
				result = 1;
			} else {
				log.info(this.getClass().getName() + "mail.sendMail fail");
				result = 0;
			}
			log.info("setUser_email : " + uDTO.getUser_email());
			
			log.info("insertAuthNum 시작");
			int res2 = userService.insertAuthNum(uDTO);
			log.info("insertAuthNum 종료");
			log.info("res2 : " + res2);
			
			if(res2 == 1) {
				log.info("update success");
			} else {
				log.info("update fail");
			}
			
			session.invalidate();
	        log.info("/The/TheEmailCertify 종료");
	        return result;
		}
	 	
	 	@ResponseBody
        @RequestMapping(value = "/The/authNumCheck", method = RequestMethod.POST)
        public int authNumCheck(HttpServletRequest request) throws Exception {
        	log.info("/myOrder/authNumCheck 시작");
        	
        	int result = 0;
        	log.info("request.getParameter 시작");
        	String auth = request.getParameter("auth");
        	log.info("auth : " + auth);
        	log.info("request.getParameter 종료");
        	
        	UserDTO uDTO = new UserDTO();

    		uDTO.setUser_authNum(auth);

    		
        	log.info("userService.authNumCheck 시작");
    		UserDTO authNumCheck = userService.authNumCheck(uDTO);
    		log.info("userService.authNumCheck 종료");
    		log.info("authNumCheck null ? " + (authNumCheck == null));
    		
    		log.info("if 시작");
    		if (authNumCheck != null) {
    			log.info(this.getClass().getName() + "authNumCheck success");
    			result = 1;
    		} else {
    			log.info(this.getClass().getName() + "authNumCheck fail");
    			result = 0;
    		}
    		log.info("if 종료");
    		
        	log.info("/The/authNumCheck 종료");
        	return result;
        }
	 	
	 	@RequestMapping(value = "/The/setting")
		public String setting() {

			log.info("/The/setting 시작");

			log.info("/The/setting 종료");

			return "/The/setting";
		}
	 	
	 	@RequestMapping(value = "/Setting/TheMypageCheck")
		public String TheMypageCheck() {

			log.info("/The/Setting/TheMypageCheck 시작");

			log.info("/The/Setting/TheMypageCheck 종료");

			return "/Setting/TheMypageCheck";
		}
	 	
	 	@ResponseBody
	 	@RequestMapping(value = "/Setting/TheMypageCheckProc", method = RequestMethod.POST)
		public int TheMypageCheckProc(HttpServletRequest request, HttpSession session) throws Exception {

			log.info("/The/TheMypageCheckProc 시작");
			int result = 0;
			log.info("String 변수저장 시작");
			String user_pwd = request.getParameter("pwd");
			String user_id = (String) session.getAttribute("user_id");
			log.info("String 변수저장 종료");
			log.info("user_pwd : " + user_pwd);
			log.info("user_id : " + user_id);
			
			String HashEnc = EncryptUtil.enHashSHA256(user_pwd);
			
			UserDTO uDTO = new UserDTO();
			log.info("uDTO.set 시작");
			uDTO.setUser_pwd(HashEnc);
			uDTO.setUser_id(user_id);
			log.info("uDTO.set 종료");
			
			log.info("userService.Userinquire 시작");
			UserDTO res = userService.Userinquire(uDTO);
			log.info("userService.Userinquire 종료");
			log.info("res : " + res);
			
			if(res != null) {
				result = 1;
			} else {
				result = 0;
			}
			
			log.info("result :" + result);
			log.info("/Setting/TheMypageCheckProc 종료");

			return result;
		}
	 	
	 	@RequestMapping(value = "/Setting/TheMypage")
		public String TheMypage() {

			log.info("/Setting/TheMypage 시작");

			log.info("/Setting/TheMypage 종료");

			return "/Setting/TheMypage";
		}
	 	
	 	@RequestMapping(value = "/Setting/TheReViewTutorial")
		public String TheReViewTutorial() {

			log.info("/The/Setting/TheReViewTutorial 시작");

			log.info("/The/Setting/TheReViewTutorial 종료");

			return "/Setting/TheReViewTutorial";
		}
	 	
	 	@RequestMapping(value = "/Setting/TheAppIntroduction")
		public String TheAppIntroduction() {

			log.info("/The/Setting/TheAppIntroduction 시작");

			log.info("/The/Setting/TheAppIntroduction 종료");

			return "/Setting/TheAppIntroduction";
		}
	 	
	 	@RequestMapping(value = "/Mypage/TheInterestSetting")
		public String TheInterestSetting(HttpSession session, Model model) {

			log.info("/The/Setting/Mypage/TheInterestSetting 시작");
			
			String user_id = (String) session.getAttribute("user_id");
			
			UserDTO uDTO = new UserDTO();
			
			uDTO.setUser_id(user_id);
			
			UserDTO res = userService.getUserCorrection(uDTO);
			
			String user_interest = res.getUser_interest();
			
			if(user_interest == null) {
				user_interest = "";
			}
			String[] interest = user_interest.split(",");
			
			for(int i = 0; i < interest.length; i++) {
				log.info("interest["+i+"]: " + interest[i]);
			}
			
			model.addAttribute("interest", interest);
			log.info("/The/Setting/Mypage/TheInterestSetting 종료");

			return "/Mypage/TheInterestSetting";
		}
	 	
	 	@RequestMapping(value = "/Mypage/TheUserCorrection")
		public String TheUserCorrection(HttpSession session, Model model) {

	 		log.info("/Mypage/TheUserCorrection 시작");
			
			String user_id = (String) session.getAttribute("user_id");
			
			UserDTO uDTO = new UserDTO();
			
			uDTO.setUser_id(user_id);
			
			UserDTO res = userService.getUserCorrection(uDTO);
			
			if(res == null) {
				model.addAttribute("msg", "회원 정보가 없습니다. 자세한 사항은 고객센터에 문의해주세요.");
				model.addAttribute("url", "/Setting/TheMypage.do");
				return "/redirect";
			}
			
			model.addAttribute("res", res);
			
			
			log.info("/The/Setting/Mypage/TheUserCorrection 종료");

			return "/Mypage/TheUserCorrection";
		}
	 	
	 	@RequestMapping(value = "/Mypage/TheUserCorrectionDo")
		public String TheUserCorrectionDo(HttpSession session, Model model) {

			log.info("/Mypage/TheUserCorrectionDo 시작");

			
			String user_id = (String) session.getAttribute("user_id");
			
			UserDTO uDTO = new UserDTO();
			
			uDTO.setUser_id(user_id);
			
			UserDTO res = userService.getUserCorrection(uDTO);
			
			model.addAttribute("res", res);
			log.info("/The/Setting/Mypage/TheUserCorrectionDo 종료");

			return "/Mypage/TheUserCorrectionDo";
		}
	 	
	 	
	 	@RequestMapping(value = "/Mypage/ThePassWordChange")
		public String ThePassWordChange() {

			log.info("/The/Setting/Mypage/ThePassWordChange 시작");

			log.info("/The/Setting/Mypage/ThePassWordChange 종료");

			return "/Mypage/ThePassWordChange";
		}
	 	
	 	@RequestMapping(value = "/Mypage/TheUserDelete")
		public String TheUserDelete() {

			log.info("/The/Setting/Mypage/TheUserDelete 시작");

			log.info("/The/Setting/Mypage/TheUserDelete 종료");

			return "/Mypage/TheUserDelete";
		}
	 	
	 	@ResponseBody
	 	@RequestMapping(value = "/Mypage/TheUserDeleteCheck", method = RequestMethod.POST)
		public int TheUserDeleteCheck(HttpServletRequest request, HttpSession session) {

			log.info("/Mypage/TheUserDeleteCheck 시작");
			
			int result = 0;
			log.info("String 변수저장 시작");
			String DeleteCheck = request.getParameter("DeleteCheck");
			log.info("String 변수저장 종료");
			log.info("DeleteCheck : " + DeleteCheck);
			
			if(DeleteCheck.equals("탈퇴를 확인합니다.")) {
				result = 1;
			} else {
				result = 0;
			}
			
			log.info("result :" + result);
			log.info("/Mypage/TheUserDeleteCheck 종료");

			return result;
		}
	 	
	 	@RequestMapping(value = "/Mypage/TheUserDeleteProc")
		public String TheUserDeleteProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

			log.info("/The/TheUserDeleteProc 시작");
			String user_id = (String) session.getAttribute("user_id");

			UserDTO uDTO = new UserDTO();
			uDTO.setUser_id(user_id);

			int res = userService.deleteUser(uDTO);
			log.info("uDTO null? : " + (uDTO == null));

			String msg = "";
			String url = "";
			if(res>0) {
				msg = "회원 탈퇴를 성공했습니다. 이용해 주셔서 감사합니다.";
			} else {
				msg = "시스템 오류로 회원 탈퇴를 실패했습니다. 자세한 사항은 고객센터에 문의해주세요.";
			}

			url = "/index.do";

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			session.invalidate();
			
			log.info("The/TheUserDeleteProc 종료");

			return "/redirect";
		}
	
	 	@RequestMapping(value = "/Mypage/correectionProc")
		public String correectionProc(HttpSession session, HttpServletRequest request, Model model) {

			log.info("/Mypage/correectionProc 시작");
			
			String user_id =(String) session.getAttribute("user_id");
			String user_gender = request.getParameter("gender");
			String user_age = request.getParameter("age");

			UserDTO uDTO = new UserDTO();

			uDTO.setUser_id(user_id);
			uDTO.setUser_gender(user_gender);
			uDTO.setUser_age(user_age);
			
			int res = userService.setUserCorrection(uDTO);
			
			String msg;
			String url = "/Mypage/TheUserCorrection.do";

			if (res > 0) {
				msg = "회원정보 수정에 성공했습니다.";
			} else {
				msg = "시스템 오류로 회원정보 수정에 실패했습니다. 자세한 사항은 고객센터에 문의해주세요.";
			}

			log.info("model.addAttribute 시작");
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			log.info("model.addAttribute 종료");

			log.info("/Mypage/correectionProc 종료");

			return "/redirect";
		}
	 	
	 	
	 	@ResponseBody
	 	@RequestMapping(value = "/Mypage/passWordCheck", method = RequestMethod.POST)
		public int passWordCheck(HttpServletRequest request, HttpSession session) throws Exception {

			log.info("/The/passWordCheck 시작");
			int result = 0;
			log.info("String 변수저장 시작");
			String user_pwd = request.getParameter("pwd");
			String user_id = (String) session.getAttribute("user_id");
			log.info("String 변수저장 종료");
			log.info("user_pwd : " + user_pwd);
			log.info("user_id : " + user_id);
			
			String HashEnc = EncryptUtil.enHashSHA256(user_pwd);
			
			UserDTO uDTO = new UserDTO();
			log.info("uDTO.set 시작");
			uDTO.setUser_pwd(HashEnc);
			uDTO.setUser_id(user_id);
			log.info("uDTO.set 종료");
			
			log.info("userService.Userinquire 시작");
			UserDTO res = userService.Userinquire(uDTO);
			log.info("userService.Userinquire 종료");
			log.info("res : " + res);
			
			if(res != null) {
				result = 1;
			} else {
				result = 0;
			}
			
			log.info("result :" + result);
			log.info("/Setting/passWordCheck 종료");

			return result;
		}
	 	
	 	@RequestMapping(value = "/Mypage/passWordChangeProc")
		public String passWordChangeProc(HttpSession session, HttpServletRequest request, Model model) throws Exception {

			log.info("/Mypage/passWordChangeProc 시작");
			
			String user_id =(String) session.getAttribute("user_id");
			String user_pwd = request.getParameter("pwd");
			log.info("user_id :" + user_id);
			log.info("user_pwd :" + user_pwd);
			
			String HashEnc = EncryptUtil.enHashSHA256(user_pwd);

			UserDTO uDTO = new UserDTO();
			log.info("uDTO.set 시작");
			uDTO.setUser_id(user_id);
			uDTO.setUser_pwd(HashEnc);
			log.info("uDTO.set 종료");
			
			int res = userService.pwdChange(uDTO);
			
			String msg;
			String url = "/Setting/TheMypage.do";

			if (res > 0) {
				msg = "회원정보 수정에 성공했습니다.";
			} else {
				msg = "시스템 오류로 회원정보 수정에 실패했습니다. 자세한 사항은 고객센터에 문의해주세요.";
			}

			log.info("model.addAttribute 시작");
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			log.info("model.addAttribute 종료");

			log.info("/Mypage/passWordChangeProc 종료");

			return "/redirect";
		}
	 	@RequestMapping(value = "/Mypage/interestSettingProc")
		public String interestSettingProc(HttpSession session, HttpServletRequest request, Model model) {

			log.info("/Mypage/interestSettingProc 시작");
			
			String user_id =(String) session.getAttribute("user_id");
			String[] user_interest = request.getParameterValues("interest");
			log.info("user_id :" + user_id);
			log.info("user_interest :" + user_interest);
			
			String interests = "";
			
			if(user_interest != null) {
				interests = String.join(",", user_interest);
				log.info("interest : " + interests);
			} else {
				CmmUtil.nvl(interests);
			}
			
			UserDTO uDTO = new UserDTO();
			log.info("uDTO.set 시작");
			uDTO.setUser_id(user_id);
			uDTO.setUser_interest(interests);
			log.info("uDTO.set 종료");
			
			int res = userService.updateInterest(uDTO);
			
			String msg;
			String url = "/Setting/TheMypage.do";

			if (res > 0) {
				msg = "관심분야 수정에 성공했습니다.";
			} else {
				msg = "시스템 오류로 관심분야 수정에 실패했습니다. 고객센터에 문의해주세요.";
			}

			log.info("model.addAttribute 시작");
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			log.info("model.addAttribute 종료");

			log.info("/Mypage/interestSettingProc 종료");

			return "/redirect";
		}
	 	
	 	
		@RequestMapping(value = "The/findId")
		public String findId() {
			log.info("findId 시작"); 
			
			log.info("findId 종료");
			return "/The/findId";
		}
	 	
		@RequestMapping(value = "The/findIdProc", method = RequestMethod.POST)
		public String FindIdProc(HttpServletRequest request, Model model, HttpSession session) {
			log.info("FindIdProc 시작");

			
			String user_email = CmmUtil.nvl(request.getParameter("user_email"));
			log.info(user_email);
			
			UserDTO uDTO = new UserDTO();
			
			log.info("set 시작");
			uDTO.setUser_email(user_email);
			log.info("set 종료");
			
			UserDTO rDTO = new UserDTO();
			log.info("userService.findId 시작");
			rDTO = userService.findId(uDTO);
			log.info("userService.findId 시작");
			
			String msg;
			String url;
			
			log.info("session.setAtrribute && if 시작");
			if(rDTO == null) {
				msg = "존재하지 않는 이메일입니다! 이메일을 확인해주세요.";
				url = "/The/findId.do";
			} else {
				msg = "아이디 찾기를 성공했습니다! 확인을 눌러 비밀번호를 변경해주세요.";
				url = "findIdResult.do";
				session.setAttribute("user_id", rDTO.getUser_id());
			}
			log.info("session.setAtrribute && if 종료");

			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			log.info("FindIdProc 종료");
			return "/redirect";
		}
	 	
		@RequestMapping(value = "The/findIdResult")
		public String FindIdResult() {
			log.info("FindIdResult 시작");
			
			log.info("FindIdResult 종료");
			return "/The/findIdResult";
		}
	 	
}
