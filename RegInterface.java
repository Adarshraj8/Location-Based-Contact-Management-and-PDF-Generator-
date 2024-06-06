package bihar.max.reg;

import org.springframework.data.repository.CrudRepository;

public interface RegInterface extends CrudRepository<RegDTO, Integer> {

	RegDTO getRegDTOByRid(int rid);

	RegDTO deleteById(int rid);
	
	RegDTO getRegDTODetailByRid(int rid);
}
