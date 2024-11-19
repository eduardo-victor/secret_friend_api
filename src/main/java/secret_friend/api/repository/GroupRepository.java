package secret_friend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secret_friend.api.model.GroupModel;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<GroupModel, UUID> {
}
