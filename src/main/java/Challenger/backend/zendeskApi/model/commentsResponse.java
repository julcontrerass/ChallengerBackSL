package Challenger.backend.zendeskApi.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Modelo para la respuesta de la API que contiene la lista de comentarios
 */
public class commentsResponse {
    private List<comment> comments;
    @JsonProperty("next_page")
    private String paginaSiguiente;
    @JsonProperty("previous_page")
    private String paginaAnterior;
    private Long count;

    // Getters y Setters
    public List<comment> getComments() {
        return comments;
    }

    public void setComments(List<comment> comments) {
        this.comments = comments;
    }

    public String getPaginaSiguiente() {
        return paginaSiguiente;
    }

    public void setPaginaSiguiente(String paginaSiguiente) {
        this.paginaSiguiente = paginaSiguiente;
    }

    public String getPaginaAnterior() {
        return paginaAnterior;
    }

    public void setPaginaAnterior(String paginaAnterior) {
        this.paginaAnterior = paginaAnterior;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}