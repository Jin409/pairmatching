package pairmatching;

import handler.InputHandler;
import java.util.List;
import pairmatching.dto.CrewRegisterDto;
import pairmatching.io.Option;

public class MatchingController {

    private final CrewService crewService;

    public MatchingController(CrewService crewService) {
        this.crewService = crewService;
    }

    public void run() {
        List<CrewRegisterDto> crewRegisterDtos = FileReader.readCrews();
        crewService.registerCrews(crewRegisterDtos);
        readOption();
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
}
