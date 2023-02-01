package com.golab.swimteach.services;

import com.golab.swimteach.dto.HomeworkListDto;
import com.golab.swimteach.mapper.HomeworkDtoFactory;
import com.golab.swimteach.model.Homework;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.HomeworkRepository;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.golab.swimteach.dto.HomeworkListDto.*;

@Service
public class HomeWorkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final SwimmerRepository swimmerRepository;

    private final HomeworkDtoFactory homeworkDtoFactory = HomeworkDtoFactory.getInstance();

    public HomeWorkServiceImpl(HomeworkRepository homeworkRepository, SwimmerRepository swimmerRepository) {
        this.homeworkRepository = homeworkRepository;
        this.swimmerRepository = swimmerRepository;
    }

    @Override
    public HomeworkListDto getHomeworkList() {
        return null; //todo implement
    }

    @Override
    public HomeworkListDto getHomeworkList(Long swimmerId) {

        Swimmer swimmer = swimmerRepository.findById(swimmerId)
                .orElseThrow(() -> new RuntimeException("Swimmer Not Found")); //todo exceptions

        List<Homework> homeworkList = new ArrayList<>(swimmer.getHomeworkSet());


        homeworkList.sort((h1, h2) -> {
            if (h1.getDateTime().isAfter(h2.getDateTime())) {
                return 1;
            } else if (h1.getDateTime().isBefore(h2.getDateTime())) {
                return -1;
            } else {
                return 0;
            }
        });

        HomeworkListDto homeworkListDto =
                homeworkDtoFactory.createHomeworkList(homeworkList);

        return homeworkListDto;
    }

    @Override
    public HomeworkItemDto createHomework(Long swimmerId, HomeworkItemDto homeworkItemDto) {
        return null; //todo implement
    }
}
