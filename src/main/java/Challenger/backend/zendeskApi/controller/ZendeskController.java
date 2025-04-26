package Challenger.backend.zendeskApi.controller;

import Challenger.backend.zendeskApi.dto.CommentRequest;
import Challenger.backend.zendeskApi.model.CommentsResponse;
import Challenger.backend.zendeskApi.service.ZendeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para operaciones con Zendesk
 */
@RestController
@RequestMapping("/api/zendesk")
@CrossOrigin(origins = "*")
public class ZendeskController {

    @Autowired
    private ZendeskService zendeskService;

    /**
     * Obtiene comentarios de un ticket
     */
    @GetMapping("/tickets/{ticketId}/comments")
    public ResponseEntity<CommentsResponse> getTicketComments(@PathVariable Long ticketId) {
        CommentsResponse comments = zendeskService.getTicketComments(ticketId);
        return ResponseEntity.ok(comments);
    }

    /**
     * Agrega un comentario a un ticket
     */
    @PostMapping("/tickets/{ticketId}/comments")
    public ResponseEntity<String> addCommentToTicket(
            @PathVariable Long ticketId,
            @RequestBody CommentRequest commentRequest) {
        zendeskService.addCommentToTicket(ticketId, commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comentario agregado con éxito");
    }
}