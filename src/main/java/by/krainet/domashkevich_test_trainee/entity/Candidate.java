package by.krainet.domashkevich_test_trainee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_link")
    private String photoLink;

    @Column(name = "cv_link")
    private String cvLink;

    @ManyToMany
    @JoinTable(name = "direction_candidate",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id"))
    private List<Direction> directions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "candidate")
    private List<CandidateTest> candidateTests = new ArrayList<>();

}
