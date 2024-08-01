package adopet.api.infrastructure.model;

import adopet.api.domain.enums.AdoptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "adoptions")
public class AdoptionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    private GuardianModel guardian;

    @OneToOne(fetch = FetchType.LAZY)
    private PetModel pet;

    private String reason;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;

    private String justification;

    public void approve() {
        this.status = AdoptionStatus.APPROVED;
    }

    public void reprove(String justification) {
        this.status = AdoptionStatus.FAILED;
        this.justification = justification;
    }
}
