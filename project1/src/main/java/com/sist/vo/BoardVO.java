package com.sist.vo;

import java.util.*;

public class BoardVO {

    private int board_id;
    private String u_id;
    private String board_category;
    private String board_title;
    private String board_content;
    private int board_visits;
    private Date board_date;

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getBoard_category() {
        return board_category;
    }

    public void setBoard_category(String board_category) {
        this.board_category = board_category;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public int getBoard_visits() {
        return board_visits;
    }

    public void setBoard_visits(int board_visits) {
        this.board_visits = board_visits;
    }

    public Date getBoard_date() {
        return board_date;
    }

    public void setBoard_date(Date board_date) {
        this.board_date = board_date;
    }
}