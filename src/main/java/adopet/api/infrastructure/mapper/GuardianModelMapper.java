package adopet.api.infrastructure.mapper;

import adopet.api.domain.entity.Guardian;
import adopet.api.infrastructure.model.GuardianModel;

import java.util.List;
import java.util.stream.Collectors;

public class GuardianModelMapper {
    public GuardianModel toModel(Guardian guardian) {
        return new GuardianModel(
                guardian.id(),
                guardian.name(),
                guardian.phone(),
                guardian.email(),
                new AdoptionModelMapper().toModelList(guardian.adoptions())
        );
    }

    public Guardian toEntity(GuardianModel guardianModel) {
        return new Guardian(
                guardianModel.getId(),
                guardianModel.getName(),
                guardianModel.getPhone(),
                guardianModel.getEmail(),
                new AdoptionModelMapper().toEntityList(guardianModel.getAdoptions())
        );
    }

    public List<GuardianModel> toModelList(List<Guardian> guardians) {
        return guardians.stream().map(this::toModel).collect(Collectors.toList());
    }

    public List<Guardian> toEntityList(List<GuardianModel> guardianModels) {
        return guardianModels.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
