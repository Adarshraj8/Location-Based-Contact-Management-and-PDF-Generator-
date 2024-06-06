package bihar.max;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bihar.max.reg.RegDTO;

@Controller
public class LoginController {
	@Autowired
	LoginBean loginBean;
	@Autowired
	RegDTO regDTO;
	//@RequestMapping(value ="ok",method =RequestMethod.GET)
	@GetMapping("/")
	public ModelAndView loginJSP()
	{
		
		
		return new ModelAndView("login","lb",loginBean);
		
	}
	@RequestMapping(value = "viewRid",method = RequestMethod.GET)
	public ModelAndView viewRid(ModelMap m)
	{
		//m.addAttribute("lb");
		return new ModelAndView("B","lb",regDTO);
	}
	
	
	@RequestMapping(value = "lgin",method = RequestMethod.POST)
	public String getUserDetail(@ModelAttribute("lb")LoginBean loginBean,HttpSession s,ModelMap m)
	{
		if(!loginBean.getUid().equalsIgnoreCase(loginBean.getPass()))
		{
			
			return "S";
		}
		else {
			return "E";
		}
		
	}
}
