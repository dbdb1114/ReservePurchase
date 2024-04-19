package reservpurchase.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/user")
public class UserController {

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
