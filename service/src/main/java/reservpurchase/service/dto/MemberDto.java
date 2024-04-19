package reservpurchase.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import reservpurchase.service.entity.embeded.Address;

@Data
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Address address;

    public MemberDto(String email, String username, String password, Address address) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
    }
}
