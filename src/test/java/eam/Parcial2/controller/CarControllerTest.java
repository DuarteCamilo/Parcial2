package eam.Parcial2.controller;

import eam.Parcial2.entity.Car;
import eam.Parcial2.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    private Car testCar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testCar = new Car();
        testCar.setCarId(1L);
        testCar.setMarca("Toyota");
        testCar.setModelo("Corolla");
        testCar.setAño(2022);
        testCar.setColor("Rojo");
        testCar.setPrecio(25000.0);
    }

    @Test
    void getAllCars_ShouldReturnAllCars() {
        // Arrange
        List<Car> cars = Arrays.asList(testCar);
        when(carService.getAllCars()).thenReturn(cars);

        // Act
        List<Car> result = carController.getAllCars();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Toyota", result.get(0).getMarca());
        verify(carService, times(1)).getAllCars();
    }

    @Test
    void getCarById_WithExistingId_ShouldReturnCar() {
        // Arrange
        when(carService.getCarById(1L)).thenReturn(Optional.of(testCar));

        // Act
        ResponseEntity<Car> response = carController.getCarById(1L);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Toyota", response.getBody().getMarca());
        verify(carService, times(1)).getCarById(1L);
    }

    @Test
    void getCarById_WithNonExistingId_ShouldReturnNotFound() {
        // Arrange
        when(carService.getCarById(99L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Car> response = carController.getCarById(99L);

        // Assert
        assertTrue(response.getStatusCode().is4xxClientError());
        verify(carService, times(1)).getCarById(99L);
    }

    @Test
    void createCar_ShouldReturnCreatedCar() {
        // Arrange
        when(carService.createCar(any(Car.class))).thenReturn(testCar);

        // Act
        Car result = carController.createCar(testCar);

        // Assert
        assertEquals("Toyota", result.getMarca());
        verify(carService, times(1)).createCar(any(Car.class));
    }

    @Test
    void updateCar_WithExistingId_ShouldReturnUpdatedCar() {
        // Arrange
        when(carService.updateCar(eq(1L), any(Car.class))).thenReturn(Optional.of(testCar));

        // Act
        ResponseEntity<Car> response = carController.updateCar(1L, testCar);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Toyota", response.getBody().getMarca());
        verify(carService, times(1)).updateCar(eq(1L), any(Car.class));
    }

    @Test
    void updateCar_WithNonExistingId_ShouldReturnNotFound() {
        // Arrange
        when(carService.updateCar(eq(99L), any(Car.class))).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Car> response = carController.updateCar(99L, testCar);

        // Assert
        assertTrue(response.getStatusCode().is4xxClientError());
        verify(carService, times(1)).updateCar(eq(99L), any(Car.class));
    }

    @Test
    void deleteCar_WithExistingId_ShouldReturnOk() {
        // Arrange
        when(carService.deleteCar(1L)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = carController.deleteCar(1L);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(carService, times(1)).deleteCar(1L);
    }

    @Test
    void deleteCar_WithNonExistingId_ShouldReturnNotFound() {
        // Arrange
        when(carService.deleteCar(99L)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = carController.deleteCar(99L);

        // Assert
        assertTrue(response.getStatusCode().is4xxClientError());
        verify(carService, times(1)).deleteCar(99L);
    }
}