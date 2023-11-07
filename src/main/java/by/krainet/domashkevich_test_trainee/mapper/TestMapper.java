package by.krainet.domashkevich_test_trainee.mapper;

import by.krainet.domashkevich_test_trainee.dto.TestDto;
import by.krainet.domashkevich_test_trainee.entity.Test;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper extends BaseMapper<TestDto, Test>{

}
