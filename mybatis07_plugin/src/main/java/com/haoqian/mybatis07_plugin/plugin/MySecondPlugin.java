package com.haoqian.mybatis07_plugin.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "parameterize",
                args = java.sql.Statement.class)
})
public class MySecondPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MySecondPlugin.intercept()--->" + invocation.getMethod());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MySecondPlugin.plugin()--->mybatis将要包装的对象" + target);
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("MySecondPlugin.setProperties()--->插件配置的信息：" + properties);
    }
}
