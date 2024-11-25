package pairmatching;

import pairmatching.model.CrewRepository;

public class Application {
    public static void main(String[] args) {
        MatchingController matchingController = new MatchingController(new CrewService(new CrewRepository()));
        matchingController.run();
    }
}
