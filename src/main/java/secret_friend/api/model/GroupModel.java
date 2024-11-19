package secret_friend.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class GroupModel {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Group name is needed.")
    @Column(name ="group_name", nullable=false)
    private String groupName;
}
