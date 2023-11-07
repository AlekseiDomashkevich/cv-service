package by.krainet.domashkevich_test_trainee.service;


import by.krainet.domashkevich_test_trainee.dto.TestDto;
import by.krainet.domashkevich_test_trainee.entity.Test;
import by.krainet.domashkevich_test_trainee.mapper.DirectionMapper;
import by.krainet.domashkevich_test_trainee.mapper.TestMapper;
import by.krainet.domashkevich_test_trainee.repository.TestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TestService {
    private final TestRepo testRepo;
    private final TestMapper testMapper;
    private final DirectionMapper directionMapper;


    public List<TestDto> findAll(String name, Pageable paging) {
        Page<Test> page = name == null
                ? testRepo.findAll(paging)
                : testRepo.findByNameContainingIgnoreCase(name, paging);
        return testMapper.listToDto(page.getContent());
    }


    public Optional<TestDto> findById(Long id) {
        return testRepo.findById(id).map(testMapper::modelToDto);
    }

    @Transactional
    public TestDto save(TestDto testDto) {
        var directions = directionMapper.dtoToList(testDto.getDirections());
        Test result = testMapper.dtoToModel(testDto);
        result.setDirections(directions);
        return testMapper.modelToDto(testRepo.save(result));
    }
    @Transactional
    public Optional<TestDto> update(TestDto object, Long id) {
        return testRepo.findById(id)
                .map(test -> {
                    test.setName(object.getName());
                    test.setDescription(object.getDescription());
                    test.setDirections(object.getDirections()
                            .stream()
                            .map(directionMapper::dtoToModel)
                            .collect(Collectors.toList()));
                    return testMapper.modelToDto(testRepo.save(test));
                });
    }
}
