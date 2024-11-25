package pairmatching;

import pairmatching.config.AppConfig;
import pairmatching.controller.MatchingController;
import pairmatching.model.CrewRepository;
import pairmatching.service.CrewService;
import pairmatching.service.PairMatchingService;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MatchingController matchingController = appConfig.matchingController();
        matchingController.run();
    }
}
