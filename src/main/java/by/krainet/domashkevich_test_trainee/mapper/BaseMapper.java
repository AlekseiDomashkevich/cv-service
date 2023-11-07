package by.krainet.domashkevich_test_trainee.mapper;

import java.util.List;
import java.util.stream.Collectors;


public interface BaseMapper<D, M> {
    D modelToDto(M k);

    M dtoToModel(D t);

    List<D> listToDto(List<M> list);

    default List<M> dtoToList(List<D> listDto) {
        return listDto.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
