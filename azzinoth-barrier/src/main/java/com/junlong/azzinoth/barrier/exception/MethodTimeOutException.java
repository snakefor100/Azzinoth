package com.junlong.azzinoth.barrier.exception;

import com.junlong.azzinoth.common.constants.ErrorConstants.Error;

/**
 * AProfiler配置超时监控后的方法超时异常
 * Created by niujunlong on 2017/7/23.
 */
public class MethodTimeOutException extends RuntimeException{
    private Error error;

    public MethodTimeOutException(Error error) {
        super("方法超时,异常CODE:[" + error.getCode() + "],异常信息:["+error.getMsg()+"]");
        this.error = error;
    }

    public MethodTimeOutException(Error error, Throwable e) {
        super("方法超时,异常CODE:[" + error.getCode() + "],异常信息:["+error.getMsg()+"]" + "Exception : " + e.getMessage(),e);
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
