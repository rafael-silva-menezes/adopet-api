package adopet.api.infrastructure.mapper;

import adopet.api.domain.entity.Shelter;
import adopet.api.infrastructure.model.ShelterModel;

import java.util.List;
import java.util.stream.Collectors;

public class ShelterModelMapper {
    public ShelterModel toModel(Shelter shelter) {
        return new ShelterModel(
                shelter.id(),
                shelter.name(),
                shelter.phone(),
                shelter.email(),
                new PetModelMapper().toModelList(shelter.pets())
        );
    }

    public Shelter toEntity(ShelterModel shelterModel) {
        return new Shelter(
                shelterModel.getId(),
                shelterModel.getName(),
                shelterModel.getPhone(),
                shelterModel.getEmail(),
                new PetModelMapper().toEntityList(shelterModel.getPets())
        );
    }

    public List<ShelterModel> toModelList(List<Shelter> shelters) {
        return shelters.stream().map(this::toModel).collect(Collectors.toList());
    }

    public List<Shelter> toEntityList(List<ShelterModel> shelterModels) {
        return shelterModels.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
