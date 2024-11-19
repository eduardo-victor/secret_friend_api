package secret_friend.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secret_friend.api.model.GroupModel;
import secret_friend.api.repository.GroupRepository;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @PostMapping
    public ResponseEntity<GroupModel> createGroup( @Valid @RequestBody GroupModel group){
        GroupModel newGroup = groupRepository.save(group);
        return ResponseEntity.ok(newGroup);
    }
}
