package adopet.api.domain.entity;


import adopet.api.domain.enums.PetType;

public record Pet(Long id, PetType type, String name, String breed, Integer age, String color, Float weight,
                  Boolean adopted, Shelter shelter, Adoption adoption) {
}
