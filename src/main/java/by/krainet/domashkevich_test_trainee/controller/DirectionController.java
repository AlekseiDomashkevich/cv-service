package by.krainet.domashkevich_test_trainee.controller;

import by.krainet.domashkevich_test_trainee.dto.DirectionDto;
import by.krainet.domashkevich_test_trainee.service.DirectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/directions")
@Tag(name = "Direction controller",
        description = "Controller manages directions")
public class DirectionController {

    private final DirectionService service;

    @Operation(summary = "Get all directions",
            description = "Return all directions with filtration by name and pagination")
    @GetMapping()
    public ResponseEntity<List<DirectionDto>> getDirections(
            @Parameter(description = "Filter by direction name") @RequestParam(required = false) String name,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size
    ) {

        return ResponseEntity.ok(service.findAll(name, PageRequest.of(page, size)));
    }

    @Operation(summary = "Get direction by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DirectionDto> getDirectionById(@PathVariable Long id) {
        return service.findById(id)
                .map(directionDto -> ResponseEntity.ok().body(directionDto))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Operation(summary = "Create new direction")
    @SneakyThrows
    @PostMapping()
    public ResponseEntity<DirectionDto> saveDirection(@RequestBody DirectionDto directionDto) {

        DirectionDto result = service.save(directionDto);
        return ResponseEntity.created(new URI("/directions/" + result.getId())).body(result);
    }

    @Operation(summary = "Update direction by ID")
    @PutMapping("/{id}")
    public ResponseEntity<DirectionDto> updateDirection(
            @PathVariable Long id,
            @RequestBody DirectionDto directionDto) {
        return service.update(directionDto, id)
                .map(updateDirectionDto -> ResponseEntity.ok().body(updateDirectionDto))
                .orElseGet(ResponseEntity.notFound()::build);
    }

}
