package br.com.linneup.firtsproject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/olamundo")
public class OlaController {

    private final List<Message>Messages;

    public OlaController() {
        this.Messages = new ArrayList<>();


    }

    @GetMapping
    public List<Message> findAll(@RequestParam(required = false) String message) {
        if (message != null) {
            return Messages.stream().filter(msg -> msg.getMessage()
                    .contains(message)).collect(Collectors.toList());

        }
        return Messages;
    }
    @GetMapping("/{id}")
    public Message findById(@PathVariable("id") Integer id){
        return this.Messages.stream().filter(msg->msg.getId().equals(id))
                .findFirst().orElse(null);
    }


    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody final Message message){
        if (message.getId()==null);{
            message.setId(Messages.size()+ 1);
    }
    Messages.add(message);
        return new ResponseEntity<> (message.getId(), HttpStatus.CREATED);

    }
    @PutMapping
    public ResponseEntity update(@RequestBody final Message message){
        Messages.stream().filter(msg-> msg.getId().equals(message.getId()))
                .forEach(msg->msg.setMessage(message.getMessage()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Messages.removeIf(msg-> msg.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
