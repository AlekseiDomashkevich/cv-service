package by.krainet.domashkevich_test_trainee.controller;

import by.krainet.domashkevich_test_trainee.dto.TestOfCandidateDto;
import by.krainet.domashkevich_test_trainee.service.TestOfCandidateService;
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
public class TestOfCandidateController {
    private final TestOfCandidateService service;

    @GetMapping("/tests/candidates")
    ResponseEntity<List<TestOfCandidateDto>> getTestsOfCandidates(
            @RequestParam(required = false) String testName,
            @RequestParam(required = false) String candidateLastname,
            @RequestParam(defaultValue = "test") String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity
                .ok()
                .body(service.findAll(testName, candidateLastname, PageRequest.of(page, size, Sort.by(sortBy))));
    }

    @GetMapping("/test/candidate/{id}")
    ResponseEntity<TestOfCandidateDto> getTestOfCandidatesById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping("/test/candidate")
    @SneakyThrows
    ResponseEntity<TestOfCandidateDto> saveTestOfCandidate(@RequestBody TestOfCandidateDto testOfCandidateDto) {
        TestOfCandidateDto result = service.save(testOfCandidateDto);
        return ResponseEntity.created(new URI("/test/candidate" + result.getId())).body(result);
    }

    @PutMapping("/test/candidate/{id}")
    @SneakyThrows
    ResponseEntity<TestOfCandidateDto> updateTestOfCandidate(
            @RequestBody TestOfCandidateDto updateTestOfCandidateDto,
            @PathVariable Long id) {
        TestOfCandidateDto testOfCandidateDto = service.findById(id);
        updateTestOfCandidateDto.setId(testOfCandidateDto.getId());
        return ResponseEntity.ok().body(service.save(updateTestOfCandidateDto));
    }
}
