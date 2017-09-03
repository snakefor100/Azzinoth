package com.azzinoth.server;

import com.azzinoth.constants.AppConstants;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by niujunlong on 17/9/3.
 */
@Service
public class VirtualMachineRegisterServiceImpl implements VirtualMachineRegisterService {
    private static volatile Map<String,VirtualMachine> virtualMachineMap = new ConcurrentHashMap<String,VirtualMachine>();
    @Override
    public void register(String sessionId,Integer javaPid) {
        VirtualMachine virtualMachine = null;
        try {
            virtualMachine = VirtualMachine.attach(String.valueOf(javaPid));
            virtualMachine.loadAgent(AppConstants.agentJarUrl,"aa");
            virtualMachineMap.put(sessionId,virtualMachine);
        } catch (Exception e) {
            if(null != virtualMachine){
                try {
                    virtualMachine.detach();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void close(String sessionId) {

    }
}
