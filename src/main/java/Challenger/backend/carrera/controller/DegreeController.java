package Challenger.backend.carrera.controller;

import Challenger.backend.carrera.model.Degree;
import Challenger.backend.carrera.service.DegreeService;
import Challenger.backend.carrera.exception.DegreeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/degrees")
public class DegreeController {

    @Autowired
    private DegreeService service;

    // Obtiene una lista con todas las carreras
    @GetMapping
    public List<Degree> getAllDegrees() {
        return service.getAll();
    }

    // Obtiene una carrera específica por su ID. Si no existe, lanza excepción.
    @GetMapping("/{id}")
    public Degree getDegreeById(@PathVariable Long id) {
        return service.getById(id)
                .orElseThrow(() -> new DegreeNotFoundException("Degree with ID " + id + " not found"));
    }

    // Crea una nueva carrera. El cuerpo debe ser válido.
    @PostMapping
    public Degree createDegree(@Valid @RequestBody Degree degree) {
        return service.createDegree(degree);
    }

    // Actualiza una carrera existente con el ID especificado
    @PutMapping("/{id}")
    public Degree updateDegree(@PathVariable Long id, @Valid @RequestBody Degree degree) {
        return service.updateDegree(id, degree);
    }

    // Elimina una carrera por su ID
    @DeleteMapping("/{id}")
    public void deleteDegree(@PathVariable Long id) {
        service.deleteDegree(id);
    }
}