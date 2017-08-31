package com.azzinoth.agent.launcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.instrument.Instrumentation;
import java.util.Random;

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
     * @param args
     * @param inst
     */
    public static void premain(String args, Instrumentation inst){
        System.out.println(args);
        System.out.println(inst);
    }

    /**
     * agent延后启动
     * @param args
     * @param inst
     */
    public static void agentmain(String args, Instrumentation inst) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            int i = new Random().nextInt(10);
            File f = new File("F:\\\\test\\"+i+".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f));
            bufferedWriter.write(objectMapper.writeValueAsString(args));
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.write(objectMapper.writeValueAsString(inst));
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println(args);
            System.out.println(inst);
        }catch (Exception e){
            e.printStackTrace();
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
