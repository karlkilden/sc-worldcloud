import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiAnalyzer extends FrequencyAnalyzer {

    List<String> allLines = new ArrayList<>();
    public List<WordFrequency> loadAll(String path) throws IOException {
        Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .forEach(this::addLines);

        return load(allLines);
    }

    private void addLines(Path p) {
        //System.out.println(p.getFileName());

        try {
            if (Files.isHidden(p) || p.toString().toLowerCase().endsWith(".java") == false)
                return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Stream<String> lines = Files.lines(p, StandardCharsets.UTF_8);
            allLines.addAll(lines.collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
