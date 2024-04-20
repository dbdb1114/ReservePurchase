package reservpurchase.service.service;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.MemberEntity;
import reservpurchase.service.entity.MemberRedisEntity;
import reservpurchase.service.repository.MemberRedisRepository;
import reservpurchase.service.repository.MemberRepository;
import reservpurchase.service.util.encrypt.MemberEncryptManager;

@Slf4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    private MemberRedisRepository memberRedisRepository;
    private MemberEncryptManager memberEncryptor;
    private MemberRepository memberRepository;


    @Override
    public MemberDto join(MemberDto memberDto){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        memberEncryptor.encodeAll(memberDto);

        MemberEntity memberEntity = modelMapper.map(memberDto, MemberEntity.class);
        MemberEntity save = memberRepository.save(memberEntity);

        MemberDto savedDto = modelMapper.map(save, MemberDto.class);

        return savedDto;
    }

    public MemberDto tempJoin(MemberDto memberDto){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MemberRedisEntity mEntity = modelMapper.map(memberDto, MemberRedisEntity.class);
        memberRedisRepository.save(mEntity);
        MemberDto savedDto = modelMapper.map(mEntity, MemberDto.class);
        return savedDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByEmail(memberEncryptor.infoEncode(username));
        if(memberEntity == null){
            throw new UsernameNotFoundException(username);
        }
        return new User(memberEntity.getEmail(), memberEntity.getPassword(),
                true,true,true,true,
                new ArrayList<>());
    }
    @Override
    public MemberDto getUserDetailsByEmail(String email) {
        MemberEntity userEntity = memberRepository.findByEmail(email);

        if(userEntity == null){
            throw new UsernameNotFoundException(email);
        }

        MemberDto memberDto = new ModelMapper().map(userEntity, MemberDto.class);
        return memberDto;
    }

    @Override
    public boolean existsByEmail(String email) {
        email = memberEncryptor.infoEncode(email);
        return memberRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return memberRepository.existsByPhone(phone);
    }


}
