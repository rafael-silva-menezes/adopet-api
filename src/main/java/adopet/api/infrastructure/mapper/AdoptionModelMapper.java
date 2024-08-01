package adopet.api.infrastructure.mapper;

import adopet.api.domain.entity.Adoption;
import adopet.api.infrastructure.model.AdoptionModel;

import java.util.List;
import java.util.stream.Collectors;

public class AdoptionModelMapper {
    public AdoptionModel toModel(Adoption adoption) {
        return new AdoptionModel(
                adoption.id(),
                adoption.date(),
                new GuardianModelMapper().toModel(adoption.guardian()),
                new PetModelMapper().toModel(adoption.pet()),
                adoption.reason(),
                adoption.status(),
                adoption.justification()
        );
    }

    public Adoption toEntity(AdoptionModel adoptionModel) {
        return new Adoption(
                adoptionModel.getId(),
                adoptionModel.getDate(),
                new GuardianModelMapper().toEntity(adoptionModel.getGuardian()),
                new PetModelMapper().toEntity(adoptionModel.getPet()),
                adoptionModel.getReason(),
                adoptionModel.getStatus(),
                adoptionModel.getJustification()
        );
    }

    public List<AdoptionModel> toModelList(List<Adoption> adoptions) {
        return adoptions.stream().map(this::toModel).collect(Collectors.toList());
    }

    public List<Adoption> toEntityList(List<AdoptionModel> adoptionModels) {
        return adoptionModels.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
