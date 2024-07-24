package br.com.alura.adopet.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "adoptions")
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @ManyToOne
    @JsonBackReference("guardian_adoptions")
    @JoinColumn(name = "guardian_id")
    private Guardian guardian;

    @NotNull
    @OneToOne
    @JoinColumn(name = "pet_id")
    @JsonManagedReference("adoption_pets")
    private Pet pet;

    @NotBlank
    @Column(name = "reason")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AdoptionStatus status;

    @Column(name = "justification_status")
    private String justificationStatus;

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

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setTutor(Guardian guardian) {
        this.guardian = guardian;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = Adoption.this.reason;
    }

    public AdoptionStatus getStatus() {
        return status;
    }

    public void setStatus(AdoptionStatus status) {
        this.status = status;
    }

    public String getJustifiedStatus() {
        return justificationStatus;
    }

    public void setJustifiedStatus(String justificationStatus) {
        this.justificationStatus = justificationStatus;
    }
}
