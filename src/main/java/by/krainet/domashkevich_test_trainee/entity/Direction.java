package by.krainet.domashkevich_test_trainee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@Table(name = "direction")
public class Direction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "directions")
    private List<Candidate> candidates = new ArrayList<>();

    @ManyToMany(mappedBy = "directions")
    private List<Test> tests = new ArrayList<>();


    public void addTest(Test test){
        tests.add(test);
        test.getDirections().add(this);
    }

    public void addCandidate(Candidate candidate){
        candidates.add(candidate);
        candidate.getDirections().add(this);
    }


}
