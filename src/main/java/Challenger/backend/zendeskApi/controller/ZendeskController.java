package Challenger.backend.zendeskApi.controller;

import Challenger.backend.zendeskApi.dto.CommentRequest;
import Challenger.backend.zendeskApi.model.commentsResponse;
import Challenger.backend.zendeskApi.service.ZendeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar las operaciones relacionadas con Zendesk
 */
@RestController
@RequestMapping("/api/zendesk")
@CrossOrigin(origins = "*")
public class ZendeskController {

    @Autowired
    private ZendeskService zendeskService;

    /**
     * Endpoint para obtener los comentarios de un ticket específico
     *  idTicket ID del ticket en Zendesk
     * return Lista de comentarios en formato JSON
     */
    @GetMapping("/tickets/{idTicket}/comments")
    public ResponseEntity<commentsResponse> obtenerComentariosTicket(@PathVariable Long idTicket) {
        commentsResponse comentarios = zendeskService.obtenerComentariosTicket(idTicket);
        return ResponseEntity.ok(comentarios);
    }

    /**
     * Endpoint para agregar un nuevo comentario a un ticket existente
     *  idTicket ID del ticket en Zendesk
     * solicitudComentario Datos del comentario a agregar
     * return Mensaje de confirmación
     */
    @PostMapping("/tickets/{idTicket}/comments")
    public ResponseEntity<String> agregarComentarioATicket(
            @PathVariable Long idTicket,
            @RequestBody CommentRequest solicitudComentario) {
        zendeskService.agregarComentarioATicket(idTicket, solicitudComentario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comentario agregado con éxito");
    }
}
