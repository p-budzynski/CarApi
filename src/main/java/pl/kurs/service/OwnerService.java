package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.dto.OwnerDto;
import pl.kurs.entity.Car;
import pl.kurs.entity.Owner;
import pl.kurs.exception.OwnerNotFoundException;
import pl.kurs.mapper.OwnerMapper;
import pl.kurs.repository.OwnerRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final CarService carService;
    private final OwnerMapper ownerMapper;

    public Owner addOwner(Owner owner) {
        log.info("Adding new owner: firstName={}, lastName={}",
                owner.getFirstName(),
                owner.getLastName());

        Owner savedOwner = ownerRepository.save(owner);

        log.info("Owner successfully added with id={}", savedOwner.getId());

        return savedOwner;
    }

    public Owner getOwnerById(Long id) {
        log.info("Searching for owner with id={}", id);

        return ownerRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Owner with id={} does not exist", id);
                    return new OwnerNotFoundException("Owner does not exists");
                });
    }

    @Transactional
    public void assignCarToOwner(Long id, Long carId) {
        log.info("Assigning car with id={} to owner with id={}", carId, id);

        Owner owner = getOwnerById(id);
        Car car = carService.getCarById(carId);

        owner.addCar(car);

        ownerRepository.save(owner);

        log.info("Car with id={} successfully assigned to owner with id={}", carId, id);
    }

    @Transactional
    public Owner updateOwner(Long id, OwnerDto ownerDto) {

        Owner owner = getOwnerById(id);

        ownerMapper.updateOwnerFromDto(ownerDto, owner);

        return owner;
    }

    @Transactional
    public void deleteOwnerById(Long id) {
        log.info("Deleting owner with id={}", id);

        Owner owner = getOwnerById(id);

        owner.getCars().forEach(car -> car.setOwner(null));

        ownerRepository.delete(owner);

        log.info("Owner with id={} successfully deleted", id);
    }
}
