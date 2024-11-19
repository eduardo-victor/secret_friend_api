package secret_friend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secret_friend.api.model.ParticipantModel;
import secret_friend.api.repository.ParticipantRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/raffles")
public class RaffleController {

    @Autowired
    private ParticipantRepository participantRepository;

    @PostMapping("/{groupId}")
    public ResponseEntity<?> generateMatch(@PathVariable UUID groupId) {

        List<ParticipantModel> participants = new ArrayList<>(
                participantRepository.findAll().stream()
                        .filter(p -> p.getGroupModel().getId().equals(groupId))
                        .toList()
        );

        if (participants.size() < 2) {
            return ResponseEntity.badRequest().body("The group must have at least 2 people.");
        }

        Collections.shuffle(participants);

        for (int i = 0; i < participants.size(); i++) {
            ParticipantModel participant = participants.get(i);
            ParticipantModel secretFriend = participants.get((i + 1) % participants.size());
            participant.setSecretFriend(secretFriend);
        }

        participantRepository.saveAll(participants);

        return ResponseEntity.ok("Draw carried out successfully!");
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<List<String>> raffleConsult(@PathVariable UUID groupId) {
        List<ParticipantModel> participants = new ArrayList<>(
                participantRepository.findAll().stream()
                        .filter(p -> p.getGroupModel().getId().equals(groupId))
                        .toList()
        );

        List<String> results = participants.stream()
                .map(p -> p.getName() + " -> " +
                        (p.getSecretFriend() != null ? p.getSecretFriend().getName() : "No match yet"))
                .toList();

        return ResponseEntity.ok(results);
    }

}
