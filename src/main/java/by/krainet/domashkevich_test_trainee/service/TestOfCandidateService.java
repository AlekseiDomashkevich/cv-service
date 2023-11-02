package by.krainet.domashkevich_test_trainee.service;

import by.krainet.domashkevich_test_trainee.dto.TestOfCandidateDto;
import by.krainet.domashkevich_test_trainee.entity.TestOfCandidate;
import by.krainet.domashkevich_test_trainee.mapper.TestOfCandidateMapper;
import by.krainet.domashkevich_test_trainee.repository.TestOfCandidateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestOfCandidateService {
    private final TestOfCandidateRepo repo;
    private final TestOfCandidateMapper mapper;


    public List<TestOfCandidateDto> findAll(String testName, String candidateLastname, Pageable pageable) {
        Page<TestOfCandidate> result = repo.findAll(pageable);
        if (testName == null && candidateLastname == null) {
            return mapper.listToDto(result.getContent());
        } else if (testName != null) {
            return mapper.listToDto(result.getContent()
                    .stream()
                    .filter(t -> t.getTest().getName().equalsIgnoreCase(testName))
                    .collect(Collectors.toList()));
        }
        return mapper.listToDto(result.getContent()
                .stream()
                .filter(t -> t.getCandidate().getLastname().equalsIgnoreCase(candidateLastname))
                .collect(Collectors.toList()));
    }

    public TestOfCandidateDto findById(Long id) {
        return Optional.of(getById(id)).map(mapper::modelToDto).get();
    }

    public TestOfCandidateDto save(TestOfCandidateDto testOfCandidate) {
        return mapper.modelToDto(repo.save(mapper.dtoToModel(testOfCandidate)));
    }

    private TestOfCandidate getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new RuntimeException("Test of candidate with ID:" + id + " not found"));
    }


}
