package bihar.max.reg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegDAO {
    @Autowired
	RegInterface regInterface;
	public int insertData(RegDTO regDTO)
	{
        RegDTO t = regInterface.save(regDTO);
        if(t.getRid()>0)
        {
        	return t.getRid();
        }
		return 0;
		
	}
	
	
	public List<RegDTO> getRegDetail()
	{
		     List<RegDTO> list = (List<RegDTO>)regInterface.findAll();
			return list;
	}
	
	public List<RegDTO> viewDetail()
	{
		try {
			List<RegDTO> list=(List<RegDTO>)regInterface.findAll();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
