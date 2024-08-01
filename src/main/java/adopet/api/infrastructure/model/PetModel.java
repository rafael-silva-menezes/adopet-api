package adopet.api.infrastructure.model;

import adopet.api.domain.enums.PetType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class PetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "type")
    private PetType type;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "breed")
    private String breed;

    @NotNull
    @Column(name = "age")
    private Integer age;

    @NotBlank
    @Column(name = "color")
    private String color;

    @NotNull
    @Column(name = "weight")
    private Float weight;

    @Column(name = "adopted")
    private Boolean adopted;

    @ManyToOne
    @JsonBackReference("shelter_pets")
    @JoinColumn(name = "shelter_id")
    private ShelterModel shelter;

    @OneToOne(mappedBy = "pet")
    @JsonBackReference("adoption_pets")
    private AdoptionModel adoption;
}
