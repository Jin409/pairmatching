package pairmatching.controller;

import pairmatching.handler.ErrorHandler;
import pairmatching.handler.InputHandler;
import java.util.List;
import pairmatching.dto.CrewRegisterDto;
import pairmatching.dto.PairMatchingRequestDto;
import pairmatching.io.AnswerSign;
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

        while (true) {
            Option option = readOption();
            if (option.meansQuit()) {
                return;
            }

            if (option.isMatching()) {
                PairMatchingRequestDto pairMatchingRequestDto = readPairMatchingRequest();
                processMatching(pairMatchingRequestDto);
            }
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

    private PairMatchingRequestDto readPairMatchingRequest() {
        while (true) {
            try {
                PairMatchingRequestDto pairMatchingRequestDto = InputHandler.readPairMatchingRequest();
                pairMatchingService.validatePairMatchingRequestDto(pairMatchingRequestDto);
                return pairMatchingRequestDto;
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }
    }

    private void processMatching(PairMatchingRequestDto pairMatchingRequestDto) {
        if (pairMatchingService.isExists(pairMatchingRequestDto)) {
            AnswerSign rematchingAnswerSign = getRematchingAnswerSign();
            if (rematchingAnswerSign.meansTrue()) {

            }

            if (rematchingAnswerSign.meansFalse()) {
                // 다시 매칭 안할 것이다

            }
        }
    }

    public AnswerSign getRematchingAnswerSign() {
        while (true) {
            try {
                return InputHandler.readRematchingAnswer();
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }
    }
}
