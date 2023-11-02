package by.krainet.domashkevich_test_trainee.controller;

import by.krainet.domashkevich_test_trainee.dto.CandidateDto;
import by.krainet.domashkevich_test_trainee.service.CandidateService;
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
public class CandidateController {
    private final CandidateService service;

    @GetMapping("/candidates")
    ResponseEntity<List<CandidateDto>> getCandidates(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastname,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok().body(service.findAll(name, lastname, PageRequest.of(page, size, Sort.by(sortBy))));
    }

    @GetMapping("candidate/{id}")
    ResponseEntity<CandidateDto> getCandidateById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping("/candidate")
    @SneakyThrows
    ResponseEntity<CandidateDto> saveCandidate(@RequestBody CandidateDto newCandidateDto) {
        CandidateDto result = service.save(newCandidateDto);
        return ResponseEntity.created(new URI("/candidate" + result.getId())).body(result);
    }

    @PutMapping("/candidate{id}")
    ResponseEntity<CandidateDto> updateCandidate(@RequestBody CandidateDto updateCandidate, @PathVariable Long id) {
        CandidateDto candidate = service.findById(id);
        updateCandidate.setId(candidate.getId());
        return ResponseEntity.ok().body(service.save(updateCandidate));

    }
}
