import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

class FileReader {
    ArrayList<String> getExpectedListEpisode() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        return (ArrayList<String>) Files.readAllLines(Paths.get(requireNonNull(classLoader.getResource("expectedListEpisode.txt")).getPath()));

    }
}
