package cn.crabapples.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;


/**
 * TODO springBoot主启动类
 *
 * @author Mr.He
 * 2019/8/5 22:53
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 *
 * EnableJpaAuditing 开启jpa审计
 * EnableNacosConfig 开启nacos配置管理 [可在配置文件中配置,且注解会覆盖配置文件] (springBoot)
 * NacosPropertySource 读取的nacos配置文件Id(springBoot)
 * EnableNacosDiscovery 开启nacos服务发现(springBoot)
 * EnableDiscoveryClient 开启服务注册发现功能(springCloud)
 * springBoot 使用application配置文件
 * springCloud 使用bootstrap配置文件
 */

@EnableJpaAuditing
@SpringBootApplication
@EnableDiscoveryClient
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        logger.info(">>>>>>>>SpringCloud服务启动成功 [nacos] [jpa]>>>>>>>>>");
    }
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
