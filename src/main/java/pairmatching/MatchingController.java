package pairmatching;

import java.io.File;
import java.util.List;
import pairmatching.dto.CrewRegisterDto;

public class MatchingController {

    private final CrewService crewService;

    public MatchingController(CrewService crewService) {
        this.crewService = crewService;
    }

    public void run() {
        List<CrewRegisterDto> crewRegisterDtos = FileReader.readCrews();
        crewService.registerCrews(crewRegisterDtos);
    }
}
