package bihar.max.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.DocumentException;

import bihar.max.LoginBean;
import bihar.max.master.BlockInterface;
import bihar.max.master.DistInterface;
import bihar.max.master.StateInterface;
import bihar.max.reg.RegDAO;
import bihar.max.reg.RegDTO;

@Controller
public class ViewController {
	@Autowired
	RegDAO regDAO;
	@Autowired
	StateInterface stateInterface;
	@Autowired
	DistInterface distInterface;
	@Autowired
	UserPDFExporter userPDFExporter;
	@Autowired
	RegDTO regDTO;
	@Autowired
	LoginBean loginBean;
	@Autowired
	BlockInterface blockInterface;
    @RequestMapping(value = "viewD",method = RequestMethod.GET)
	public ModelAndView viewDetail()
	{
    	List<RegDTO> list = regDAO.getRegDetail();
    	 List<RegDTO> l = new ArrayList<RegDTO>();
    	for(RegDTO regDTO:list)
    	{
    		
    		RegDTO td= new RegDTO(); 
    		td.setStCode(stateInterface.getStateBeanByStCode(Integer.parseInt(regDTO.getStCode())).getStName());
    		td.setDistCode(distInterface.getDistBeanByDistCode(regDTO.getDistCode()).getDistname());
    		td.setBlockCode(blockInterface.getBlockBeanByBlockCode(regDTO.getBlockCode()).getBlockName());
    		//td.setBlockCode(regDTO.getBlockCode());
    		td.setName("Mr "+regDTO.getName().substring(0,1).toUpperCase()+regDTO.getName().substring(1).toLowerCase());
    		td.setPhone(regDTO.getPhone());
    		td.setRid(regDTO.getRid());
    		l.add(td);
    	}
		return new ModelAndView("viewEmp","lst",l);
//		Iterator<RegDTO> it = list.iterator();
// 	   while(it.hasNext())
// 	{
// 		   RegDTO regDTO = it.next();
// 		RegDTO td= new RegDTO(); 
// 		td.setStCode(stateInterface.getStateBeanByStCode(Integer.parseInt(regDTO.getStCode())).getStName());
// 		td.setDistCode(distInterface.getDistBeanByDistCode(regDTO.getDistCode()).getDistname());
// 		td.setName("Mr "+regDTO.getName().substring(0,1).toUpperCase()+regDTO.getName().substring(1).toLowerCase());
// 		td.setPhone(regDTO.getPhone());
// 		td.setRid(regDTO.getRid());
// 		it.remove();
// 		list.add(td);
// 	}
	}
    
   @RequestMapping(value = "DownloadPDF", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws DocumentException, IOException
	{
		
		userPDFExporter.export(response);
		
	}
   
   @RequestMapping(value = "modify", method = RequestMethod.GET)
   public String modifyDetail(ModelMap m) 
	{
		m.addAttribute("m",regDTO);
		return "modify";
		
	}
   @ModelAttribute("rid")
   public List<RegDTO> getDe()
   {
	   List<RegDTO> list = regDAO.getRegDetail();
	return list;
   }
   
  
       @GetMapping("/login")
      
       public ModelAndView loginPage() {
    	   System.out.println("aa gya ");
   	  
           return new ModelAndView("login","lb",loginBean); // This assumes you have a login.jsp file for the login page.
}
//       public String loginPage(@ModelAttribute("lb")LoginBean loginBean,ModelMap m) 
//       {
//    	   m.addAttribute("k", loginBean);
//    	   return "login";
//   }
       @RequestMapping(value = "delete",method = RequestMethod.GET)
       public String deleteRow(ModelMap m)
       {
    	   m.addAttribute("r",regDTO);
   		    return "delete";
    	   
       }
       @ModelAttribute("rid")
       public List<RegDTO> getList()
       {
    	   List<RegDTO> list = regDAO.getRegDetail();
    	return list;
       }
       
}
