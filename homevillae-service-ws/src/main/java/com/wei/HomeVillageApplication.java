package com.wei;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author a_pen
 */
@SpringBootApplication
@MapperScan("com.wei.mapper")
public class HomeVillageApplication {

private static final Logger log  = LoggerFactory.getLogger(HomeVillageApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext run = SpringApplication.run(HomeVillageApplication.class, args);
        Environment env = run.getEnvironment();
        log.info("\n---------------------------------------------------------------------\t\n"
                        +"application is {}\t\t\n"
                        +"local:   \t\thttp://localhost:{}\t\t\n"
                        +"external:\t\thttp://{}:{}\t\t\n"
                        +"-----------------------------------------------------------------------\t\n",
                env.getProperty("spring.application.name"),env.getProperty("server.port"), InetAddress.getLocalHost().getHostAddress(),env.getProperty("server.port"));
    }


}