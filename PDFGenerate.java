package bihar.max.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bihar.max.master.DistInterface;
import bihar.max.master.StateInterface;
import bihar.max.reg.RegBean;
import bihar.max.reg.RegDAO;
import bihar.max.reg.RegDTO;
import bihar.max.reg.RegInterface;

@Service
public class PDFGenerate {
	@Autowired
	RegBean regBean;
	@Autowired
	RegInterface registInterface;
	@Autowired
	RegDTO regDTO;
	@Autowired
	RegDAO registDao;
	@Autowired
	StateInterface stateInterface;
	@Autowired
	DistInterface distInterface;
	@Autowired
	UserPDFExporter userPDFExporter;
	@Autowired
	PDFGenerate pDFGenerate;
	
	
	public List<RegDTO> setDataWithLactionName()
	{
		List<RegDTO> list = registDao.viewDetail();
		RegDTO c=null;
		
		List<RegDTO> l=new ArrayList<RegDTO>();
		  for(RegDTO p:list)
		  {
			 c = new  RegDTO();
			c.setRid(p.getRid());
			c.setName(p.getName());
			
			
			//StateEntity sb = stateInterface.getStateEntityStateNameByStCode(Integer.parseInt(p.getStCode()));
			//DistrictEntity db = distInterface.getDistrictEntityDistNameByDistCode(p.getDistCode());
			c.setStCode(stateInterface.getStateBeanByStCode(Integer.parseInt(p.getStCode())).getStName());
    		c.setDistCode(distInterface.getDistBeanByDistCode(p.getDistCode()).getDistname());
    		c.setPhone(p.getPhone());
    		//c.setStName(sb.getStName());
			//c.setDistName(db.getDistName());
			l.add(c);
		  }
		return l;
	}
	

}
