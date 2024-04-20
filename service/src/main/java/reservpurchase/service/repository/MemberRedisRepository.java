package reservpurchase.service.repository;

import org.springframework.data.repository.CrudRepository;
import reservpurchase.service.entity.MemberRedisEntity;

public interface MemberRedisRepository extends CrudRepository<MemberRedisEntity,String> {
}
