package handler;

import pairmatching.io.InputView;
import pairmatching.io.Option;
import pairmatching.io.validator.InputValidator;

public class InputHandler {
    public static Option readOption() {
        String rawOption = InputView.readOption();
        return Option.findBySign(rawOption);
    }
}
