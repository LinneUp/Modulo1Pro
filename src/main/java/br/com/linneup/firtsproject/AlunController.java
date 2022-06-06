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
    public List<Message> findAll(@RequestParam(required = false) String aluno, Integer id, Integer idade) {
        if (aluno != null) {
            return Alunos.stream().filter(aln -> aln.getAluno()
                    .contains(aluno)).collect(Collectors.toList());

        } else if (id != null) {
            return Alunos.stream().filter(ida -> ida.getId()
                    .equals(id)).collect(Collectors.toList());

        } else if (idade != null) {
            return Alunos.stream().filter(idades -> idades.getIdade()
                    .equals(idade)).collect(Collectors.toList());
        }
        return Alunos;
    }


    @GetMapping("/{id}")
    public Message findById(@PathVariable("id") Integer id) {
        return this.Alunos.stream().filter(fiid -> fiid.getId().equals(id))
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
            aluno.setIdade(Alunos.size() + 1);
        }
        Alunos.add(aluno);
        return new ResponseEntity<>(aluno.getIdade(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody final Message aluno) {
        Alunos.stream().filter(upd -> upd.getId().equals(aluno.getId()))
                .forEach(upd -> upd.setAluno(aluno.getAluno()));
        Alunos.stream().filter(upda -> upda.getIdade().equals(aluno.getIdade()))
                .forEach(upd -> upd.setIdade(aluno.getIdade()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Alunos.removeIf(del -> del.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{idade}")
    public ResponseEntity deleteIdade(@PathVariable("idade") Integer idade) {
        Alunos.removeIf(dele -> dele.getIdade().equals(idade));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
