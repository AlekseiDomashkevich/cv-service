package by.krainet.domashkevich_test_trainee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDto {
    private Long id;
    private String name;
    private String description;
    private Set<DirectionDto> directions;


}
