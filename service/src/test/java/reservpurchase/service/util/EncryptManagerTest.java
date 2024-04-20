package reservpurchase.service.util;

import java.security.InvalidAlgorithmParameterException;
import org.junit.jupiter.api.Test;
class EncryptManagerTest {

    @Test
    void Exception처리() {

        try{
            throw new InvalidAlgorithmParameterException();
        }catch (Exception e){
            if(e instanceof InvalidAlgorithmParameterException){
                System.out.println("탐지 가능 ");
            } else {
                System.out.println("탐지 불가능");
            }
        }


    }

}
