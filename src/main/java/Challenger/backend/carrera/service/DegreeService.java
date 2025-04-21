package Challenger.backend.carrera.service;

import Challenger.backend.carrera.model.Degree;
import Challenger.backend.carrera.repository.DegreeRepository;
import Challenger.backend.carrera.exception.DegreeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DegreeService {

    @Autowired
    private DegreeRepository repository;

    // Devuelve todas las carreras disponibles en la base de datos
    public List<Degree> getAll() {
        return repository.findAll();
    }

    // Busca una carrera por su ID y devuelve un Optional (puede estar o no presente)
    public Optional<Degree> getById(Long id) {
        return repository.findById(id);
    }

    // Crea una nueva carrera en la base de datos
    public Degree createDegree(Degree degree) {
        return repository.save(degree);
    }

    // Actualiza una carrera existente si se encuentra, si no lanza excepción
    public Degree updateDegree(Long id, Degree updatedDegree) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedDegree.getName());
            existing.setDescription(updatedDegree.getDescription());
            existing.setDurationYears(updatedDegree.getDurationYears());
            return repository.save(existing);
        }).orElseThrow(() -> new DegreeNotFoundException("Degree with ID " + id + " not found"));
    }

    // Elimina una carrera si existe, si no lanza excepción
    public void deleteDegree(Long id) {
        if (!repository.existsById(id)) {
            throw new DegreeNotFoundException("Degree with ID " + id + " not found");
        }
        repository.deleteById(id);
    }
}