package com.junlong.azzinoth.core.utils;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * Created by niujunlong on 2017/8/29.
 */
public class AsmCodeLock implements CodeLock, Opcodes {
    private final AdviceAdapter adviceAdapter;
    /**
     * 是否锁住
     */
    private boolean isLock;
    /**
     * 代码块开始特征数组
     */
    private final int[] beginCodeArray;
    /**
     * 代码块结束特征数组
     */
    private final int[] endCodeArray;

    /**
     * 代码匹配索引
     */
    private int index = 0;

    /**
     * 用ASM构建代码块
     *
     * @param adviceAdapter  asm
     * @param beginCodeArray 代码块开始特征数组，字节码流要求不能破坏执行堆栈
     * @param endCodeArray   代码块结束特征数组，字节码流要求不能破坏执行堆栈
     */
    public AsmCodeLock(AdviceAdapter adviceAdapter, int[] beginCodeArray, int[] endCodeArray) {
        //// TODO: 2017/8/29  数组长度判断？
        if (null == beginCodeArray || null == endCodeArray || beginCodeArray.length != endCodeArray.length) {
            throw new IllegalArgumentException();
        }
        this.adviceAdapter = adviceAdapter;
        this.beginCodeArray = beginCodeArray;
        this.endCodeArray = endCodeArray;
    }

    @Override
    public void code(int opcode) {
        final int[] codes = isLock ? endCodeArray : beginCodeArray;
        if(index >= codes.length){
            reset();
            return;
        }
        if(codes[index] != opcode){
            reset();
            return;
        }
        if(++ index == codes.length){
            isLock = !isLock;
            reset();
        }
    }

    @Override
    public boolean isLock() {
       return isLock;
    }


    private void reset(){
        index = 0;
    }

    private void lock(){
        for(int op : beginCodeArray){
            adviceAdapter.visitInsn(op);
        }
    }
    private void unLock(){
        for(int op : endCodeArray){
            adviceAdapter.visitInsn(op);
        }
    }
    @Override
    public void lock(Block block) {
        lock();
        try {
            block.code();
        }finally {
            unLock();
        }
    }
}
