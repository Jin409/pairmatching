package pairmatching.controller;

import pairmatching.dto.MatchingResultDto;
import pairmatching.handler.ErrorHandler;
import pairmatching.handler.InputHandler;
import java.util.List;
import pairmatching.dto.CrewRegisterDto;
import pairmatching.dto.PairMatchingRequestDto;
import pairmatching.io.sign.AnswerSign;
import pairmatching.io.FileReader;
import pairmatching.io.sign.Option;
import pairmatching.io.view.OutputView;
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

        processing();
    }

    private void processing() {
        while (true) {
            Option option = readOption();
            if (option.meansQuit()) {
                return;
            }

            if (option.isMatching()) {
                processMatching();
            }
        }
    }

    private void processMatching() {
        PairMatchingRequestDto pairMatchingRequestDto = readPairMatchingRequest();

        if (pairMatchingService.isExists(pairMatchingRequestDto)) {
            AnswerSign rematchingAnswerSign = getRematchingAnswerSign();
            if (rematchingAnswerSign.meansTrue()) {
                matchPairs(pairMatchingRequestDto);
            }

            if (rematchingAnswerSign.meansFalse()) {
                processMatching();
            }
            return;
        }

        matchPairs(pairMatchingRequestDto);

        // 이전 내역이 없는 경우

    }

    private void matchPairs(PairMatchingRequestDto pairMatchingRequestDto) {
        List<MatchingResultDto> resultDtos = pairMatchingService.match(pairMatchingRequestDto);
        OutputView.displayMatchedResult(resultDtos);
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
