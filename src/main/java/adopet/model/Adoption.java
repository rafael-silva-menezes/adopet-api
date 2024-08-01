package adopet.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "adoptions")
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guardian guardian;

    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String reason;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;

    private String justification;

    public Adoption() {
    }

    public Adoption(Guardian guardian, Pet pet, String reason) {
        this.guardian = guardian;
        this.pet = pet;
        this.reason = reason;
        this.status = AdoptionStatus.WAITING_EVALUATION;
        this.date = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adoption adoption = (Adoption) o;
        return Objects.equals(id, adoption.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public Pet getPet() {
        return pet;
    }

    public String getReason() {
        return reason;
    }

    public AdoptionStatus getStatus() {
        return status;
    }

    public String getJustification() {
        return justification;
    }

    public void approve() {
        this.status = AdoptionStatus.APPROVED;
    }

    public void reprove(String justification) {
        this.status = AdoptionStatus.FAILED;
        this.justification = justification;
    }
}
