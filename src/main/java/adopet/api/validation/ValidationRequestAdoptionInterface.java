package adopet.api.validation;

import adopet.api.dto.RequestAdoptionDto;

public interface ValidationRequestAdoptionInterface {
    void validate(RequestAdoptionDto requestAdoptionDto);
}
