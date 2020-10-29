package com.cn.weixuan.base.module;

import java.io.Serializable;

public class ReponseBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Object o = "";// 结果返回值
    int code;// 成功代码，0=失败，1=成功
    String msg = "";// 当code=0的时候msg提示错误信息

    public Object getO() {
        return o;
    }

    public ReponseBean setO(Object o) {
        this.o = o;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ReponseBean setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ReponseBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 返回成功对象
     *
     * @param o
     * @return
     */
    public static ReponseBean success(Object o) {
        return new ReponseBean().setCode(1).setO(o);
    }

    /**
     * 返回成功对象
     *
     * @param code
     * @param o
     * @return
     */
    public static ReponseBean success(int code, Object o) {
        return new ReponseBean().setCode(code).setO(o);
    }

    /**
     * 返回成功对象
     *
     * @return
     */
    public static ReponseBean success() {
        return success(1, null);
    }

    /**
     * 返回错误消息对象
     *
     * @param msg
     * @return
     */
    public static ReponseBean fail(String msg) {
        return fail(0, msg);
    }

    /**
     * 返回错误消息对象
     *
     * @param code
     * @param msg
     * @return
     */
    public static ReponseBean fail(int code, String msg) {
        return new ReponseBean().setCode(code).setMsg(msg);
    }

}
