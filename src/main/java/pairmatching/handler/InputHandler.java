package pairmatching.handler;

import pairmatching.dto.PairMatchingRequestDto;
import pairmatching.io.InputView;
import pairmatching.io.Option;
import pairmatching.io.validator.InputValidator;

public class InputHandler {
    private static final String DELIMITER = ",";

    public static Option readOption() {
        String rawOption = InputView.readOption();
        return Option.findBySign(rawOption);
    }

    public static PairMatchingRequestDto readPairMatchingRequest() {
        String rawRequestInput = InputView.readPairMatchingRequest();
        InputValidator.validatePairMatchingRequestInput(rawRequestInput);
        String[] splitInput = rawRequestInput.split(DELIMITER);
        return new PairMatchingRequestDto(splitInput[0].trim(), splitInput[1].trim(), splitInput[2].trim());
    }
}
