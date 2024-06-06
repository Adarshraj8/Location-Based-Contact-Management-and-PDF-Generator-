package bihar.max;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bihar.max.master.DistInterface;
import bihar.max.master.StateInterface;
import bihar.max.reg.RegDAO;
import bihar.max.reg.RegDTO;
import bihar.max.reg.RegInterface;
import bihar.max.view.CommanBean;

@Controller
public class ModifyController {
	@Autowired
	RegDTO regDTO;
	@Autowired
	RegInterface regInterface;
	@Autowired
	StateInterface stateInterface;
	@Autowired
	DistInterface distInterface;
	@Autowired
	CommanBean cb;
	@Autowired
	RegDAO regDAO;
	@RequestMapping(value = "viewModifyDetail",method = RequestMethod.POST )
	public String viewModify(@ModelAttribute("m")RegDTO regDTO,ModelMap m)
	{
		  
		System.out.println("regDTO "+regDTO.getRid());
	    RegDTO rb = regInterface.getRegDTOByRid(regDTO.getRid());
	    cb.setDistCode(rb.getDistCode());
	    cb.setStCode(rb.getStCode());
	    cb.setName(rb.getName());
	    cb.setPhone(rb.getPhone());
	    cb.setStName(stateInterface.getStateBeanByStCode(Integer.parseInt(rb.getStCode())).getStName());
	    cb.setDistName(distInterface.getDistBeanByDistCode(rb.getDistCode()).getDistname());
	    cb.setRid(regDTO.getRid());
	    m.addAttribute("rbean",cb);
		return "viewModify";
		
	}
	
	@RequestMapping(value = "saveUpdate",method = RequestMethod.POST)
	public String getUpdateData(@ModelAttribute("m")CommanBean cb)
	{
		 BeanUtils.copyProperties(cb, regDTO);
		 int t = regDAO.insertData(regDTO);
		 if(t>0)
		 {
			 //UUID userId = UUID.randomUUID();
			 //regDTO.setRid(userId);
			 
			 System.out.println("Successfull ");
		 }
		 else {
			 System.out.println("Error ");
		 }
		System.out.println("r "+cb.getStCode()+" "+cb.getDistCode()+" "+cb.getName()+" "+cb.getPhone()+" "+cb.getRid());
		return "S";
		
	}
	
	@RequestMapping(value = "deleteDetail",method = RequestMethod.POST )
	public String deleteById(@ModelAttribute("r")RegDTO regDTO)
	{
		  
		System.out.println("regDTO "+regDTO.getRid());
		regInterface.deleteById(regDTO.getRid());
		//regInterface.deleteById(regDTO.getRid());
		
		return "S";
		
	}
   
}
