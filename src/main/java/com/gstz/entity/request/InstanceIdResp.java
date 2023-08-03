package com.gstz.entity.request;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2023/8/3 10:17
 */
public class InstanceIdResp {

    /**
     * code : 0
     * msg : 成功
     * data : {"instanceId":"e259493c-2db1-436f-9454-f3bc1cb6ae19","workItemID":null}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * instanceId : e259493c-2db1-436f-9454-f3bc1cb6ae19
         * workItemID : null
         */

        private String instanceId;
        private Object workItemID;

        public String getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }

        public Object getWorkItemID() {
            return workItemID;
        }

        public void setWorkItemID(Object workItemID) {
            this.workItemID = workItemID;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "instanceId='" + instanceId + '\'' +
                    ", workItemID=" + workItemID +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "InstanceIdResp{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
