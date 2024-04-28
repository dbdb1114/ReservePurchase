package com.reservation.userservice.entity;

import com.reservation.userservice.dto.MemberDto;
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

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
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

    public void update(MemberDto member){
        this.password = isUpdate(this.password, member.getPassword());
        this.name = isUpdate(this.name, member.getName());
        this.address.setDetailAddress(isUpdate(this.address.getDetailAddress(), member.getAddress().getDetailAddress()));
        this.address.setCity(isUpdate(this.address.getCity(), member.getAddress().getCity()));
        this.phone = this.phone != member.getPhone() && member.getPhone() != null?
                member.getPhone() : this.phone;
    }

    private static String isUpdate(String a1, String b1){
        if(b1 != null && !a1.equals(b1)){
            return b1;
        }
        return a1;
    }

}
