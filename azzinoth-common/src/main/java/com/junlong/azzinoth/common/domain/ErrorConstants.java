package com.junlong.azzinoth.common.domain;

/**
 * 错误信息常量
 * Created by niujunlong on 2017/7/23.
 */
public class ErrorConstants {
    public static final String APROENUM_LOG = "方法名:[ {} ], 方法入参: [ {} ], 方法出参: [ {} ] ";

    public static enum Error {
        METHOD_TIME_OUT(1000, "方法超时");
        private int code;
        private String msg;

        Error(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
