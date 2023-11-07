package by.krainet.domashkevich_test_trainee.service;

import by.krainet.domashkevich_test_trainee.dto.DirectionDto;
import by.krainet.domashkevich_test_trainee.entity.Direction;
import by.krainet.domashkevich_test_trainee.mapper.BaseMapper;
import by.krainet.domashkevich_test_trainee.repository.DirectionRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectionService {

    private final DirectionRepo repo;
    private final BaseMapper<DirectionDto, Direction> mapper;


    public List<DirectionDto> findAll(String name, Pageable paging) {
        Page<Direction> page = name == null
                ? repo.findAll(paging)
                : repo.findByNameContainingIgnoreCase(name, paging);
        return mapper.listToDto(page.getContent());
    }


    public Optional<DirectionDto> findById(Long id) {
        return repo.findById(id).map(mapper::modelToDto);
    }

    @Transactional
    public DirectionDto save(DirectionDto directionDto) {
        return mapper.modelToDto(repo.save(mapper.dtoToModel(directionDto)));
    }

    @Transactional
    public Optional<DirectionDto> update(DirectionDto object, Long id) {
        return repo.findById(id)
                .map(direction -> {
                    direction.setName(object.getName());
                    direction.setDescription(object.getDescription());
                    return mapper.modelToDto(repo.save(direction));
                });
    }

}
