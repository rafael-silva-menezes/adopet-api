package adopet.dto.shelter;

import adopet.model.Shelter;

public record GetShelterDto(Long id, String name, String phone, String email) {
    public GetShelterDto(Shelter shelter) {
        this(shelter.getId(), shelter.getName(), shelter.getPhone(), shelter.getEmail());
    }
}
