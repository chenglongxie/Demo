package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KuduKerberos implements CommandLineRunner {

    @Value("${kerb5}")
    private String krb5;
    @Value("${keyTab}")
    private String keyTab;
    @Value("${principal}")
    private String principal;

    @Override
    public void run(String... args) {
        Kerberos.authentication(krb5, keyTab, principal);
    }
}
