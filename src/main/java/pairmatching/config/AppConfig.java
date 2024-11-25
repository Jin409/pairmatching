package pairmatching.config;

import pairmatching.controller.MatchingController;
import pairmatching.model.CrewRepository;
import pairmatching.model.PairMatchingHistoryRepository;
import pairmatching.service.CrewService;
import pairmatching.service.PairMatchingService;

public class AppConfig {
    public PairMatchingHistoryRepository pairMatchingHistoryRepository() {
        return new PairMatchingHistoryRepository();
    }

    public CrewRepository crewRepository() {
        return new CrewRepository();
    }

    public PairMatchingService pairMatchingService() {
        return new PairMatchingService(pairMatchingHistoryRepository(), crewRepository());
    }

    public CrewService crewService() {
        return new CrewService(crewRepository());
    }

    public MatchingController matchingController() {
        return new MatchingController(crewService(), pairMatchingService());
    }
}
