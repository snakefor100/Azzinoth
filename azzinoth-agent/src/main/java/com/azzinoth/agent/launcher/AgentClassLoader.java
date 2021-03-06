package com.azzinoth.agent.launcher;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * agent的classLoader
 * Created by niujunlong on 2017/8/27.
 */
public class AgentClassLoader extends URLClassLoader {
    public AgentClassLoader(final String agentJar) throws MalformedURLException {
        super(new URL[]{new URL("file:" + agentJar)});
    }

    /**
     * 加载类(双亲委派)
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        final Class<?> loadedClass = findLoadedClass(name);
        if(loadedClass != null){
            return loadedClass;
        }
        try {
            Class<?> aClass = findClass(name);
            if(resolve){
                resolveClass(aClass);
            }
            return aClass;
        }catch (Exception e){
            return super.loadClass(name,resolve);
        }
    }
}
