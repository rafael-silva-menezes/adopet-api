package adopet.api.domain.entity;

import java.util.List;

public record Guardian(Long id, String name, String phone, String email, List<Adoption> adoptions) {
}
