package adopet.service.shelter;

import adopet.dto.shelter.GetShelterDto;
import adopet.dto.shelter.ListSheltersDto;
import adopet.dto.shelter.RegisterShelterDto;

import java.util.List;

public interface IShelterService {
    void registerShelter(RegisterShelterDto dto);
    List<ListSheltersDto> listShelters();
    GetShelterDto getShelter(String param);
}
