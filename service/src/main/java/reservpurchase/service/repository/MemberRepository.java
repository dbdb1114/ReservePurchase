package reservpurchase.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reservpurchase.service.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    String findNameByEmailOrPhone(String email, String phone);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    MemberEntity findByEmail(String email);
}
