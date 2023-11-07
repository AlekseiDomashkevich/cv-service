package by.krainet.domashkevich_test_trainee.service;

import by.krainet.domashkevich_test_trainee.dto.CandidateTestDto;
import by.krainet.domashkevich_test_trainee.entity.CandidateTest;
import by.krainet.domashkevich_test_trainee.mapper.CandidateTestMapper;
import by.krainet.domashkevich_test_trainee.repository.CandidateTestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateTestService {
    private final CandidateTestRepo repo;
    private final CandidateTestMapper mapper;


    public List<CandidateTestDto> findAll(String testName, String candidateLastname, Pageable pageable) {
        Page<CandidateTest> result = repo.findAll(pageable);
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

    public Optional<CandidateTestDto> findById(Long id) {
        return repo.findById(id).map(mapper::modelToDto);
    }

    @Transactional
    public CandidateTestDto save(CandidateTestDto testOfCandidate) {
        return mapper.modelToDto(repo.save(mapper.dtoToModel(testOfCandidate)));
    }

    @Transactional
    public Optional<CandidateTestDto> update(CandidateTestDto updateCandidateTestDto, Long id) {
        var updateCandidateTest = mapper.dtoToModel(updateCandidateTestDto);
        return repo.findById(id)
                .map(candidateTest -> {
                    candidateTest.setCandidate(updateCandidateTest.getCandidate());
                    candidateTest.setTest(updateCandidateTest.getTest());
                    candidateTest.setTestHistory(updateCandidateTest.getTestHistory());
                    return mapper.modelToDto(repo.save(candidateTest));
                });
    }
}
