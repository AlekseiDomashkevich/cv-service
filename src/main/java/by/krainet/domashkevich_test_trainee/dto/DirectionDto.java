package by.krainet.domashkevich_test_trainee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectionDto {
    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "Name", example = "QA")
    private String name;

    @Schema(description = "Description", example = "Manual QA")
    private String description;


}
