package com.ansiyida.cgjl.view;

/**
 * Created by ansiyida on 2018/3/22.
 */
public class Entity implements RecyclerBanner.BannerEntity {
    private String url;
    private String jump;
    public Entity(String url,String jump) {
        this.url = url;
        this.jump=jump;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
