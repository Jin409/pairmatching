package pairmatching;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.dto.CrewRegisterDto;
import pairmatching.model.Course;
import pairmatching.model.Crew;
import pairmatching.model.CrewRepository;

public class CrewService {
    private final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public void registerCrews(List<CrewRegisterDto> crewRegisterDtos) {
        List<Crew> crews = crewRegisterDtos.stream()
                .map(dto -> new Crew(dto.getName(), Course.findByValue(dto.getCourse())))
                .collect(Collectors.toList());
        crews.forEach(crewRepository::save);
    }
}
