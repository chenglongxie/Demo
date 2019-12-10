package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

public class Kerberos {

    private final static Log log = LogFactory.getLog(Kerberos.class);

    public static void authentication(String krb5,String keyTab,String principal){
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
