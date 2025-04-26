package Challenger.backend.zendeskApi.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Modelo de comentario de ticket en Zendesk
 */
public class comment {
    private Long id;
    @JsonProperty("body")
    private String body;
    @JsonProperty("html_body")
    private String htmlBody;
    @JsonProperty("plain_body")
    private String plainBody;
    @JsonProperty("public")
    private boolean isPublic;
    @JsonProperty("author_id")
    private Long authorId;
    @JsonProperty("created_at")
    private Date createdAt;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public String getPlainBody() {
        return plainBody;
    }

    public void setPlainBody(String plainBody) {
        this.plainBody = plainBody;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}