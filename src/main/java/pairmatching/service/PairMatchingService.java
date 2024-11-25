package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pairmatching.dto.MatchingResultDto;
import pairmatching.dto.PairMatchingRequestDto;
import pairmatching.model.Course;
import pairmatching.model.Crew;
import pairmatching.model.CrewRepository;
import pairmatching.model.Level;
import pairmatching.model.Mission;
import pairmatching.model.PairMatchingHistory;
import pairmatching.model.PairMatchingHistoryRepository;

public class PairMatchingService {

    private static final int MAXIMUM_TRY_COUNT = 3;

    private final PairMatchingHistoryRepository pairMatchingHistoryRepository;
    private final CrewRepository crewRepository;

    public PairMatchingService(PairMatchingHistoryRepository pairMatchingHistoryRepository,
                               CrewRepository crewRepository) {
        this.pairMatchingHistoryRepository = pairMatchingHistoryRepository;
        this.crewRepository = crewRepository;
    }

    public void validatePairMatchingRequestDto(PairMatchingRequestDto pairMatchingRequestDto) {
        Course.findByValue(pairMatchingRequestDto.getCourse());
        Level level = Level.findByValue(pairMatchingRequestDto.getLevel());
        Mission.findByNameAndLevel(pairMatchingRequestDto.getMission(), level);
    }

    public boolean isExists(PairMatchingRequestDto pairMatchingRequestDto) {
        return pairMatchingHistoryRepository.doesHistoryExistsOf(
                Mission.findByName(pairMatchingRequestDto.getMission()),
                Course.findByValue(pairMatchingRequestDto.getCourse()));
    }

    public List<MatchingResultDto> read(PairMatchingRequestDto pairMatchingRequestDto) {
        List<MatchingResultDto> results = new ArrayList<>();

        Mission mission = Mission.findByName(pairMatchingRequestDto.getMission());
        Course course = Course.findByValue(pairMatchingRequestDto.getCourse());

        List<PairMatchingHistory> pairMatchingHistories = pairMatchingHistoryRepository.find(mission, course);
        for (PairMatchingHistory pairMatchingHistory : pairMatchingHistories) {
            List<String> names = pairMatchingHistory.getPair().stream()
                    .map(Crew::getName)
                    .collect(Collectors.toList());
            results.add(new MatchingResultDto(names));
        }
        return results;
    }

    public List<MatchingResultDto> match(PairMatchingRequestDto pairMatchingRequestDto) {
        Mission mission = Mission.findByName(pairMatchingRequestDto.getMission());
        Level level = Level.findByValue(pairMatchingRequestDto.getLevel());
        Course course = Course.findByValue(pairMatchingRequestDto.getCourse());
        List<Crew> crews = crewRepository.findByCourse(course);

        for (int i = 0; i < MAXIMUM_TRY_COUNT; i++) {
            List<List<String>> matchedResults = PairMatcher.match(crews);
            List<List<Crew>> pairs = getPairs(matchedResults);
            if (pairMatchingHistoryRepository.doesHistoryExistsOf(mission, course, pairs)) {
                continue;
            }
            saveHistories(mission, level, course, pairs);
            return makeToDtos(pairs);
        }
        throw new IllegalArgumentException("매칭에 실패했습니다.");
    }

    private void saveHistories(Mission mission, Level level, Course course, List<List<Crew>> pairs) {
        for (List<Crew> pair : pairs) {
            pairMatchingHistoryRepository.save(new PairMatchingHistory(pair, level, mission, course));
        }
    }

    private List<MatchingResultDto> makeToDtos(List<List<Crew>> pairs) {
        List<MatchingResultDto> results = new ArrayList<>();
        for (List<Crew> pair : pairs) {
            List<String> names = pair.stream().map(Crew::getName).collect(Collectors.toList());
            results.add(new MatchingResultDto(names));
        }
        return results;
    }

    private List<List<Crew>> getPairs(List<List<String>> matchedResults) {
        List<List<Crew>> pairs = new ArrayList<>();
        for (List<String> matchedResult : matchedResults) {
            List<Crew> pair = matchedResult.stream().map(crewRepository::findByName).collect(Collectors.toList());
            pairs.add(pair);
        }
        return pairs;
    }

}
