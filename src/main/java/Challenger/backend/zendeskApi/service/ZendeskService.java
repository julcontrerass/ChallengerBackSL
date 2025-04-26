package Challenger.backend.zendeskApi.service;

import Challenger.backend.zendeskApi.dto.CommentRequest;
import Challenger.backend.zendeskApi.model.CommentsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Servicio para API de Zendesk
 */
@Service
public class ZendeskService {

    @Value("${zendesk.subdomain}")
    private String subdomain;

    @Value("${zendesk.token}")
    private String token;

    @Value("${zendesk.email}")
    private String email;

    /**
     * Obtiene comentarios de un ticket
     */
    public CommentsResponse getTicketComments(Long ticketId) {
        String url = "https://" + subdomain + ".zendesk.com/api/v2/tickets/" + ticketId + "/comments.json";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodeAuthentication());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restClient = new RestTemplate();

        try {
            ResponseEntity<CommentsResponse> response = restClient.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    CommentsResponse.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al obtener comentarios del ticket ID: " + ticketId + " - " + e.getMessage());
        }
    }

    /**
     * Agrega comentario a un ticket existente
     */
    public void addCommentToTicket(Long ticketId, CommentRequest commentRequest) {
        String url = "https://" + subdomain + ".zendesk.com/api/v2/tickets/" + ticketId + ".json";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodeAuthentication());
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crear estructura para la solicitud
        Map<String, Object> comment = new HashMap<>();
        comment.put("body", commentRequest.getBody());
        comment.put("public", true);

        Map<String, Object> ticket = new HashMap<>();
        ticket.put("comment", comment);

        Map<String, Object> payload = new HashMap<>();
        payload.put("ticket", ticket);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
        RestTemplate restClient = new RestTemplate();

        try {
            restClient.exchange(url, HttpMethod.PUT, entity, String.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error al agregar comentario al ticket ID: " + ticketId + " - " + e.getMessage());
        }
    }

    /**
     * Codifica credenciales para autenticación
     */
    private String encodeAuthentication() {
        String authentication = email + "/token:" + token;
        return java.util.Base64.getEncoder().encodeToString(authentication.getBytes());
    }
}