package reservpurchase.service.dto;

import reservpurchase.service.entity.embeded.Address;

public class MemberRedisDto {
    private String email; // 이메일
    private String name; // 이름
    private String password; // 비밀번호
    private String phone;
    private String certificationNumber;
    private Boolean emailAuthentication;
    private Address address;

}
