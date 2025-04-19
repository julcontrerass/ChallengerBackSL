package Challenger.backend.zendeskApi.dto;

public class CommentRequest {
    private String body;

    public CommentRequest() {}

    public CommentRequest(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}