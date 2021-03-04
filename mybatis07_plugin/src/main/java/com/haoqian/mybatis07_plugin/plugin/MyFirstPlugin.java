package com.haoqian.mybatis07_plugin.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * @Intercepts 完成插件签名：
 * 告诉MyBatis当前插件用来拦截哪个对象的哪个方法。在目标方法执行之前做一些事情
 * <p>
 * type   ：指定要拦截四大对象的哪一个
 * method ：指定要拦截对象的哪个方法 （parameterize是设置参数的方法）
 * args   ：指定要拦截方法的参数列表（因为方法可能有重载）
 */
@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "parameterize",
                args = java.sql.Statement.class)
})
public class MyFirstPlugin implements Interceptor {

    /**
     * intercept：拦截：
     * 拦截目标对象的目标方法的执行
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin.intercept()--->" + invocation.getMethod());

        //动态的改变一下sql运行的参数(请求1号员工信息，拦截器改为2号员工)
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象：" + target);
        //从哪里拿?：StatementHandler==>ParameterHandler===>parameterObject
        //拿到target的元数据
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数是：" + value);
        //修改完sql语句要用的参数
        metaObject.setValue("parameterHandler.parameterObject", 2);

        // 放行让目标方法执行
        Object proceed = invocation.proceed();
        // 返回执行后的返回值
        return proceed;
    }

    /**
     * plugin：包装目标对象的
     * 包装：为目标对象创建一个代理对象
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstPlugin.plugin()--->mybatis将要包装的对象" + target);
        // 借助Plugin的wrap方法,使用当前Interceptor来包装目标对象,生成动态代理对象
        Object wrap = Plugin.wrap(target, this);
        // 返回为利用当前target创建的动态代理对象供mybatis使用
        return wrap;
    }

    /**
     * setProperties：
     * 将插件注册时的property属性设置进来
     */
    @Override
    public void setProperties(Properties properties) {
        // properties中可以获取插件注册时传入的属性
        System.out.println("MyFirstPlugin.setProperties()--->插件配置的信息：" + properties);
    }
}
