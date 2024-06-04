package biblioteca.security.spring.sis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import biblioteca.security.spring.sis.services.models.coreFunctions.CodeVerificationCore;
import biblioteca.security.spring.sis.services.models.coreFunctions.UserCore;

@Configuration
public class Config {

    @Bean
    public UserCore userValidations(){
        return new UserCore();
    }

    @Bean
    public CodeVerificationCore codVerification(){
        return new CodeVerificationCore();
    }



}
