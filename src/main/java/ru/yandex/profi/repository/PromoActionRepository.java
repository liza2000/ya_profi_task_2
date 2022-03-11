package ru.yandex.profi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.profi.entity.PromoAction;

public interface PromoActionRepository extends JpaRepository<PromoAction, Long>  {
  }
