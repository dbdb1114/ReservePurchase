package reservpurchase.service.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.service.MemberService;
import reservpurchase.service.vo.request.RequestMember;
import reservpurchase.service.vo.response.ResponseMember;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/member-service")
public class MemberController {

    MemberService memberService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PutMapping("/join")
    public ResponseEntity<ResponseMember> join(@RequestBody RequestMember member)
            throws Exception {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        MemberDto memberDto = mapper.map(member, MemberDto.class);

        if (memberService.isDuple(memberDto)){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(null);
        }

        memberService.join(memberDto);

        ResponseMember responseMember = mapper.map(memberDto, ResponseMember.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMember);
    }
}
