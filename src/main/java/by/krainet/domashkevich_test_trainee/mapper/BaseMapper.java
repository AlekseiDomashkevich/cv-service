package by.krainet.domashkevich_test_trainee.mapper;
import java.util.List;


public interface BaseMapper<D, M> {
    D modelToDto(M k);
    M dtoToModel(D t);
    List<D> listToDto(List<M> list);
    List<M> listToModel(List<D> list);
}
