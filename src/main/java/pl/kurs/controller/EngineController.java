package pl.kurs.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.dto.CarDto;
import pl.kurs.dto.EngineDto;
import pl.kurs.entity.Car;
import pl.kurs.entity.Engine;
import pl.kurs.mapper.EngineMapper;
import pl.kurs.service.EngineService;
import pl.kurs.validation.Create;
import pl.kurs.validation.Update;

@Validated
@RestController
@RequestMapping("/engines")
@RequiredArgsConstructor
public class EngineController {

    private final EngineService engineService;
    private final EngineMapper engineMapper;

    @GetMapping("/{id}")
    public ResponseEntity<EngineDto> getEngineById(@PathVariable("id") @Min(value = 1, message = "ID must be greater than zero!") Long id) {
        Engine engine = engineService.getEngineById(id);
        return ResponseEntity.ok(engineMapper.entityToDto(engine));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EngineDto addEngine(@RequestBody @Validated(Create.class) EngineDto engineDto) {
        Engine engine = engineMapper.dtoToEntity(engineDto);
        Engine addedEngine = engineService.addedEngine(engine);
        return engineMapper.entityToDto(engine);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EngineDto> updateEngine(@PathVariable("id") @Min(value = 1, message = "ID must be greater than zero!") Long id,
                                                  @RequestBody @Validated(Update.class) EngineDto engineDto) {
        Engine updatedEngine = engineService.updateEngine(id, engineDto);
        return ResponseEntity.ok(engineMapper.entityToDto(updatedEngine));
    }

    @DeleteMapping("/{id}")
    public void deleteEngineById(@PathVariable @Min(value = 1, message = "ID must be greater than zero!") Long id) {
        engineService.deleteEngineById(id);
    }

}
