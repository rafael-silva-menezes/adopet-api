package adopet.validation;

import adopet.dto.adoption.RequestAdoptionDto;

public interface IValidationRequestAdoption {
    void validate(RequestAdoptionDto requestAdoptionDto);
}
