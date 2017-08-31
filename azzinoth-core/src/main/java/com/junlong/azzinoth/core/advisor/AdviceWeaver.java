package com.junlong.azzinoth.core.advisor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by niujunlong on 2017/8/30.
 */
public class AdviceWeaver extends ClassVisitor implements Opcodes {
    private final static Logger LOG = LoggerFactory.getLogger(AdviceWeaver.class);
    // 线程帧栈堆栈大小
    private final static int FRAME_STACK_SIZE = 7;


    private final static Map<Integer,AdviceListener> advicesMap = new ConcurrentHashMap<Integer, AdviceListener>();

    public AdviceWeaver(int api) {
        super(api);
    }
}
