package by.krainet.domashkevich_test_trainee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "test_of_candidate")
public class TestOfCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ElementCollection
    @CollectionTable(name = "test_history", joinColumns = @JoinColumn(name = "test_of_candidate_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "assessment")
    private Map<String, Integer> testHistory;
}
