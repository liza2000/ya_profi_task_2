package ru.yandex.profi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.profi.entity.Prize;
import ru.yandex.profi.entity.PromoAction;

import java.util.List;
import java.util.Optional;

public interface PrizeRepository extends JpaRepository<Prize, Long> {
    List<Prize> findAllByAction(PromoAction action);
    Optional<Prize> findByActionAndId(PromoAction action, Long id);

}
