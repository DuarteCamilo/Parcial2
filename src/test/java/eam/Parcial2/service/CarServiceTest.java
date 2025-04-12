package eam.Parcial2.service;

import eam.Parcial2.entity.Car;
import eam.Parcial2.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

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
        when(carRepository.findAll()).thenReturn(cars);

        // Act
        List<Car> result = carService.getAllCars();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Toyota", result.get(0).getMarca());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void getCarById_WithExistingId_ShouldReturnCar() {
        // Arrange
        when(carRepository.findById(1L)).thenReturn(Optional.of(testCar));

        // Act
        Optional<Car> result = carService.getCarById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Toyota", result.get().getMarca());
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void getCarById_WithNonExistingId_ShouldReturnEmpty() {
        // Arrange
        when(carRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<Car> result = carService.getCarById(99L);

        // Assert
        assertFalse(result.isPresent());
        verify(carRepository, times(1)).findById(99L);
    }

    @Test
    void createCar_ShouldReturnCreatedCar() {
        // Arrange
        when(carRepository.save(any(Car.class))).thenReturn(testCar);

        // Act
        Car result = carService.createCar(testCar);

        // Assert
        assertEquals("Toyota", result.getMarca());
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    void updateCar_WithExistingId_ShouldReturnUpdatedCar() {
        // Arrange
        when(carRepository.findById(1L)).thenReturn(Optional.of(testCar));
        when(carRepository.save(any(Car.class))).thenReturn(testCar);

        Car updatedCar = new Car();
        updatedCar.setMarca("Honda");
        updatedCar.setModelo("Civic");
        updatedCar.setAño(2023);
        updatedCar.setColor("Azul");
        updatedCar.setPrecio(27000.0);

        // Act
        Optional<Car> result = carService.updateCar(1L, updatedCar);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Honda", result.get().getMarca());
        verify(carRepository, times(1)).findById(1L);
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    void updateCar_WithNonExistingId_ShouldReturnEmpty() {
        // Arrange
        when(carRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<Car> result = carService.updateCar(99L, testCar);

        // Assert
        assertFalse(result.isPresent());
        verify(carRepository, times(1)).findById(99L);
        verify(carRepository, never()).save(any(Car.class));
    }

    @Test
    void deleteCar_WithExistingId_ShouldReturnTrue() {
        // Arrange
        when(carRepository.findById(1L)).thenReturn(Optional.of(testCar));
        doNothing().when(carRepository).deleteById(1L);

        // Act
        boolean result = carService.deleteCar(1L);

        // Assert
        assertTrue(result);
        verify(carRepository, times(1)).findById(1L);
        verify(carRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCar_WithNonExistingId_ShouldReturnFalse() {
        // Arrange
        when(carRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        boolean result = carService.deleteCar(99L);

        // Assert
        assertFalse(result);
        verify(carRepository, times(1)).findById(99L);
        verify(carRepository, never()).deleteById(anyLong());
    }
}