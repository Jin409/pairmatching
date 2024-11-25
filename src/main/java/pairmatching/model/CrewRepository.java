package pairmatching.model;

import java.util.ArrayList;
import java.util.List;

public class CrewRepository {
    private static List<Crew> crews;

    public CrewRepository() {
        crews = new ArrayList<>();
    }

    public void save(Crew crew) {
        crews.add(crew);
    }
}
