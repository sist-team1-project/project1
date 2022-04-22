package com.sist.vo;

public class ReviewVO {
    
    private int review_id;
    private String u_id;
    private int c_id;
    private String review_content;
    private int review_goodbad;
    private String review_job;
    private String review_date;

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public int getReview_goodbad() {
        return review_goodbad;
    }

    public void setReview_goodbad(int review_goodbad) {
        this.review_goodbad = review_goodbad;
    }

    public String getReview_job() {
        return review_job;
    }

    public void setReview_job(String review_job) {
        this.review_job = review_job;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }
}