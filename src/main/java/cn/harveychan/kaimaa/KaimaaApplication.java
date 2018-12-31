package cn.harveychan.kaimaa;

import cn.harveychan.kaimaa.util.SnowflakeIdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KaimaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaimaaApplication.class, args);
    }

    @Bean
    public SnowflakeIdWorker idWorker() {
        return new SnowflakeIdWorker(1, 1);
    }
}
