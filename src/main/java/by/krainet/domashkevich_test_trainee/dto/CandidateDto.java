package by.krainet.domashkevich_test_trainee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateDto {
    private Long id;
    private String name;
    private String surname;
    private String lastname;
    private String description;
    private List<DirectionDto> directions;
}
