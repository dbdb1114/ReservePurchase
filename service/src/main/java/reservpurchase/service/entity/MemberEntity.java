package reservpurchase.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import reservpurchase.service.entity.embeded.Address;

@Entity
@Builder
@ToString
@Table(name = "MEMBER")
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; // 이메일
    @Column(nullable = false)
    private String name; // 이름
    @Column(nullable = false, unique = true)
    private String password; // 비밀번호
    @Column(nullable = false, unique = true)
    private String phone;

    @Embedded
    private Address address;

    public String getEmail() {
        return this.email;
    }
}
