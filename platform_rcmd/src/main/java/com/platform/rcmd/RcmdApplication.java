package com.platform.rcmd;


import com.ihrm.common.utils.IdWorker;
import com.ihrm.common.utils.JwtUtil;
import com.ihrm.common.utils.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

//1.配置springboot的包扫描
@SpringBootApplication(scanBasePackages = "com.platform")
//2.配置jpa注解的扫描
@EntityScan(value="com.ihrm.domain.rcmd")
public class RcmdApplication {

    /**
     * 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(RcmdApplication.class,args);


    }
    /**
     * 配置idworkr到本工程中
     * */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    @Bean
    public JwtUtil jwtUtils() {
        return new JwtUtil();
    }

}
