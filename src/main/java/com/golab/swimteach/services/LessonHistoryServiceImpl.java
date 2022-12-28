package com.golab.swimteach.services;

import com.golab.swimteach.dto.LessonHistoryDto;
import com.golab.swimteach.dto.LessonHistoryDtoFactory;
import com.golab.swimteach.model.Lesson;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonHistoryServiceImpl implements LessonService {

    private final SwimmerRepository swimmerRepository;

    private final LessonHistoryDtoFactory lessonHistoryDtoFactory = LessonHistoryDtoFactory.getInstance();

    public LessonHistoryServiceImpl(SwimmerRepository swimmerRepository) {
        this.swimmerRepository = swimmerRepository;
    }

    @Override
    public LessonHistoryDto getLessonHistory() {
        return lessonHistoryDtoFactory.createLessonHistoryDto(List.of());
    }

    @Override
    public LessonHistoryDto getLessonHistory(Long swimmerId) {

        Swimmer swimmer = swimmerRepository.findById(swimmerId)
                .orElseThrow(() -> new RuntimeException("Swimmer Not Found")); //todo exceptions

        List<Lesson> lessonList = new ArrayList<>(swimmer.getLessonSet());

        lessonList.sort((l1, l2) -> {
            if (l1.getDateTime().isAfter(l2.getDateTime())) {
                return 1;
            } else if (l1.getDateTime().isBefore(l2.getDateTime())) {
                return -1;
            } else {
                return 0;
            }
        });

        LessonHistoryDto lessonHistoryDto =
                lessonHistoryDtoFactory.createLessonHistoryDto(lessonList);

        return lessonHistoryDto;
    }
}
