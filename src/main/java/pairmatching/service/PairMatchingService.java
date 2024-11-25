package pairmatching.service;

import pairmatching.dto.PairMatchingRequestDto;
import pairmatching.model.Course;
import pairmatching.model.Level;
import pairmatching.model.Mission;

public class PairMatchingService {
    public void matchingPairs(PairMatchingRequestDto pairMatchingRequestDto) {
        Course course = Course.findByValue(pairMatchingRequestDto.getCourse());
        Level level = Level.findByValue(pairMatchingRequestDto.getLevel());

        Mission.findByNameAndLevel(pairMatchingRequestDto.getMission(), level);
    }
}
