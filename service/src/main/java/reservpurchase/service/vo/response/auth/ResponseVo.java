package reservpurchase.service.vo.response.auth;

import lombok.Data;
import reservpurchase.service.vo.response.auth.JoinStatus;

@Data
public class ResponseVo {

    public ResponseVo() {}

    String responseCode;
    String responseMessage;

}
