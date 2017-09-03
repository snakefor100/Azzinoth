package com.azzinoth.server;

/**
 * Created by niujunlong on 17/9/3.
 */
public interface VirtualMachineRegisterService {
    void register(String sessionId,Integer javaPid);
    void close(String sessionId);
}
