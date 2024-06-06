package bihar.max.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bihar.max.reg.RegDTO;
import bihar.max.reg.RegInterface;
//http://localhost:9000/dist?stCode=22
@RestController
public class DistController {
	@Autowired
	LocationDAO locationDAO;
	@Autowired
	RegInterface regInterface;
    @RequestMapping(value = "dist",method = RequestMethod.GET)
	public List<DistBean> getDistList(@RequestParam("stCode")String stCode)
	{
    	   List<DistBean> list = locationDAO.getDistListByStCode(stCode);
    	  
		return list;
	}
	
    @RequestMapping(value = "block",method = RequestMethod.GET)
   	public List<BlockBean> getBlockList(@RequestParam("distCode")String distCode)
   	{
       	  List<BlockBean> list = locationDAO.getBlockList(distCode);
       	  
   		return list;
   	}
    
    @RequestMapping(value = "RidByDetail",method = RequestMethod.GET)
   	public RegDTO getRidByDetail(@RequestParam("rid")String rid)
   	{
    	RegDTO b = regInterface.getRegDTODetailByRid(Integer.parseInt(rid));
       	  
   		return b;
   	}
}
