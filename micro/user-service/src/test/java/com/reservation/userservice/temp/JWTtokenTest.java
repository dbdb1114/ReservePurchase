package com.reservation.userservice.temp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class JWTtokenTest {

    String jwt =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsM0phR3htMTQ2WWlyc2V2bzBKNFpaTS9mMEVySVNibGlQakhaV0QzekxBPSIsImV4cCI6MTcxNDI2OTM4M30.NCRzaXQ91pQLkSrVEWgHLCnvXVgfqDw9ZaTzNKliZa0";

    @Autowired
    JWTutil jwTutil;
    @Test
    void 복호화조회(){
    }

}
