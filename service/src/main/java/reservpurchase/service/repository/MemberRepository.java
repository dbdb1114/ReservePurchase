package reservpurchase.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reservpurchase.service.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    String findNameByEmailOrPhone(String email, String phone);
    MemberEntity findByEmail(String email);

    @Query("select m.id from MemberEntity m where m.email = :email")
    Long findIdByEmail(@Param("email") String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

}
