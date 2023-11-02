package by.krainet.domashkevich_test_trainee.service;

import by.krainet.domashkevich_test_trainee.dto.CandidateDto;
import by.krainet.domashkevich_test_trainee.entity.Candidate;
import by.krainet.domashkevich_test_trainee.mapper.CandidateMapper;
import by.krainet.domashkevich_test_trainee.repository.CandidateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepo candidateRepo;
    private final CandidateMapper candidateMapper;


    public List<CandidateDto> findAll(String name, String lastname, Pageable paging) {
        Page<Candidate> page = null;
        if (name == null && lastname == null) {
            page = candidateRepo.findAll(paging);
        } else if (name != null) {
            page = candidateRepo.findByNameContainingIgnoreCase(name, paging);
        } else {
            page = candidateRepo.findByLastnameContainingIgnoreCase(lastname, paging);
        }
        return candidateMapper.listToDto(page.getContent());
    }

    public CandidateDto findById(Long id) {
        return Optional.of(getById(id)).map(candidateMapper::modelToDto).get();
    }


    public CandidateDto save(CandidateDto candidateDto) {

        return candidateMapper.modelToDto(candidateRepo.save(candidateMapper.dtoToModel(candidateDto)));
    }

    private Candidate getById(Long id) {
        return candidateRepo.findById(id).orElseThrow(() -> new RuntimeException(
                "Candidate with ID:" + id + " not found"));
    }
}
