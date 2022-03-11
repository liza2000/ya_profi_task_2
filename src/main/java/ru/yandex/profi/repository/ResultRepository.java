package ru.yandex.profi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.profi.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
