package pairmatching;

import pairmatching.controller.MatchingController;
import pairmatching.model.CrewRepository;
import pairmatching.service.CrewService;
import pairmatching.service.PairMatchingService;

public class Application {
    public static void main(String[] args) {
        MatchingController matchingController = new MatchingController(new CrewService(new CrewRepository()),
                new PairMatchingService());
        matchingController.run();
    }
}
