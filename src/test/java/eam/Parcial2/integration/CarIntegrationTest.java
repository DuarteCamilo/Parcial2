package eam.Parcial2.integration;

import eam.Parcial2.entity.Car;
import eam.Parcial2.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CarIntegrationTest {

    @Autowired
    private CarService carService;

    @Test
    void testCreateAndRetrieveCar() {
        // Crear un coche
        Car car = new Car();
        car.setMarca("Toyota");
        car.setModelo("Corolla");
        car.setAño(2022);
        car.setColor("Rojo");
        car.setPrecio(25000.0);
        
        Car savedCar = carService.createCar(car);
        
        // Verificar que el coche se ha creado correctamente
        assertNotNull(savedCar.getCarId());
        assertEquals("Toyota", savedCar.getMarca());
        
        // Recuperar el coche por ID
        Optional<Car> retrievedCar = carService.getCarById(savedCar.getCarId());
        assertTrue(retrievedCar.isPresent());
        assertEquals("Toyota", retrievedCar.get().getMarca());
    }

    @Test
    void testGetAllCars() {
        // Crear varios coches
        Car car1 = new Car();
        car1.setMarca("Toyota");
        car1.setModelo("Corolla");
        car1.setAño(2022);
        car1.setColor("Rojo");
        car1.setPrecio(25000.0);
        carService.createCar(car1);
        
        Car car2 = new Car();
        car2.setMarca("Honda");
        car2.setModelo("Civic");
        car2.setAño(2023);
        car2.setColor("Azul");
        car2.setPrecio(27000.0);
        carService.createCar(car2);
        
        // Obtener todos los coches
        List<Car> cars = carService.getAllCars();
        
        // Verificar que hay al menos dos coches
        assertTrue(cars.size() >= 2);
        
        // Verificar que los coches creados están en la lista
        boolean foundToyota = false;
        boolean foundHonda = false;
        
        for (Car car : cars) {
            if ("Toyota".equals(car.getMarca()) && "Corolla".equals(car.getModelo())) {
                foundToyota = true;
            }
            if ("Honda".equals(car.getMarca()) && "Civic".equals(car.getModelo())) {
                foundHonda = true;
            }
        }
        
        assertTrue(foundToyota);
        assertTrue(foundHonda);
    }

    @Test
    void testUpdateCar() {
        // Crear un coche
        Car car = new Car();
        car.setMarca("Toyota");
        car.setModelo("Corolla");
        car.setAño(2022);
        car.setColor("Rojo");
        car.setPrecio(25000.0);
        
        Car savedCar = carService.createCar(car);
        
        // Actualizar el coche
        Car updatedDetails = new Car();
        updatedDetails.setMarca("Toyota");
        updatedDetails.setModelo("Corolla");
        updatedDetails.setAño(2022);
        updatedDetails.setColor("Azul");
        updatedDetails.setPrecio(27000.0);
        
        Optional<Car> updatedCar = carService.updateCar(savedCar.getCarId(), updatedDetails);
        
        // Verificar que el coche se ha actualizado correctamente
        assertTrue(updatedCar.isPresent());
        assertEquals("Azul", updatedCar.get().getColor());
        assertEquals(27000.0, updatedCar.get().getPrecio());
        
        // Verificar que los cambios se han guardado en la base de datos
        Optional<Car> retrievedCar = carService.getCarById(savedCar.getCarId());
        assertTrue(retrievedCar.isPresent());
        assertEquals("Azul", retrievedCar.get().getColor());
        assertEquals(27000.0, retrievedCar.get().getPrecio());
    }

    @Test
    void testDeleteCar() {
        // Crear un coche
        Car car = new Car();
        car.setMarca("Toyota");
        car.setModelo("Corolla");
        car.setAño(2022);
        car.setColor("Rojo");
        car.setPrecio(25000.0);
        
        Car savedCar = carService.createCar(car);
        
        // Verificar que el coche existe
        Optional<Car> retrievedCar = carService.getCarById(savedCar.getCarId());
        assertTrue(retrievedCar.isPresent());
        
        // Eliminar el coche
        boolean deleted = carService.deleteCar(savedCar.getCarId());
        assertTrue(deleted);
        
        // Verificar que el coche ha sido eliminado
        Optional<Car> deletedCar = carService.getCarById(savedCar.getCarId());
        assertFalse(deletedCar.isPresent());
    }
}