package by.krainet.domashkevich_test_trainee.mapper;

import by.krainet.domashkevich_test_trainee.dto.CandidateTestDto;
import by.krainet.domashkevich_test_trainee.entity.CandidateTest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateTestMapper extends BaseMapper<CandidateTestDto, CandidateTest>{
}
