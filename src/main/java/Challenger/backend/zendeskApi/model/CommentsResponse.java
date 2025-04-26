package Challenger.backend.zendeskApi.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Respuesta con lista de comentarios y paginación
 */
public class CommentsResponse {
    private List<comment> comments;
    @JsonProperty("next_page")
    private String nextPage;
    @JsonProperty("previous_page")
    private String previousPage;
    private Long count;

    // Getters y Setters
    public List<comment> getComments() {
        return comments;
    }

    public void setComments(List<comment> comments) {
        this.comments = comments;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}