package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KuduKerberos implements CommandLineRunner {

    private final static Log log = LogFactory.getLog(KuduKerberos.class);

    @Value("${kerb5}")
    private String krb5;
    @Value("${keyTab}")
    private String keyTab;
    @Value("${principal}")
    private String principal;

    @Override
    public void run(String... args) {
        krb5 = Thread.currentThread().getContextClassLoader().getResource(krb5).getPath();
        keyTab = Thread.currentThread().getContextClassLoader().getResource(keyTab).getPath();
        Configuration configuration = new Configuration();
        configuration.set("hadoop.security.authentication", "Kerberos");
        System.setProperty("java.security.krb5.conf", krb5);
        UserGroupInformation.setConfiguration(configuration);
        try {
            UserGroupInformation.loginUserFromKeytab(principal, keyTab);
            System.out.println(UserGroupInformation.getLoginUser());
            log.info(UserGroupInformation.getLoginUser());
            log.info("Kerberos认证成功");

        } catch (Exception e) {
            log.error("Kerberos认证失败", e);
        }
    }
}
