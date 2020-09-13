package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.service.INewsService;

@Controller
public class NewsController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "NewsService") 
	private INewsService newsService;
	
	@RequestMapping(value="/Today/clawlingNews")
	public String getMovieInfoFromWEB(HttpServletRequest request, HttpServletResponse response, ModelMap model) 
	throws Exception {
		
		log.info(this.getClass().getName() + ".getMovieInfoFromWEB Start!");
		
		int res = newsService.getNewsInfoFromWEB();
		
		model.addAttribute("res", String.valueOf(res));
		
		log.info(this.getClass().getName() + ".getMovieInfoFromWEB End!");
		
		return "/Today/clawlingNews";
		
	}
}