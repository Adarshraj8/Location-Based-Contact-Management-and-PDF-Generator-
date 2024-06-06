package bihar.max.master;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LocationDAO {
    @Autowired
	StateInterface stateInterface;
    @Autowired
    DistInterface distInterface;
    @Autowired
    BlockInterface blockInterface;
    
    public List<BlockBean> getBlockList(String distCode)
    {
    	return blockInterface.getBlockBeanListByDistCode(distCode);
    }
    
    public List<StateBean> getStateList()
    {
     List<StateBean> list = (List<StateBean>)stateInterface.findAll();
	return list;
      
    }
    
    public List<DistBean> getDistListByStCode(String stCode)
    {
    	//List<DistBean> list = (List<DistBean>)distInterface.findById(stCode);
         List<DistBean> list = distInterface.getDistBeanListByStCode(stCode.length()==1?"0"+stCode:stCode);
		return list;
    }
}
