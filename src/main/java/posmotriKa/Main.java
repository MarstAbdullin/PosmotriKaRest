package posmotriKa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.userdetails.User;
import posmotriKa.config.ApplicationContextConfig;
import posmotriKa.dto.RegisterDto;
import posmotriKa.models.Category;
import posmotriKa.models.Video;
import posmotriKa.repositories.CategoryRepository;
import posmotriKa.repositories.UserRepository;
import posmotriKa.repositories.VideoRepository;
import posmotriKa.services.RegisterService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);

        RegisterService registerService = context.getBean(RegisterService.class);
UserRepository userRepository = context.getBean(UserRepository.class);

        //registerService.register(RegisterDto.builder().email("dsfgdfg").password("sdlfkjglsdk").username("dfkslgsdl").build());

        userRepository.findByName("dsfgdfg");
    }
}
