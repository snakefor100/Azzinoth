package com.junlong.azzinoth.core.advisor;

/**
 * 通知监听器
 * Created by niujunlong on 2017/8/30.
 */
public interface AdviceListener {
    /**
     * 监听器创建<br/>
     * 监听器被注册时触发
     */
    void create();

    /**
     * 监听器销毁<br/>
     * 监听器被销毁时触发
     */
    void destroy();

    /**
     * 前置通知
     * @param loader 类加载器
     * @param className 类名
     * @param methodName 方法名
     * @param methodDesc 描述
     * @param target 目标类实例
     * @param args 参数列表
     * @throws Throwable
     */
    void beforeNotice(ClassLoader loader, String className, String methodName, String methodDesc,
                      Object target, Object[] args) throws Throwable;
    /**
     * 后置通知
     * @param loader 类加载器
     * @param className 类名
     * @param methodName 方法名
     * @param methodDesc 描述
     * @param target 目标类实例
     * @param args 参数列表
     * @throws Throwable
     */
    void afterNotice(ClassLoader loader, String className, String methodName, String methodDesc,
                      Object target, Object[] args) throws Throwable;

    /**
     * 异常通知
     * @param loader 类加载器
     * @param className 类名
     * @param methodName 方法名
     * @param methodDesc 描述
     * @param target 目标类实例
     * @param args 参数列表
     * @throws Throwable
     */
    void throwingNotice(ClassLoader loader, String className, String methodName, String methodDesc,
                     Object target, Object[] args) throws Throwable;
}
