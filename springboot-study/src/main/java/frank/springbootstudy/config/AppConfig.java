package frank.springbootstudy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import frank.springbootstudy.config.interceptor.LoginInterceptor;
import frank.springbootstudy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig implements WebMvcConfigurer {//web框架 执行初始化工作时会调用接口方法

    @Autowired
    private ObjectMapper objectMapper;
    //静态资源也会被拦截
    /*
    * /*一级匹配
    * /** 多级匹配
    *
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(objectMapper))
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login");
    }

    @Bean
    public Map<Integer,Integer> test1() {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,100);
        map.put(2,200);
        return map;
    }

    @Bean
    public Map<Integer,Integer> test2() {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(3,300);
        map.put(4,400);
        return map;
    }

    @Bean
    public User user1() {
        User u = new User();
        u.setUsername("aaa");
        u.setPassword("bbb");
        return u;
    }
    @Bean
    public User user2() {
        User u = new User();
        u.setUsername("ccc");
        u.setPassword("ddd");
        return u;
    }

}
