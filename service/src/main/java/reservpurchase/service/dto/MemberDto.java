package reservpurchase.service.dto;

import java.util.ArrayList;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;
import reservpurchase.service.entity.embeded.Address;
import reservpurchase.service.util.encrypt.EncryptManager;

@EqualsAndHashCode
@NoArgsConstructor
public class MemberDto{

    private Long id;
    private String email;
    private String name;
    private String password;
    private String phone;
    private Address address;
    private String certificationNumber;

    public User getUser(){
        return new User(this.email, this.password,
                true,true,true,true,
                new ArrayList<>());
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public MemberDto(String email, String name, String password, String phone, Address address) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public void encodePassword() {
        this.password = EncryptManager.passwordEncoder.encode(password);
    }
    public void encodeEmail(){
        this.email = EncryptManager.infoEncode(this.email);
    }
    public void decodeEmail() {
        this.email = EncryptManager.infoDecode(this.email);
    }
    public void encodeName() {
        this.name = EncryptManager.infoEncode(this.name);
    }
    public void decodeName() {
        this.name = EncryptManager.infoDecode(this.name);
    }
    public void decodeAddress() {
        if(address != null){
            address.setCity(EncryptManager.infoDecode(address.getCity()));
            address.setDetailAddress(EncryptManager.infoDecode(address.getDetailAddress()));
        }
    }
    public void encodeAddress() {
        if(address != null){
            address.setCity(EncryptManager.infoEncode(address.getCity()));
            address.setDetailAddress(EncryptManager.infoEncode(address.getDetailAddress()));
        }
    }
    public void encodeAll(){
        this.email = EncryptManager.infoEncode(this.email);
        this.name = EncryptManager.infoEncode(this.name);
        this.password = EncryptManager.passwordEncoder.encode(this.password);

        Address address = this.address;

        if(address != null){
            address.setCity(EncryptManager.infoEncode(address.getCity()));
            address.setDetailAddress(EncryptManager.infoEncode(address.getDetailAddress()));
        }
    }

    private void decodeAll(){
        this.email = EncryptManager.infoDecode(this.email);
        this.name = EncryptManager.infoDecode(this.name);

        Address address = this.address;

        if(address != null){
            address.setCity(EncryptManager.infoDecode(address.getCity()));
            address.setDetailAddress(EncryptManager.infoDecode(address.getDetailAddress()));
        }
    }

    public void setCertificationNumber(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }
}
