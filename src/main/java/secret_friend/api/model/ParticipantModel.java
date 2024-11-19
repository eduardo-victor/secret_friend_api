package secret_friend.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class ParticipantModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupModel groupModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secret_friend_id")
    private ParticipantModel secretFriend;
}
