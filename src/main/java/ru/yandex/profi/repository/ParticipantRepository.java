package ru.yandex.profi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.profi.entity.Participant;
import ru.yandex.profi.entity.PromoAction;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAllByAction(PromoAction action);
    Optional<Participant> findByActionAndId(PromoAction action, Long id);
}
