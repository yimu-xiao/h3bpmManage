package com.gstz.entity.request.user;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2023/8/3 9:57
 */
public class UserResp {

    /**
     * code : 0
     * data : {"ParentID":"10006ddddddddddddddddddddddddddddddd","ObjectID":"2702uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu","SourceID":"2702"}
     */

    private Integer code;
    private DataBean data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ParentID : 10006ddddddddddddddddddddddddddddddd
         * ObjectID : 2702uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu
         * SourceID : 2702
         */

        private String ParentID;
        private String ObjectID;
        private String SourceID;

        public String getParentID() {
            return ParentID;
        }

        public void setParentID(String ParentID) {
            this.ParentID = ParentID;
        }

        public String getObjectID() {
            return ObjectID;
        }

        public void setObjectID(String ObjectID) {
            this.ObjectID = ObjectID;
        }

        public String getSourceID() {
            return SourceID;
        }

        public void setSourceID(String SourceID) {
            this.SourceID = SourceID;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "ParentID='" + ParentID + '\'' +
                    ", ObjectID='" + ObjectID + '\'' +
                    ", SourceID='" + SourceID + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserResp{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
