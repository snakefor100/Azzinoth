package com.azzinoth.agent.launcher;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

/**
 * java agent启动类
 * 参考：https://www.ibm.com/developerworks/cn/java/j-lo-jse61/index.html
 * Created by niujunlong on 2017/8/27.
 */
public class AgentLauncher {
    /**
     * 全局持有classloader，用于隔离azzino实现
     * todo: 考虑list？
     */
    private static volatile ClassLoader azzinothClassLoader;

    /**
     * 随着监听程序一起启动的agent
     */
    public static void premain(String args, Instrumentation inst) {
        System.out.println("test");
        System.out.println(args);
        System.out.println(inst);
    }

    /**
     * agent延后启动
     */
    public static void agentmain(String args, Instrumentation inst) {
        final String agentJarUrl = "/Users/didi/workspace/Azzinoth/azzinoth-agent/build/libs/azzinoth-agent.jar";
        try {
            AgentClassLoader agentClassLoader = new AgentClassLoader(agentJarUrl);
            System.out.println(222);
            Class<?> aClass = agentClassLoader.loadClass("com.azzinoth.server.impl.H2MethodService");
            Object o = aClass.newInstance();
            Object test = aClass.getMethod("test").invoke(o);
            inst.appendToBootstrapClassLoaderSearch(new JarFile(AgentLauncher.class.getProtectionDomain().getCodeSource().getLocation().getFile()));
        } catch (Exception e) {
            System.out.println(1111);
        }

    }


//    private static ClassLoader loadOrDefineClassLoader(String agentJar) throws Exception{
//        final ClassLoader classLoader;
//        //如果azzinothClassLoader有值，说明之前启动过，返回azzinothClassLoader
//        if(null != azzinothClassLoader){
//            return azzinothClassLoader;
//        }
//        classLoader = new AgentClassLoader(agentJar);
//        classLoader.loadClass()
//        Mage.initForAgentLauncher(classLoader,advice);
//    }
//
//    private static synchronized void startAgent(final String args, final Instrumentation inst){
//        try {
//            //args分为两个部分，以冒号分割：agentJar的路径，以及传递到服务器的参数
//            final int index = args.indexOf(AgentConstants.SYMBOL_COLON);
//            final String agentJar = args.substring(0, index);
//            final String agentArgs = args.substring(index, args.length());
//            //将agent添加到BootstrapClassLoader中
//            inst.appendToBootstrapClassLoaderSearch(new JarFile(AgentLauncher.class.getProtectionDomain().getCodeSource().getLocation().getFile()));
//            //构造自定义类加载器，减小对运行中工程的影响
////            loadO
//
//        }catch (Exception e){
//
//        }
//    }
}
