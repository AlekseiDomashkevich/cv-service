package by.krainet.domashkevich_test_trainee.mapper;

import by.krainet.domashkevich_test_trainee.dto.DirectionDto;
import by.krainet.domashkevich_test_trainee.entity.Direction;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface DirectionMapper extends BaseMapper<DirectionDto, Direction> {

}
