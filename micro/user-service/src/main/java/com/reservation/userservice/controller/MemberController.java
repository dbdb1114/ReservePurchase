package com.reservation.userservice.controller;

import com.reservation.userservice.dto.MemberDto;
import com.reservation.userservice.service.AuthService;
import com.reservation.userservice.service.MemberService;
import com.reservation.userservice.temp.JWTutil;
import com.reservation.userservice.vo.request.auth.EmailCertificationRequestVo;
import com.reservation.userservice.vo.request.RequestMember;
import com.reservation.userservice.vo.response.ResponseMember;
import com.reservation.userservice.vo.response.status.EmailAuthStatus;
import com.reservation.userservice.vo.response.status.JoinStatus;
import com.reservation.userservice.vo.response.status.ResponseVo;
import com.reservation.userservice.vo.response.status.ResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class MemberController {

    MemberService memberService;
    AuthService authService;

    ModelMapper modelMapper;
    JWTutil jwtUil;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PutMapping("/join")
    public ResponseEntity<ResponseVo> join(@RequestBody @Valid RequestMember member) {

        if (memberService.existsByEmail(member.getEmail())){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new ResponseVo(JoinStatus.DE));
        } else if(memberService.existsByPhone(member.getPhone())){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new ResponseVo(JoinStatus.DP));
        }

        MemberDto memberDto = modelMapper.map(member, MemberDto.class);
        String certificationNumber = authService.emailCertification(member.getEmail());

        if(certificationNumber.length() > 2){
            memberDto.setCertificationNumber(certificationNumber);
            memberService.tempJoin(memberDto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo(EmailAuthStatus.SU));
    }

    @PostMapping("/join/email-certify")
    public ResponseEntity<ResponseVo> joinWithEmailCertificate(@RequestBody EmailCertificationRequestVo emailVo){
        MemberDto memberDto = authService.certificate(emailVo);
        if(memberDto != null){
            memberService.join(memberDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo(JoinStatus.FA));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo(JoinStatus.SU));
    }

    @PostMapping("/user-info")
    public ResponseEntity<ResponseMember> user(HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ","");
        String email = jwtUil.getEmail(token);
        MemberDto userInfo = memberService.findMemberByEmail(email);
        ResponseMember responseMember = modelMapper.map(userInfo, ResponseMember.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseMember);
    }

    @PostMapping("/member-id")
    public ResponseEntity<?> loginUserId(HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ","");
        String email = jwtUil.getEmail(token);
        Long memberId = memberService.findIdByEmail(email);

        if(memberId != null){
            return ResponseEntity.status(HttpStatus.OK).body(memberId);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseVo(ResponseStatus.FA));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseMember> updateMember(@RequestBody RequestMember requestMember){
        MemberDto memberDto = modelMapper.map(requestMember, MemberDto.class);
        ResponseMember updateMember = memberService.updateMember(memberDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateMember);
    }

}
