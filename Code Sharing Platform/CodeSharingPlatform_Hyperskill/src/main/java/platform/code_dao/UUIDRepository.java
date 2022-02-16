package platform.code_dao;

import org.springframework.data.repository.CrudRepository;
import platform.code_service.UUIDEntity;

import java.util.Optional;

public interface UUIDRepository extends CrudRepository<UUIDEntity,Long> {

    Optional<UUIDEntity> findByUuid(String uuid);
}
