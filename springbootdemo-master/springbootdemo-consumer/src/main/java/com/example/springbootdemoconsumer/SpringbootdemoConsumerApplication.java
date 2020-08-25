package com.example.springbootdemoconsumer;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients


@ComponentScan(value = "com.example.*")
public class SpringbootdemoConsumerApplication {
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(8080);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(7001);
        return connector;
    }
    //@Bean
    //public FilterRegistrationBean csrfFilter() {
       // FilterRegistrationBean registration = new FilterRegistrationBean();
       // registration.setFilter(new CsrfFilter(new HttpSessionCsrfTokenRepository()));
       // registration.addUrlPatterns("/*");
        //return registration;
   // }
    //@Bean
   // public WebMvcConfigurer webMvcConfigurer() {
        //return new WebMvcConfigurer() {
            //@Override
           // public void addCorsMappings(CorsRegistry registry) {
                // spring boot跨域设置
               // registry.addMapping("/**")
                        //设置允许跨域请求的域名
                       // .allowedOrigins("*")
                        //是否允许证书 不再默认开启（在跨域情况下使用cookie时开启，需要axios开启该对应的选项）
                        //.allowCredentials(true)
                        //设置允许的方法
                       // .allowedMethods("*")
                        //跨域允许时间
                       // .maxAge(3600);
            //}
        //};
    //}

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoConsumerApplication.class, args);
    }

}
