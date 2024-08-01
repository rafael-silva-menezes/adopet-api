package adopet.api.domain.entity;

import adopet.api.domain.enums.AdoptionStatus;

import java.time.LocalDateTime;

public record Adoption(Long id, LocalDateTime date, Guardian guardian, Pet pet, String reason, AdoptionStatus status,
                       String justification) {
}
