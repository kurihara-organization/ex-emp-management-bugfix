package com.example.form;

public class SearchByNameForm {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SearchByNameForm{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
