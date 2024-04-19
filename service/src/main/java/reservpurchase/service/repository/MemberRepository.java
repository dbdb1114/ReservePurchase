package reservpurchase.service.repository;

import org.springframework.data.repository.CrudRepository;
import reservpurchase.service.entity.MemberEntity;

public interface MemberRepository extends CrudRepository<MemberEntity,Long> {
    String findNameByEmailOrPhone(String email, String phone);

}
