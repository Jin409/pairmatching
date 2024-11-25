package pairmatching.io;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import pairmatching.dto.CrewRegisterDto;
import pairmatching.handler.ErrorHandler;

public class FileReader {

    private static final String PATH_OF_BACKEND_CREW_NAMES = "src/main/resources/backend-crew.md";
    private static final String PATH_OF_FRONTEND_CREW_NAMES = "src/main/resources/frontend-crew.md";

    private static List<String> readMarkdownFile(String path) {
        List<String> result = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                result.add(name);
            }
            return result;
        } catch (Exception e) {
            ErrorHandler.handle(e);
        }
        return result;
    }

    private static List<CrewRegisterDto> readBackendCrews() {
        List<String> backendCrewNames = readMarkdownFile(PATH_OF_BACKEND_CREW_NAMES);
        return backendCrewNames.stream().map(name -> new CrewRegisterDto("백엔드", name)).collect(Collectors.toList());
    }

    private static List<CrewRegisterDto> readFrontendCrews() {
        List<String> frontendCrewNames = readMarkdownFile(PATH_OF_FRONTEND_CREW_NAMES);
        return frontendCrewNames.stream().map(name -> new CrewRegisterDto("프론트엔드", name)).collect(Collectors.toList());
    }

    public static List<CrewRegisterDto> readCrews() {
        List<CrewRegisterDto> crews = new ArrayList<>();
        crews.addAll(readBackendCrews());
        crews.addAll(readFrontendCrews());
        return crews;
    }
}