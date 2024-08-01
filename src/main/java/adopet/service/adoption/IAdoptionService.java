package adopet.service.adoption;

import adopet.dto.adoption.ApproveAdoptionDto;
import adopet.dto.adoption.RejectAdoptionDto;
import adopet.dto.adoption.RequestAdoptionDto;
import jakarta.validation.ValidationException;

public interface IAdoptionService {
    void request(RequestAdoptionDto requestAdoptionDto) throws ValidationException;

    void approve(ApproveAdoptionDto approveAdoptionDto);

    void reject(RejectAdoptionDto rejectAdoptionDto);
}
