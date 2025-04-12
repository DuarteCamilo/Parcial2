package eam.Parcial2.repository;

import eam.Parcial2.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Solo mantenemos el método de búsqueda más común
    Optional<Car> findByMarca(String marca);
}
