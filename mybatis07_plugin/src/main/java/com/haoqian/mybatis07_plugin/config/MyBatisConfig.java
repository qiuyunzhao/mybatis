package com.haoqian.mybatis07_plugin.config;

import com.haoqian.mybatis07_plugin.plugin.MyFirstPlugin;
import com.haoqian.mybatis07_plugin.plugin.MySecondPlugin;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfig {


    @Bean
    public MyFirstPlugin myFirstPlugin() {
        MyFirstPlugin myFirstPlugin = new MyFirstPlugin();
        // 设置参数，可以在配置文件中配置，这里直接写死便于测试
        Properties properties = new Properties();
        // 这里设置慢查询阈值为1毫秒，便于测试
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");
        myFirstPlugin.setProperties(properties);
        return myFirstPlugin;
    }

    @Bean
    public MySecondPlugin mySecondPlugin() {
        MySecondPlugin mySecondPlugin = new MySecondPlugin();
        Properties properties = new Properties();
        mySecondPlugin.setProperties(properties);
        return mySecondPlugin;
    }


    /**
     * 数据库厂商标识
     */
    @Bean
    public DatabaseIdProvider databaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties p = new Properties();
        p.setProperty("Oracle", "oracle");
        p.setProperty("MySQL", "mysql");
        p.setProperty("PostgreSQL", "postgresql");
        p.setProperty("DB2", "db2");
        p.setProperty("SQL Server", "sqlserver");
        databaseIdProvider.setProperties(p);
        return databaseIdProvider;
    }
}
