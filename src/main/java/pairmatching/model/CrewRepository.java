package pairmatching.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrewRepository {
    private static List<Crew> crews;

    public CrewRepository() {
        crews = new ArrayList<>();
    }

    public void save(Crew crew) {
        crews.add(crew);
    }

    public List<Crew> findByCourse(Course course) {
        return crews.stream()
                .filter(crew -> crew.isCrewOf(course))
                .collect(Collectors.toList());
    }

    public Crew findByName(String name) {
        return crews.stream()
                .filter(crew -> crew.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 크루입니다."));
    }
}
