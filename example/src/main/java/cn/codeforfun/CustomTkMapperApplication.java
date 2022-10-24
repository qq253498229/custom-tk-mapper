package cn.codeforfun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.codeforfun.**.mapper")
public class CustomTkMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomTkMapperApplication.class, args);
    }

}
