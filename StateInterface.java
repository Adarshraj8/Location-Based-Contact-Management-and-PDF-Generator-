package bihar.max.master;

import org.springframework.data.repository.CrudRepository;

public interface StateInterface extends CrudRepository<StateBean, Integer>{

	StateBean getStateBeanByStCode(int stCode);
}
