package eam.Parcial2.service;

import eam.Parcial2.entity.Car;
import eam.Parcial2.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> updateCar(Long id, Car carDetails) {
        Optional<Car> carOptional = carRepository.findById(id);
        
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            car.setMarca(carDetails.getMarca());
            car.setModelo(carDetails.getModelo());
            car.setAño(carDetails.getAño());
            car.setColor(carDetails.getColor());
            car.setPrecio(carDetails.getPrecio());
            return Optional.of(carRepository.save(car));
        }
        
        return Optional.empty();
    }

    public boolean deleteCar(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        
        if (carOptional.isPresent()) {
            carRepository.deleteById(id);
            return true;
        }
        
        return false;
    }
}
