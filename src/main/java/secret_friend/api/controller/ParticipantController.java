package secret_friend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secret_friend.api.model.GroupModel;
import secret_friend.api.model.ParticipantModel;
import secret_friend.api.repository.GroupRepository;
import secret_friend.api.repository.ParticipantRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping("/{groupId}")
    public ResponseEntity<ParticipantModel>addParticipant(@PathVariable UUID groupId, @RequestBody ParticipantModel participant){
        Optional<GroupModel> group = groupRepository.findById(groupId);

        if (group.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        participant.setGroupModel(group.get());
        ParticipantModel newParticipant = participantRepository.save(participant);
        return ResponseEntity.ok(newParticipant);
    }
}
