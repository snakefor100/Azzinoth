package com.junlong.azzinoth.core.utils;

import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by niujunlong on 2017/8/31.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        VirtualMachine vm = null;
        try {
File f = new File("/Users/didi/workspace/Azzinoth/azzinoth-agent/build/libs/azzinoth-agent.jar");

            vm = VirtualMachine.attach("1847");
            System.out.println(vm.list());
//            vm.loadAgent("F:\\\\workspace\\github\\Azzinoth\\azzinoth-agent\\build\\libs\\azzinoth-agent.jar","ff");
            vm.loadAgent(f.getAbsolutePath(),"toagent");
            System.out.println(11);
        }catch (Exception e){
            e.printStackTrace();
            vm.detach();
        }

    }
}
