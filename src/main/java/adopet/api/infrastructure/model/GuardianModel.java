package adopet.api.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guardians")
public class GuardianModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
    @Column(name = "phone")
    private String phone;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "guardian")
    @JsonManagedReference("guardian_adoptions")
    private List<AdoptionModel> adoptions;
}
