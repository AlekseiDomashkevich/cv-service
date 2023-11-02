package by.krainet.domashkevich_test_trainee.repository;

import by.krainet.domashkevich_test_trainee.entity.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepo extends JpaRepository<Direction, Long> {
    Page<Direction> findByNameContainingIgnoreCase(String name, Pageable pageable);

}