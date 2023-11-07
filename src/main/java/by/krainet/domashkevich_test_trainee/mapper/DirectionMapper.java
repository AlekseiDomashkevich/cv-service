package by.krainet.domashkevich_test_trainee.mapper;

import by.krainet.domashkevich_test_trainee.dto.DirectionDto;
import by.krainet.domashkevich_test_trainee.entity.Direction;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface DirectionMapper extends BaseMapper<DirectionDto, Direction> {

}
