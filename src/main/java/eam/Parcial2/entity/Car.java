package eam.Parcial2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars") // Cambiamos el nombre de la tabla a "cars" para evitar problemas con palabras reservadas
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    
    private String marca;
    private String modelo;
    private Integer año;
    private String color;
    private Double precio;
}
