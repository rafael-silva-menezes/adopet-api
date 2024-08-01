package adopet.api.application.gateway;

import adopet.api.domain.entity.Shelter;

import java.util.List;

public interface ShelterGateway {
    Shelter create(Shelter shelter);
    List<Shelter> getShelters();
    Shelter getShelter();
}
