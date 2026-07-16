package pl.kurs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "engines")
@NoArgsConstructor
@Getter
@Setter
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String producer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer horsepower;

    @Column(nullable = false)
    private Integer torque;

    @Column(name = "engine_number", length = 10, nullable = false, unique = true)
    private String engineNumber;

    public Engine(String producer, String model, Integer capacity, Integer horsepower, Integer torque, String engineNumber) {
        this.producer = producer;
        this.model = model;
        this.capacity = capacity;
        this.horsepower = horsepower;
        this.torque = torque;
        this.engineNumber = engineNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Engine engine)) return false;
        return getId() != null && getId().equals(engine.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
