package by.krainet.domashkevich_test_trainee.mapper;

import by.krainet.domashkevich_test_trainee.dto.TestOfCandidateDto;
import by.krainet.domashkevich_test_trainee.entity.TestOfCandidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestOfCandidateMapper extends BaseMapper<TestOfCandidateDto, TestOfCandidate>{
}
