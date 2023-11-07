package by.krainet.domashkevich_test_trainee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "candidate_test")
public class CandidateTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ElementCollection
    @CollectionTable(name = "test_history", joinColumns = @JoinColumn(name = "candidate_test_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "assessment")
    private Map<LocalDate, Integer> testHistory = new HashMap<>();

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
        this.candidate.getCandidateTests().add(this);
    }

    public void setTest(Test test){
        this.test = test;
        this.test.getCandidateTests().add(this);
    }
}
