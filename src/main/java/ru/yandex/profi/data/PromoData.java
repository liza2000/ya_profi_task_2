package ru.yandex.profi.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.profi.entity.Participant;
import ru.yandex.profi.entity.Prize;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PromoData{
    Long id;
    String name;
    String description;

    List<Participant> participants;
    List<Prize> prizes;
}
