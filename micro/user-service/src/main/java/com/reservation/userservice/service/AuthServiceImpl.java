package com.reservation.userservice.service;


import static com.reservation.userservice.util.email.EmailProvider.getCertificationNumber;

import com.reservation.userservice.dto.MemberDto;
import com.reservation.userservice.entity.MemberRedis;
import com.reservation.userservice.repository.MemberRedisRepository;
import com.reservation.userservice.util.email.EmailProvider;
import com.reservation.userservice.vo.request.auth.EmailCertificationRequestVo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    private EmailProvider emailProvider;
    private MemberRedisRepository memberRedisRepository;
    private ModelMapper modelMapper;

    @Override
    public String emailCertification(String email) {
        String certificationNumber = getCertificationNumber();

        try {
            emailProvider.sendCertificationMail(email, certificationNumber);
        } catch (Exception e){
            e.printStackTrace();
            return "0";
        }

        return certificationNumber;
    }

    @Override
    public MemberDto certificate(EmailCertificationRequestVo emailCertificationRequestVo) {
        MemberRedis memberRedis = memberRedisRepository
                .findById(emailCertificationRequestVo.getEmail()).get();
        if(memberRedis.certification(emailCertificationRequestVo.getNumber())){
            return modelMapper.map(memberRedis, MemberDto.class);
        }
        return null;
    }


}
