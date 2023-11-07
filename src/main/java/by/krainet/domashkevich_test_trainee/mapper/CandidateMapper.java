package by.krainet.domashkevich_test_trainee.mapper;

import by.krainet.domashkevich_test_trainee.dto.CandidateDto;
import by.krainet.domashkevich_test_trainee.entity.Candidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateMapper extends BaseMapper<CandidateDto, Candidate> {

}
