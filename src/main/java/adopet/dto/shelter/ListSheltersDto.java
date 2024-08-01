package adopet.dto.shelter;

import adopet.model.Shelter;

public record ListSheltersDto(Long id, String name) {
    public ListSheltersDto(Shelter shelter){
        this(shelter.getId(), shelter.getName());
    }
}
