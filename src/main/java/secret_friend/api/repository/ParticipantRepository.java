package secret_friend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secret_friend.api.model.ParticipantModel;

import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<ParticipantModel, UUID> {
}
