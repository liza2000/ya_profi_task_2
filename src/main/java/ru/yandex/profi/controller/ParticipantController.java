package ru.yandex.profi.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.profi.entity.Participant;
import ru.yandex.profi.service.ParticipantService;

import java.util.List;



@RestController
@AllArgsConstructor
@RequestMapping("/promo/{promoId}/participant")
public class ParticipantController {

    ParticipantService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> save(@PathVariable Long promoId,@RequestBody Participant data){
       Long id = service.save(promoId, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long promoId,@PathVariable Long id,@RequestBody Participant data){
        service.update(promoId, id, data);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Participant> get(@PathVariable long promoId, @PathVariable long id){
        Participant participant = service.get(promoId, id);

        return ResponseEntity.ok().body(participant);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Participant>> get(@PathVariable long promoId){
        List<Participant> participants = service.get(promoId);
        return ResponseEntity.status(HttpStatus.OK).body(participants);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long promoId,@PathVariable Long id){
       service.delete(promoId, id);
        return ResponseEntity.noContent().build();
    }

}
