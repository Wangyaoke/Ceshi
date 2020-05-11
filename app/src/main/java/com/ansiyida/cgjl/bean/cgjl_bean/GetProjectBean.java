package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class GetProjectBean {

    /**
     * data : [{"publishTime":"2019-09-17 19:13:00","title":"江苏大洲工程项目管理有限公司凌云县2019年第一批财政扶贫资金基础设施建设项目监理服务（BSZC2019-J3-08022-JSDZ）成交公告"},{"publishTime":"2019-09-16 19:08:00","title":"江苏大洲工程项目管理有限公司来宾市公安局来宾市公安监所所外就医防脱逃系统竞争性谈判公告"},{"publishTime":"2019-09-01 17:25:00","title":"江苏大洲工程项目管理有限公司象州县农业农村局象州县2019年中央农业生产救灾农作物病虫害防治补助资金项目竞争性谈判公告"},{"publishTime":"2019-08-16 17:36:00","title":"江苏大洲工程项目管理有限公司关于贺州市平桂区羊头镇双季莲藕现代特色农业核心示范区（垒石村）产业发展道路建设工程II期监理竞争性磋商采购公告"},{"publishTime":"2019-08-15 17:37:00","title":"江苏大洲工程项目管理有限公司关于贺州市生态新城核心区外围南部（局部）片区规划八步区农民\u201c两地\u201d范围桂东35kV高压线路迁移工程招标公告"},{"publishTime":"2019-08-12 12:17:00","title":"江苏大洲工程项目管理有限公司象州县中平镇人民政府中平镇环卫保洁、垃圾清运服务公开招标公告"},{"publishTime":"2019-08-12 11:31:00","title":"江苏大洲工程项目管理有限公司金秀瑶族自治县旅游投资有限公司金秀瑶族自治县六巷乡青山旅游驿站工程成交公告"},{"publishTime":"2019-07-09 18:35:00","title":"江苏大洲工程项目管理有限公司象州县运江镇人民政府象州县运江镇上坪村委周边景观改造工程成交公告"},{"publishTime":"2019-07-01 12:00:00","title":"江苏大洲工程项目管理有限公司关于2019年度智能摄像远传水表抄读系统、衬塑复合钢管、阀门、伸缩节、铜阀门、钢塑配件采购项目（年度标）(第三次招标)公开招标公告"},{"publishTime":"2019-06-24 17:51:00","title":"江苏大洲工程项目管理有限公司呼吸机（BSZC2019-J1-11003-JSDZ）竞争性谈判公告"},{"focus":false}]
     * message : 成功
     * status : 200
     */

    private String message;
    private int status;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetProjectBean{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * publishTime : 2019-09-17 19:13:00
         * title : 江苏大洲工程项目管理有限公司凌云县2019年第一批财政扶贫资金基础设施建设项目监理服务（BSZC2019-J3-08022-JSDZ）成交公告
         * focus : false
         */

        private String publishTime;
        private String title;
        private boolean focus;

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isFocus() {
            return focus;
        }

        public void setFocus(boolean focus) {
            this.focus = focus;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "publishTime='" + publishTime + '\'' +
                    ", title='" + title + '\'' +
                    ", focus=" + focus +
                    '}';
        }
    }
}
