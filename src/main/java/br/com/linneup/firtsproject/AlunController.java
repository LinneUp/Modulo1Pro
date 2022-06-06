package br.com.linneup.firtsproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Alunos")
public class AlunController {

    private final List<Message> Alunos;

    public AlunController() {
        this.Alunos = new ArrayList<>();


    }

    @GetMapping
    public List<Message> findAll(@RequestParam(required = false) String aluno) {
        if (aluno != null) {
            return Alunos.stream().filter(msg -> msg.getAluno()
                    .contains(aluno)).collect(Collectors.toList());

        }
        return Alunos;
    }

    @GetMapping("/{id}")
    public Message findById(@PathVariable("id") Integer id) {
        return this.Alunos.stream().filter(msg -> msg.getId().equals(id))
                .findFirst().orElse(null);
    }


    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody final Message aluno) {
        if (aluno.getId() == null) {
            aluno.setId(Alunos.size() + 1);
        }
        Alunos.add(aluno);
        return new ResponseEntity<>(aluno.getId(), HttpStatus.CREATED);

    }

    public ResponseEntity<Integer> addIdade(@RequestBody final Message aluno) {
        if (aluno.getIdade() == null) {
            aluno.setIdade(Alunos.size() + 10);
        }
        Alunos.add(aluno);
        return new ResponseEntity<>(aluno.getIdade(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody final Message aluno) {
        Alunos.stream().filter(msg -> msg.getId().equals(aluno.getId()))
                .forEach(msg -> msg.setAluno(aluno.getAluno()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Alunos.removeIf(msg -> msg.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
