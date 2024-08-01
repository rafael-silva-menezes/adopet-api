package adopet.api.domain.entity;

import java.util.List;

public record Shelter(Long id, String name, String phone, String email, List<Pet> pets) {
}
