package reservpurchase.service.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservpurchase.service.dto.MemberDto;
import reservpurchase.service.entity.MemberRedisEntity;
import reservpurchase.service.service.AuthService;
import reservpurchase.service.service.MemberService;
import reservpurchase.service.vo.request.EmailCertificationRequestVo;
import reservpurchase.service.vo.request.RequestMember;
import reservpurchase.service.vo.response.auth.EmailAuthStatus;
import reservpurchase.service.vo.response.auth.JoinStatus;
import reservpurchase.service.vo.response.auth.ResponseVo;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/member-service")
public class MemberController {

    MemberService memberService;
    AuthService authService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PutMapping("/join")
    public ResponseEntity<ResponseVo> join(@RequestBody @Valid RequestMember member) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        if (memberService.existsByEmail(member.getEmail())){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(JoinStatus.DE.responseVo);
        } else if(memberService.existsByPhone(member.getPhone())){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(JoinStatus.DP.responseVo);
        }

        MemberDto memberDto = mapper.map(member, MemberDto.class);
        String certificationNumber = authService.emailCertification(member.getEmail());

        if(certificationNumber.length() > 2){
            memberDto.setCertificationNumber(certificationNumber);
            memberService.tempJoin(memberDto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(EmailAuthStatus.SU.responseVo);
    }

    @PostMapping("/join/email-certify")
    public ResponseEntity<ResponseVo> joinWithEmailCertificate(@RequestBody EmailCertificationRequestVo emailVo){
        MemberDto memberDto = authService.certificate(emailVo);
        if(memberDto != null){
            memberService.join(memberDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JoinStatus.FA.responseVo);
        }
        return ResponseEntity.status(HttpStatus.OK).body(JoinStatus.SU.responseVo);
    }

}
