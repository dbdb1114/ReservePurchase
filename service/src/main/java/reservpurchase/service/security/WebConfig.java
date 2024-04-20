package reservpurchase.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import reservpurchase.service.service.MemberService;

@Configuration
public class WebConfig {

    private PasswordEncoder passwordEncoder;
    private MemberService memberService;
    private Environment env;

    @Autowired
    public WebConfig(Environment env, MemberService memberService, PasswordEncoder passwordEncoder) {
        this.env = env;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));

        http.csrf(config->config.disable());
        http.authorizeHttpRequests(custom->custom.requestMatchers("/member-service/join").permitAll()
                .requestMatchers("/member-service/temp-join").permitAll());

        http.addFilter(getAuthenticationFilter(authenticationManager));
        http.headers(custom->custom.disable());

        return http.build();
    }

    private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, memberService, env);
        return authenticationFilter;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Override 해주는 용도
    @Bean
    AuthenticationProvider AuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(memberService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
