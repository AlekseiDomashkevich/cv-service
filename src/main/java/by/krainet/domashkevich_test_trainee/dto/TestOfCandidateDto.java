package by.krainet.domashkevich_test_trainee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestOfCandidateDto {
    private Long id;
    private CandidateDto candidate;
    private TestDto test;
    private Map<String, Integer> testHistory;
}
