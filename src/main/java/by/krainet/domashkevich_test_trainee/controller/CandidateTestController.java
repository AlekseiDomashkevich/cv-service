package by.krainet.domashkevich_test_trainee.controller;

import by.krainet.domashkevich_test_trainee.dto.CandidateTestDto;
import by.krainet.domashkevich_test_trainee.service.CandidateTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tests/candidates")
@Tag(name = "Candidate test controller",
        description = "Controller manages tests of candidate")
public class CandidateTestController {
    private final CandidateTestService service;

    @Operation(summary = "Get all tests of all candidates",
            description = "Endpoint return all tests of all candidates. Supporting sorting, filtration and pagination")
    @GetMapping()
    ResponseEntity<List<CandidateTestDto>> getAll(
            @Parameter(description = "Filter by test name")
            @RequestParam(required = false) String testName,
            @Parameter(description = "Filter by candidate lastname")
            @RequestParam(required = false) String candidateLastname,
            @Parameter(description = "Sort by value")
            @RequestParam(defaultValue = "test") String sortBy,
            @Parameter(description = "Page number")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity
                .ok()
                .body(service.findAll(testName, candidateLastname, PageRequest.of(page, size, Sort.by(sortBy))));
    }

    @Operation(summary = "Get test of candidate by ID")
    @GetMapping("/{id}")
    ResponseEntity<CandidateTestDto> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(candidateTestDto -> ResponseEntity.ok().body(candidateTestDto))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Operation(summary = "Save test of candidate")
    @PostMapping()
    @SneakyThrows
    ResponseEntity<CandidateTestDto> save(@RequestBody CandidateTestDto testOfCandidateDto) {
        CandidateTestDto result = service.save(testOfCandidateDto);
        return ResponseEntity.created(new URI("/tests/candidates" + result.getId())).body(result);
    }

    @Operation(summary = "Update test of candidate by ID")
    @PutMapping("/{id}")
    @SneakyThrows
    ResponseEntity<CandidateTestDto> update(
            @RequestBody CandidateTestDto candidateTestDto,
            @PathVariable Long id) {

        return service.update(candidateTestDto, id)
                .map(updateCandidateTestDto -> ResponseEntity.ok().body(updateCandidateTestDto))
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
