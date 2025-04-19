package Challenger.backend.zendeskApi.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Modelo que representa un comentario de un ticket en Zendesk
 */
public class comment {
    private Long id;
    @JsonProperty("body")
    private String cuerpo;
    @JsonProperty("html_body")
    private String cuerpoHtml;
    @JsonProperty("plain_body")
    private String cuerpoPlano;
    @JsonProperty("public")
    private boolean esPublico;
    @JsonProperty("author_id")
    private Long idAutor;
    @JsonProperty("created_at")
    private Date fechaCreacion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getCuerpoHtml() {
        return cuerpoHtml;
    }

    public void setCuerpoHtml(String cuerpoHtml) {
        this.cuerpoHtml = cuerpoHtml;
    }

    public String getCuerpoPlano() {
        return cuerpoPlano;
    }

    public void setCuerpoPlano(String cuerpoPlano) {
        this.cuerpoPlano = cuerpoPlano;
    }

    public boolean isEsPublico() {
        return esPublico;
    }

    public void setEsPublico(boolean esPublico) {
        this.esPublico = esPublico;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}