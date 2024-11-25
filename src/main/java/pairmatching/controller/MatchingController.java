package pairmatching.controller;

import pairmatching.handler.ErrorHandler;
import pairmatching.handler.InputHandler;
import java.util.List;
import pairmatching.dto.CrewRegisterDto;
import pairmatching.dto.PairMatchingRequestDto;
import pairmatching.io.FileReader;
import pairmatching.io.Option;
import pairmatching.service.CrewService;
import pairmatching.service.PairMatchingService;

public class MatchingController {

    private final CrewService crewService;
    private final PairMatchingService pairMatchingService;

    public MatchingController(CrewService crewService, PairMatchingService pairMatchingService) {
        this.crewService = crewService;
        this.pairMatchingService = pairMatchingService;
    }

    public void run() {
        List<CrewRegisterDto> crewRegisterDtos = FileReader.readCrews();
        crewService.registerCrews(crewRegisterDtos);
        Option option = readOption();
        if (option.isMatching()) {
            readPairMatchingRequest();
        }
    }


    private Option readOption() {
        while (true) {
            try {
                return InputHandler.readOption();
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }
    }

    private void readPairMatchingRequest() {
        while (true) {
            try {
                PairMatchingRequestDto pairMatchingRequestDto = InputHandler.readPairMatchingRequest();
                pairMatchingService.matchingPairs(pairMatchingRequestDto);
                return;
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }
    }
}
