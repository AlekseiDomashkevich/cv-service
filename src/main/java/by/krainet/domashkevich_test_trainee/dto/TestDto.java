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
public class TestDto {
    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "Name", example = "QA Test")
    private String name;

    @Schema(description = "Description", example = "Test for manual QA")
    private String description;

    @Schema(description = "Directions")
    private List<DirectionDto> directions;
}
