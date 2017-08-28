package com.junlong.azzinoth.core.utils;

/**
 * 代码锁<br/>
 * 字节码中，无法判别代码是自己生成的，还是原有的，会导致监控逻辑的混乱。
 * 例如：trace命令，就需要代码锁保护起来，否则会看到植入的代码
 * Created by niujunlong on 2017/8/28.
 */
public interface CodeLock {
    /**
     * 根据字节码流锁或者解锁代码<br/>
     * 通过对字节码流的判断，决定当前代码是锁定还是解锁
     * @param opcode 字节码
     */
    void code(int opcode);

    /**
     * 判断代码是否还在锁定
     */
    boolean isLock();

    /**
     * 将代码块锁住
     * @param block 代码块
     */
    void lock(Block block);


    /**
     * 代码block块
     */
    interface Block{
        /**
         * 代码
         */
        void code();
    }
}
