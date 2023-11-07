package by.krainet.domashkevich_test_trainee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateTestDto {
    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "Candidate")
    private CandidateDto candidate;

    @Schema(description = "Test")
    private TestDto test;

    @Schema(description = "Test history", example = "2023-10-11 - 100")
    private Map<LocalDate, Integer> testHistory = new HashMap<>();
}

