package Challenger.backend.zendeskApi.service;

import Challenger.backend.zendeskApi.dto.CommentRequest;
import Challenger.backend.zendeskApi.model.commentsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Servicio para interactuar con la API de Zendesk
 */
@Service
public class ZendeskService {

    @Value("${zendesk.subdomain}")
    private String subdominio;

    @Value("${zendesk.token}")
    private String token;

    @Value("${zendesk.email}")
    private String email;

    /**
     * Obtiene los comentarios de un ticket específico
     * idTicket ID del ticket en Zendesk
     * return Objeto con la lista de comentarios y metadatos de paginación
     */
    public commentsResponse obtenerComentariosTicket(Long idTicket) {
        String url = "https://" + subdominio + ".zendesk.com/api/v2/tickets/" + idTicket + "/comments.json";

        HttpHeaders cabeceras = new HttpHeaders();
        cabeceras.set("Authorization", "Basic " + codificarAutenticacion());
        cabeceras.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entidad = new HttpEntity<>(cabeceras);
        RestTemplate clienteRest = new RestTemplate();

        try {
            ResponseEntity<commentsResponse> respuesta = clienteRest.exchange(
                    url,
                    HttpMethod.GET,
                    entidad,
                    commentsResponse.class
            );
            return respuesta.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener comentarios del ticket ID: " + idTicket + " - " + e.getMessage());
        }
    }

    /**
     * Agrega un nuevo comentario a un ticket existente
     *  idTicket ID del ticket en Zendesk
     *  solicitudComentario Datos del comentario a agregar
     */
    public void agregarComentarioATicket(Long idTicket, CommentRequest solicitudComentario) {
        String url = "https://" + subdominio + ".zendesk.com/api/v2/tickets/" + idTicket + ".json";

        HttpHeaders cabeceras = new HttpHeaders();
        cabeceras.set("Authorization", "Basic " + codificarAutenticacion());
        cabeceras.setContentType(MediaType.APPLICATION_JSON);

        // Crear estructura de datos para la solicitud según la API de Zendesk
        Map<String, Object> comentario = new HashMap<>();
        comentario.put("body", solicitudComentario.getBody());
        comentario.put("public", true);

        Map<String, Object> ticket = new HashMap<>();
        ticket.put("comment", comentario);

        Map<String, Object> cargaUtil = new HashMap<>();
        cargaUtil.put("ticket", ticket);

        HttpEntity<Map<String, Object>> entidad = new HttpEntity<>(cargaUtil, cabeceras);
        RestTemplate clienteRest = new RestTemplate();

        try {
            clienteRest.exchange(url, HttpMethod.PUT, entidad, String.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al agregar comentario al ticket ID: " + idTicket + " - " + e.getMessage());
        }
    }

    /**
     * Codifica las credenciales para la autenticación con Zendesk
     * return Credenciales codificadas en Base64
     */
    private String codificarAutenticacion() {
        String autenticacion = email + "/token:" + token;
        return java.util.Base64.getEncoder().encodeToString(autenticacion.getBytes());
    }
}