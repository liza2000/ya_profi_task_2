package ru.yandex.profi.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.yandex.profi.data.PromoData;
import ru.yandex.profi.entity.Participant;
import ru.yandex.profi.entity.Prize;
import ru.yandex.profi.entity.PromoAction;
import ru.yandex.profi.entity.Result;
import ru.yandex.profi.repository.ParticipantRepository;
import ru.yandex.profi.repository.PrizeRepository;
import ru.yandex.profi.repository.PromoActionRepository;
import ru.yandex.profi.repository.ResultRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class PromoActionService {

    PromoActionRepository promoRepository;
    ResultRepository resultRepository;
    PrizeRepository prizeRepository;
    ParticipantRepository participantRepository;

    public Long save( PromoData data){
        PromoAction promo = promoRepository.save(new PromoAction(0L,data.getName(),data.getDescription()));
        return promo.getId();
    }

    public void update(long id,  PromoData data){
        PromoAction promo = promoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        promo.setName(data.getName());
        promo.setDescription(data.getDescription());
        promoRepository.save(promo);
    }

    public PromoData get(long id){
        PromoAction action = promoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        List<Prize> prizes = prizeRepository.findAllByAction(action);
        List<Participant> participants = participantRepository.findAllByAction(action);
        PromoData data = new PromoData(action.getId(),action.getName(), action.getDescription(),participants,prizes);
        return data;
    }

    public List<PromoAction> get(){
        return promoRepository.findAll();
    }

    public void delete(long id){
        PromoAction promo = promoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        promoRepository.delete(promo);
    }


    public List<Result> ruffle(long id){
        PromoAction promo = promoRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        List<Participant> participants = participantRepository.findAllByAction(promo);
        List<Prize> prizes = prizeRepository.findAllByAction(promo);

        if (participants.size() != prizes.size())
            return null;
        Collections.shuffle(participants);
        Collections.shuffle(prizes);

        ArrayList<Result> results = new ArrayList<>();
        for(int i=0;i<prizes.size();i++)
            results.add(resultRepository.save(new Result(0L,prizes.get(i), participants.get(i))));

        return results;
    }
    
}
