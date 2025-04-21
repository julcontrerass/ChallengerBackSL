package Challenger.backend.carrera.repository;



import Challenger.backend.carrera.model.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
    // Esta interfaz hereda de JpaRepository, lo que nos da métodos
    // CRUD (crear, leer, actualizar y eliminar) para la entidad Degree sin necesidad de implementarlos.
}