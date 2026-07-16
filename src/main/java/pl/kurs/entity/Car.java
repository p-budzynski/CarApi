package pl.kurs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String producer;

    @Column(nullable = false)
    private String model;

    @Column(name = "year_of_production", nullable = false)
    private Integer yearOfProduction;

    @Column(name = "registration_number", length = 10, nullable = false, unique = true)
    private String registrationNumber;

    @Column(name = "vin_number", length = 17, nullable = false, unique = true)
    private String vinNumber;

    @Column(nullable = false)
    private String color;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "engine_fk")
    private Engine engine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_fk")
    private Owner owner;

    public Car(String producer, String model, Integer yearOfProduction, String registrationNumber, String vinNumber, String color, Engine engine) {
        this.producer = producer;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.registrationNumber = registrationNumber;
        this.vinNumber = vinNumber;
        this.color = color;
        this.engine = engine;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Car car)) return false;
        return getId() != null && getId().equals(car.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
