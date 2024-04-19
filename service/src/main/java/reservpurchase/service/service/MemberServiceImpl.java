package reservpurchase.service.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader.Strategy;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.MemberEntity;
import reservpurchase.service.repository.MemberRepository;

@Slf4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;



    @Override
    public MemberDto join(MemberDto memberDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        MemberEntity mEntity = modelMapper.map(memberDto, MemberEntity.class);
        memberRepository.save(mEntity);
        MemberDto savedDto = modelMapper.map(mEntity, MemberDto.class);
        return savedDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
