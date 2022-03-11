package ru.yandex.profi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.profi.data.PromoData;
import ru.yandex.profi.entity.PromoAction;
import ru.yandex.profi.entity.Result;
import ru.yandex.profi.service.PromoActionService;

import java.util.List;


@RestController
@RequestMapping("/promo")
@AllArgsConstructor
public class PromoActionController {

    PromoActionService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> save(@RequestBody PromoData data){
        Long id = service.save(data);
         return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable long id,@RequestBody PromoData data){
       service.update(id,data);
         return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PromoData> get(@PathVariable long id){
        PromoData action = service.get(id);
        return ResponseEntity.ok().body(action);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PromoAction>> get(){
       List<PromoAction> dataList = service.get();
        return ResponseEntity.status(HttpStatus.OK).body(dataList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "/{id}/ruffle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Result>> ruffle(@PathVariable long id){
       List<Result> results = service.ruffle(id);
       if (results==null)
           return ResponseEntity.status(HttpStatus.CONFLICT).build();
       return ResponseEntity.ok().body(results);
    }
}
