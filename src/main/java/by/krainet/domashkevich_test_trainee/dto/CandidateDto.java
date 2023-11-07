package by.krainet.domashkevich_test_trainee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateDto {
    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "Name", example = "Ivan")
    private String name;

    @Schema(description = "Surname", example = "Ivanovich")
    private String surname;

    @Schema(description = "Lastname", example = "Ivanenko")
    private String lastname;

    @Schema(description = "Description", example = "Junior QA")
    private String description;

    @Schema(description = "Directions")
    private List<DirectionDto> directions;
}
