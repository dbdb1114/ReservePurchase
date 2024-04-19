package reservpurchase.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import reservpurchase.service.entity.embeded.Address;

@Data
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String name;
    private String password;
    private Address address;

    public MemberDto(String email, String name, String password, Address address) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
    }
}
