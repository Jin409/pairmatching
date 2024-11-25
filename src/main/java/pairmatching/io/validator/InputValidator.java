package pairmatching.io.validator;

public class InputValidator {
    private static final String PAIR_MATCHING_INPUT_DELIMITER = ",";
    private static final int PAIR_MATCHING_INPUT_VALID_LENGTH = 3;

    public static void validatePairMatchingRequestInput(String input) {
        if (!input.contains(PAIR_MATCHING_INPUT_DELIMITER)) {
            throw new IllegalArgumentException("올바르지 않은 형태입니다.");
        }

        String[] splitInput = input.split(PAIR_MATCHING_INPUT_DELIMITER);

        if (splitInput.length != PAIR_MATCHING_INPUT_VALID_LENGTH) {
            throw new IllegalArgumentException("올바르지 않은 형태입니다.");
        }
    }
}
