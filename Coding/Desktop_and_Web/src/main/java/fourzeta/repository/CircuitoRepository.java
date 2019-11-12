package fourzeta.repository;

import org.springframework.data.repository.CrudRepository;

import fourzeta.models.Circuito;



public interface CircuitoRepository extends CrudRepository<Circuito, String>{
	Circuito findById(int id);
}
