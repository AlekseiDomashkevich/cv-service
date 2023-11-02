package by.krainet.domashkevich_test_trainee.service;

import by.krainet.domashkevich_test_trainee.dto.TestDto;
import by.krainet.domashkevich_test_trainee.entity.Test;
import by.krainet.domashkevich_test_trainee.mapper.TestMapper;
import by.krainet.domashkevich_test_trainee.repository.TestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TestService {
    private final TestRepo repo;
    private final TestMapper testMapper;


    public List<TestDto> findAll(String name, Pageable paging) {
        Page<Test> page = name == null
                ? repo.findAll(paging)
                : repo.findByNameContainingIgnoreCase(name, paging);

        return testMapper.listToDto(page.getContent());
    }


    public TestDto findById(Long id) {
        return Optional.of(getById(id)).map(testMapper::modelToDto).get();
    }


    public TestDto save(TestDto testDto) {

        return testMapper.modelToDto(repo.save(testMapper.dtoToModel(testDto)));
    }

    private Test getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException(
                "Direction with ID:" + id + " not found"));
    }
}
