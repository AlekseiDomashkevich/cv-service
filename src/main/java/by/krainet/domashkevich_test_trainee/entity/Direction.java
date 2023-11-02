package by.krainet.domashkevich_test_trainee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(exclude = {"candidateSet", "testSet"})
@ToString(exclude = {"candidateSet", "testSet"})
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

//    @ManyToMany
//    @JoinTable(name = "candidate_direction",
//            joinColumns = @JoinColumn(name = "direction_id"),
//            inverseJoinColumns = @JoinColumn(name = "candidate_id"))
//    private Set<Candidate> candidateSet;

//    @ManyToMany
//    @JoinTable(name = "test_direction",
//            joinColumns = @JoinColumn(name="direction_id"),
//            inverseJoinColumns = @JoinColumn(name="test_id"))
//    private Set<Test> tests = new HashSet<>();



}
