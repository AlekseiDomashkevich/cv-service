package by.krainet.domashkevich_test_trainee.repository;

import by.krainet.domashkevich_test_trainee.entity.CandidateTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateTestRepo extends JpaRepository<CandidateTest, Long> {

}
