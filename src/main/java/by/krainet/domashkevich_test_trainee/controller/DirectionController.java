package by.krainet.domashkevich_test_trainee.controller;

import by.krainet.domashkevich_test_trainee.dto.DirectionDto;
import by.krainet.domashkevich_test_trainee.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DirectionController {


    private final DirectionService service;


    @GetMapping("/directions")
    public ResponseEntity<List<DirectionDto>> getDirections(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        return ResponseEntity.ok(service.findAll(name, PageRequest.of(page, size)));
    }

    @GetMapping("/direction/{id}")
    public ResponseEntity<DirectionDto> getDirectionById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @SneakyThrows
    @PostMapping("/direction")
    public ResponseEntity<DirectionDto> saveDirection(@RequestBody DirectionDto newDirectionDto) {

        DirectionDto result = service.save(newDirectionDto);
        return ResponseEntity.created(new URI("/direction/" + result.getId())).body(result);
    }

    @PutMapping("/direction/{id}")
    public ResponseEntity<DirectionDto> updateDirection(
            @PathVariable Long id,
            @RequestBody DirectionDto newDirectionDto) {
        DirectionDto directionDto = service.findById(id);
        directionDto.setName(newDirectionDto.getName());
        directionDto.setDescription(newDirectionDto.getDescription());
        return ResponseEntity.ok().body(service.save(directionDto));
    }


}
