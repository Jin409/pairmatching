package pairmatching.service;

import pairmatching.dto.PairMatchingRequestDto;
import pairmatching.model.Course;
import pairmatching.model.Level;
import pairmatching.model.Mission;
import pairmatching.model.PairMatchingHistoryRepository;

public class PairMatchingService {

    private final PairMatchingHistoryRepository pairMatchingHistoryRepository;

    public PairMatchingService(PairMatchingHistoryRepository pairMatchingHistoryRepository) {
        this.pairMatchingHistoryRepository = pairMatchingHistoryRepository;
    }

    public void validatePairMatchingRequestDto(PairMatchingRequestDto pairMatchingRequestDto) {
        Course course = Course.findByValue(pairMatchingRequestDto.getCourse());
        Level level = Level.findByValue(pairMatchingRequestDto.getLevel());
        Mission.findByNameAndLevel(pairMatchingRequestDto.getMission(), level);
    }

    public boolean isExists(PairMatchingRequestDto pairMatchingRequestDto) {
        return pairMatchingHistoryRepository.doesHistoryExistsOf(
                Mission.findByName(pairMatchingRequestDto.getMission()));
    }

}
