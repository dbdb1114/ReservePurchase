package reservpurchase.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reservpurchase.service.entity.MemberEntity;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity,Long> {
}
