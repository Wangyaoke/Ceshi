package com.ansiyida.cgjl.bean;

/**
 * Created by chenyaoxiang on 2017/12/28.
 */
public class CollegeBean {
    private String type;
    private boolean edit;
    private boolean isCheck;
    private String time;
    private String title;
    private String imgUrl;
    private String id;
    private String jc_id;
    private String videoTime;



    public CollegeBean(String type,String id,boolean isCheck,boolean edit,String time,String title,String imgUrl,String jc_id,String videoTime){
        this.type=type;
        this.edit=edit;
        this.isCheck=isCheck;
        this.time=time;
        this.title=title;
        this.imgUrl=imgUrl;
        this.id=id;
        this.jc_id=jc_id;
        this.videoTime=videoTime;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public String getJc_id() {
        return jc_id;
    }

    public void setJc_id(String jc_id) {
        this.jc_id = jc_id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEdit() {
        return edit;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
