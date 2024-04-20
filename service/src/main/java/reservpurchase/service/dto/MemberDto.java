package reservpurchase.service.dto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
    private String phone;
    private Address address;
    private String certificationNumber;

    public MemberDto(String email, String name, String password, String phone, Address address) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
}
