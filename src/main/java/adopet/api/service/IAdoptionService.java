package adopet.api.service;

import adopet.api.dto.ApproveAdoptionDto;
import adopet.api.dto.RejectAdoptionDto;
import adopet.api.dto.RequestAdoptionDto;
import jakarta.validation.ValidationException;

public interface IAdoptionService {
    void request(RequestAdoptionDto requestAdoption) throws ValidationException;

    void approve(ApproveAdoptionDto approveAdoption);

    void reject(RejectAdoptionDto rejectAdoption);
}
