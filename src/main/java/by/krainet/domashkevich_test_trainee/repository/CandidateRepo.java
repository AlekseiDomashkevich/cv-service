package by.krainet.domashkevich_test_trainee.repository;

import by.krainet.domashkevich_test_trainee.entity.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    Page<Candidate> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Candidate> findByLastnameContainingIgnoreCase(String lastname, Pageable pageable);
}
