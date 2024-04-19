package reservpurchase.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reservpurchase.service.entity.embeded.Address;

@Data
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email; // 이메일
    @Column(nullable = false, length = 50)
    private String username; // 이름
    @Column(nullable = false, unique = true)
    private String password; // 비밀번호

    @Embedded
    private Address address;
}
