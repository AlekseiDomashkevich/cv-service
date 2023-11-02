package by.krainet.domashkevich_test_trainee.repository;

import by.krainet.domashkevich_test_trainee.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
    Page<Test> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
