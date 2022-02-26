package br.com.api;

public class Register {

    private String title;
    private String body;
    private Integer userId;

    public Register(String title, String body, Integer userId){
            this.title = title;
            this.body = body;
            this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
