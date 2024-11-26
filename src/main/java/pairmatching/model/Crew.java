package pairmatching.model;

public class Crew {
    private final String name;
    private final Course course;

    public Crew(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public boolean isCrewOf(Course course) {
        return this.course.equals(course);
    }
}
