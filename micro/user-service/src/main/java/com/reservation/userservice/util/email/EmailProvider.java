package com.reservation.userservice.util.email;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailProvider {

    private final JavaMailSender javaMailSender;

    private final String SUBJECT = "[예약 구매] 인증 메일입니다.";

    public boolean sendCertificationMail(String email, String certificationNumber){

        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email);
            messageHelper.setSubject(SUBJECT);
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (Exception exception){
            exception.printStackTrace();
            return false;
        }

        return true;
    }
    private String getCertificationMessage(String certificationNumber) {
        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'>[임대주택 가격 서비스] 인증메일 </h1>";
        certificationMessage += "<h3 style='text-align: center;'>인증 코드 : <strong style='font-size: 32px; letter-spacing: 8px'>"
                +certificationNumber + "</strong></h3>";
        return certificationMessage;
    }


    public static String getCertificationNumber(){

        String certificationNumber = "";

        for (int i = 0; i < 4; i++) certificationNumber += (int) (Math.random() * 10);

        return certificationNumber;
    }
}
