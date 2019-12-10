package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class KuduImpala {

    public static void main(String[] args) throws Exception {
        //认证
        Kerberos.authentication("krb5.conf", "impala.haproxy.keytab", "impala/indata-172-16-3-35.indata.com@INDATA.COM");
        //加载驱动程序
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        //获得数据库连接
        Connection conn = DriverManager.getConnection("jdbc:hive2://172.16.3.36:21050/default;principal=impala/indata-172-16-3-36.indata.com@INDATA.COM");
        //连接查询
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select 1");
        //连接成功rs.next()返回true
        while (rs.next()) {
            System.out.println("连接成功");
        }
    }
}
