package pairmatching.dto;

public class CrewRegisterDto {
    private final String course;
    private final String name;

    public CrewRegisterDto(String course, String name) {
        this.course = course;
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }
}
