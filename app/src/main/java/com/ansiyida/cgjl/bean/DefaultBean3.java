package com.ansiyida.cgjl.bean;

/**
 * Created by ansiyida on 2018/3/12.
 */
public class DefaultBean3 {

    /**
     * status : 0000
     * message : 失败
     * data : {"flag":false,"msg":"账号已存在,请前去登录"}
     */

    private String status;
    private String message;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * flag : false
         * msg : 账号已存在,请前去登录
         */

        private boolean flag;
        private String msg;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
