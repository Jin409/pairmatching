package pairmatching.controller;

import java.util.function.Supplier;
import pairmatching.dto.MatchingResultDto;
import pairmatching.handler.ErrorHandler;
import pairmatching.handler.InputHandler;
import java.util.List;
import pairmatching.dto.CrewRegisterDto;
import pairmatching.dto.PairMatchingRequestDto;
import pairmatching.io.CrewFileReader;
import pairmatching.io.sign.AnswerSign;
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
        readyToMatch();
        process();
    }

    private void readyToMatch() {
        List<CrewRegisterDto> crewRegisterDtos = CrewFileReader.readCrews();
        crewService.registerCrews(crewRegisterDtos);
    }

    private void process() {
        while (true) {
            Option option = readOption();
            if (option.meansQuit()) {
                return;
            }

            if (option.isMatching()) {
                processMatching();
            }

            if (option.meansRead()) {
                processReading();
            }

            if (option.meansReset()) {
                processReset();
            }
        }
    }

    private void processReset() {
        pairMatchingService.resetAllPairs();
        OutputView.noticeResetCompleted();
    }

    private void processMatching() {
        PairMatchingRequestDto pairMatchingRequestDto = readPairMatchingRequest();

        if (pairMatchingService.doesExist(pairMatchingRequestDto)) {
            handleExistedProcessHistory(pairMatchingRequestDto);
            return;
        }
        matchPairs(pairMatchingRequestDto);
    }

    private void handleExistedProcessHistory(PairMatchingRequestDto pairMatchingRequestDto) {
        AnswerSign rematchingAnswerSign = getRematchingAnswerSign();

        if (rematchingAnswerSign.meansTrue()) {
            pairMatchingService.resetSelectedPairs(pairMatchingRequestDto);
            matchPairs(pairMatchingRequestDto);
        }

        if (rematchingAnswerSign.meansFalse()) {
            processMatching();
        }
    }

    private void processReading() {
        PairMatchingRequestDto pairMatchingRequestDto = readPairMatchingRequest();

        if (pairMatchingService.doesExist(pairMatchingRequestDto)) {
            OutputView.displayMatchedResult(pairMatchingService.read(pairMatchingRequestDto));
            return;
        }
        ErrorHandler.handle(new IllegalArgumentException("매칭 이력이 없습니다."));
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

    public AnswerSign getRematchingAnswerSign() {
        while (true) {
            try {
                return InputHandler.readRematchingAnswer();
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }
    }

    private PairMatchingRequestDto readPairMatchingRequest() {
        return retryOnInvalidInput(() -> {
            PairMatchingRequestDto pairMatchingRequestDto = InputHandler.readPairMatchingRequest();
            pairMatchingService.validatePairMatchingRequestDto(pairMatchingRequestDto);
            return pairMatchingRequestDto;
        });
    }

    private <T> T retryOnInvalidInput(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                ErrorHandler.handle(e);
            }
        }
    }
}
