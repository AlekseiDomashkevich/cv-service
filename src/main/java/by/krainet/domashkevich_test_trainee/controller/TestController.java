package by.krainet.domashkevich_test_trainee.controller;

import by.krainet.domashkevich_test_trainee.dto.TestDto;
import by.krainet.domashkevich_test_trainee.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @GetMapping("/tests")
    public ResponseEntity<List<TestDto>> getTests(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(service.findAll(name, PageRequest.of(page, size)));
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @SneakyThrows
    @PostMapping("/test")
    public ResponseEntity<TestDto> saveTest(@RequestBody TestDto testDto) {
        TestDto result = service.save(testDto);
        return ResponseEntity.created(new URI("/test/" + result.getId())).body(result);
    }

    @SneakyThrows
    @PutMapping("/test/{id}")
    public ResponseEntity<TestDto> updateTest(@RequestBody TestDto newTestDto, @PathVariable Long id) {
        TestDto result = service.findById(id);
        result.setName(newTestDto.getName());
        result.setDescription(newTestDto.getDescription());
        result.setDirections(newTestDto.getDirections());
        return ResponseEntity.ok().body(service.save(result));
    }


}
