package adopet.api.validation;

import adopet.api.dto.RequestAdoptionDto;

public interface IValidationRequestAdoption {
    void validate(RequestAdoptionDto requestAdoptionDto);
}
