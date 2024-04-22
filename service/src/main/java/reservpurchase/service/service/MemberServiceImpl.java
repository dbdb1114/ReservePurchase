package reservpurchase.service.service;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.MemberEntity;
import reservpurchase.service.entity.MemberRedisEntity;
import reservpurchase.service.repository.MemberRedisRepository;
import reservpurchase.service.repository.MemberRepository;
import reservpurchase.service.util.encrypt.EncryptManager;
import reservpurchase.service.vo.response.ResponseMember;

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

        MemberEntity memberEntity = modelMapper.map(memberDto, MemberEntity.class);
        MemberEntity save = memberRepository.save(memberEntity);

        MemberDto savedDto = modelMapper.map(save, MemberDto.class);

        return savedDto;
    }

    public MemberDto tempJoin(MemberDto memberDto){
        MemberRedisEntity mEntity = modelMapper.map(memberDto, MemberRedisEntity.class);
        memberRedisRepository.save(mEntity);
        MemberDto savedDto = modelMapper.map(mEntity, MemberDto.class);
        return savedDto;
    }

    @Override
    @Transactional
    public ResponseMember updateMember(MemberDto memberDto) {
        String email = EncryptManager.infoEncode(memberDto.getEmail());
        MemberEntity entity = memberRepository.findByEmail(email);

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
        Long idByEmail = memberRepository.findIdByEmail(email);
        return idByEmail;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByEmail(EncryptManager.infoEncode(email));
        if(memberEntity == null){
            throw new UsernameNotFoundException(email);
        }
        return modelMapper.map(memberEntity,MemberDto.class).getUser();
    }

    @Override
    public MemberDto getUserDetailsByEmail(String email) {
        MemberEntity memberEntity = memberRepository.findByEmail(email);

        if(memberEntity == null){
            throw new UsernameNotFoundException(email);
        }

        MemberDto memberDto = new ModelMapper().map(memberEntity, MemberDto.class);
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
