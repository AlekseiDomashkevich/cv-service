package by.krainet.domashkevich_test_trainee.controller;

import by.krainet.domashkevich_test_trainee.dto.TestDto;
import by.krainet.domashkevich_test_trainee.service.TestService;
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
@RequestMapping("/api/v1/tests")
@Tag(name = "Test controller",
        description = "Controller manages tests")
public class TestController {

    private final TestService service;

    @Operation(summary = "Get all tests with paging and filtration by name")
    @GetMapping()
    public ResponseEntity<List<TestDto>> getTests(
            @Parameter(description = "Filter by name") @RequestParam(required = false) String name,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.findAll(name, PageRequest.of(page, size)));
    }

    @Operation(summary = "Get test by ID")
    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable Long id) {
        return service.findById(id)
                .map(test -> ResponseEntity.ok().body(test))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Operation(summary = "Save new test")
    @SneakyThrows
    @PostMapping()
    public ResponseEntity<TestDto> saveTest(@RequestBody TestDto testDto) {
        TestDto result = service.save(testDto);
        return ResponseEntity.created(new URI("/test/" + result.getId())).body(result);
    }

    @Operation(summary = "Update test by ID")
    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<TestDto> updateTest(@RequestBody TestDto testDto, @PathVariable Long id) {
        return service.update(testDto, id)
                .map(test -> ResponseEntity.ok().body(test))
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
