package platform.code_dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.code_service.Code;

@Repository
public interface CodeRepository extends CrudRepository<Code, Integer> {
}
