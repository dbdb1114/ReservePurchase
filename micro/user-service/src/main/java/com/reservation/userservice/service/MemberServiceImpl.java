package com.reservation.userservice.service;

import com.reservation.userservice.dto.MemberDto;
import com.reservation.userservice.entity.Member;
import com.reservation.userservice.entity.MemberRedis;
import com.reservation.userservice.repository.MemberRedisRepository;
import com.reservation.userservice.repository.MemberRepository;
import com.reservation.userservice.util.encrypt.EncryptManager;
import com.reservation.userservice.vo.response.ResponseMember;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    private MemberRedisRepository memberRedisRepository;
    private MemberRepository memberRepository;
    private ModelMapper modelMapper;

    @Override
    public MemberDto join(MemberDto memberDto){
        memberDto.encodeAll();

        Member member = modelMapper.map(memberDto, Member.class);
        Member save = memberRepository.save(member);

        MemberDto savedDto = modelMapper.map(save, MemberDto.class);

        return savedDto;
    }

    public MemberDto tempJoin(MemberDto memberDto){
        MemberRedis mEntity = modelMapper.map(memberDto, MemberRedis.class);
        memberRedisRepository.save(mEntity);
        MemberDto savedDto = modelMapper.map(mEntity, MemberDto.class);
        return savedDto;
    }

    @Override
    @Transactional
    public ResponseMember updateMember(MemberDto memberDto) {
        String email = EncryptManager.infoEncode(memberDto.getEmail());
        Member entity = memberRepository.findByEmail(email);

        if(entity != null){
            // 변경사항 영속화
            // 수정된 Dto를 암호화 하여 영속화 하여 저장시키고,
            memberDto.encodeAll();
            entity.update(memberDto);
            memberDto.decodeAll();
        }

        return modelMapper.map(memberDto, ResponseMember.class);
    }

    public Long findIdByEmail(String email){
        Long idByEmail = memberRepository.findIdByEmail(EncryptManager.infoEncode(email));
        return idByEmail;
    }

    public MemberDto findMemberByEmail(String email){
        Member member = memberRepository.findByEmail(EncryptManager.infoEncode(email));

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        MemberDto memberDto = modelMapper.map(member, MemberDto.class);
        memberDto.decodeAll();
        return memberDto;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(EncryptManager.infoEncode(email));
        if(member == null){
            throw new UsernameNotFoundException(email);
        }
        return modelMapper.map(member,MemberDto.class).getUser();
    }

    @Override
    public MemberDto getUserDetailsByEmail(String email) {
        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        MemberDto memberDto = modelMapper.map(member, MemberDto.class);
        return memberDto;
    }

    @Override
    public boolean existsByEmail(String email) {
        email = EncryptManager.infoEncode(email);
        return memberRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return memberRepository.existsByPhone(phone);
    }


}
