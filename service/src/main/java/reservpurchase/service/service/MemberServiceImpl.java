package reservpurchase.service.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.MemberEntity;
import reservpurchase.service.repository.MemberRepository;
import reservpurchase.service.util.encrypt.MemberEncryptManager;

@Slf4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;
    private MemberEncryptManager memberEncoder;

    @Override
    public MemberDto join(MemberDto memberDto){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        memberEncoder.encodeAll(memberDto);

        MemberEntity mEntity = modelMapper.map(memberDto, MemberEntity.class);
        memberRepository.save(mEntity);

        MemberDto savedDto = modelMapper.map(mEntity, MemberDto.class);
        return savedDto;
    }

    public boolean isDuple(MemberDto memberDto){
        String username = memberRepository.findNameByEmailOrPhone(memberDto.getEmail(), memberDto.getPhone());
        return username != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
