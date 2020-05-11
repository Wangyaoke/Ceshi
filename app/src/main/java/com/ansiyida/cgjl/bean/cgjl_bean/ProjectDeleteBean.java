package com.ansiyida.cgjl.bean.cgjl_bean;

public class ProjectDeleteBean {

    /**
     * article : string
     * source : string
     */

    private String article;
    private String source;

    public ProjectDeleteBean(String article, String source) {
        this.article = article;
        this.source = source;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "{" +
                "article='" + article + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
