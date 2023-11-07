package by.krainet.domashkevich_test_trainee.controller;

import by.krainet.domashkevich_test_trainee.dto.CandidateDto;
import by.krainet.domashkevich_test_trainee.service.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    private final CandidateService service;

    @Operation(summary = "Get list of candidates",
            description = "Return list of candidates. Support sort, pagination and filter by name and lastname.")

    @GetMapping()
    ResponseEntity<List<CandidateDto>> getCandidates(
            @Parameter(description = "Filter by candidate name")
            @RequestParam(required = false) String name,

            @Parameter(description = "Filter by candidate lastname")
            @RequestParam(required = false) String lastname,

            @Parameter(name = "sort", description = "Sort by id, name etc..")
            @RequestParam(defaultValue = "name") String sortBy,

            @Parameter(description = "Page number")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(service.findAll(name, lastname, PageRequest.of(page, size, Sort.by(sortBy))));
    }

    @Operation(summary = "Get candidate by ID",
            description = "Return candidate by ID")
    @GetMapping("/{id}")
    ResponseEntity<CandidateDto> getCandidateById(@PathVariable Long id) {
        return service.findById(id)
                .map(candidateDto -> ResponseEntity.ok().body(candidateDto))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Operation(summary = "Save candidate", description = "Save candidate without photo and cv-file")
    @PostMapping()
    @SneakyThrows
    ResponseEntity<CandidateDto> saveCandidate(@RequestBody CandidateDto candidate) {
        CandidateDto result = service.save(candidate);
        return ResponseEntity.created(new URI("/candidates" + result.getId())).body(result);
    }

    @Operation(summary = "Update candidate", description = "Update candidate by ID")
    @PutMapping("/{id}")
    ResponseEntity<CandidateDto> updateCandidate(@RequestBody CandidateDto candidate, @PathVariable Long id) {
        return service.update(candidate, id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Operation(summary = "Save candidate photo")
    @PostMapping("/{id}/photo")
    ResponseEntity<CandidateDto> uploadCandidatePhoto(@PathVariable Long id, @RequestBody MultipartFile content) {
        return service.uploadPhoto(id, content)
                .map(candidateDto -> ResponseEntity.ok().body(candidateDto))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Operation(summary = "Get candidate proto", description = "Get candidate photo by candidate ID")
    @GetMapping("/{id}/photo")
    @SneakyThrows
    ResponseEntity<Resource> getCandidatePhoto(@PathVariable Long id, HttpServletRequest request) {
        var resource = service.getPhoto(id);
        if (resource.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            var photo = resource.get();
            var contentType = request.getServletContext().getMimeType(photo.getFile().getAbsolutePath());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .contentLength(photo.contentLength())
                    .body(photo);
        }
    }

    @Operation(summary = "Save candidate cv-file", description = "Save candidate cv-file by candidate ID")
    @PostMapping("/{id}/doc")
    ResponseEntity<CandidateDto> uploadCandidateDocument(@PathVariable Long id, @RequestBody MultipartFile content) {
        return service.uploadDocument(id, content)
                .map(candidateDto -> ResponseEntity.ok().body(candidateDto))
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Operation(summary = "Get candidate cv-file", description = "Get candidate cv-file by candidate ID")
    @SneakyThrows
    @GetMapping("/{id}/doc")
    ResponseEntity<Resource> getCandidateDocument(@PathVariable Long id, HttpServletRequest request) {
        var resource = service.getDocument(id);
        if (resource.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            var doc = resource.get();
            var contentType = request.getServletContext().getMimeType(doc.getFile().getAbsolutePath());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getFilename() + "\"")
                    .contentLength(doc.contentLength())
                    .body(doc);
        }
    }
}
