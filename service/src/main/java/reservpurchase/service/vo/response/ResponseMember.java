package reservpurchase.service.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import reservpurchase.service.entity.embeded.Address;

@Data
@JsonInclude(Include.NON_NULL) // RestAPI로 데이터를 반환할 때 NULL은 제외한다 .
public class ResponseMember {
    private String email;
    private String name;
    private String pwd;
    private String phone;
    private Address address;
}
