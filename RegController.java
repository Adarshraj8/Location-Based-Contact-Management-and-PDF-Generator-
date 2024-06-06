package bihar.max.reg;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bihar.max.master.BlockInterface;
import bihar.max.master.DistInterface;
import bihar.max.master.LocationDAO;
import bihar.max.master.StateBean;
import bihar.max.master.StateInterface;

@Controller
public class RegController {
	 @Autowired
	 RegBean regBean;
	 @Autowired
	 RegDAO rgDAO;
	 @Autowired
	 LocationDAO locationDAO;
	 @Autowired
	 RegDTO regDTO;
	 @Autowired
	 StateInterface stateInterface;
	 @Autowired
	 DistInterface distInterface;
	 @Autowired
	 BlockInterface blockInterface;
     @RequestMapping(value = "reg",method = RequestMethod.GET)
	public ModelAndView regJSP()
	{
		return new  ModelAndView("reg","r",regBean);
		
	}
     @ModelAttribute("stList")
     public List<StateBean> getState()
     {
    	   List<StateBean> list = locationDAO.getStateList();
		return list;
     }
     
     @RequestMapping(value = "regSave",method = RequestMethod.POST)
     public String getEmpDet(@ModelAttribute("r")RegBean regBean,ModelMap m)
     {
    	  BeanUtils.copyProperties(regBean, regDTO);
    	   int t = rgDAO.insertData(regDTO);
    	   if(t>0)
    	 {
    		  String stcode = regDTO.getStCode();
    		   regDTO.setStCode(stateInterface.getStateBeanByStCode(Integer.parseInt(stcode)).getStName());
    		    regDTO.setDistCode(distInterface.getDistBeanByDistCode(regDTO.getDistCode()).getDistname());
    		    regDTO.setName("Mr "+regDTO.getName().substring(0,1).toUpperCase()+regDTO.getName().substring(1).toLowerCase());
    		   m.addAttribute("b",regDTO);
    		   regDTO.setRid(t);
    		   regDTO.setBlockCode(blockInterface.getBlockBeanByBlockCode(regDTO.getBlockCode()).getBlockName());
    		 return "View";
    	 }
    	 else {
    		 return "F";
    	 }
    	 
     }
}
