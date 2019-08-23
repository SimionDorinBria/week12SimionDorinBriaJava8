package readfromafileandwritetoanotherfile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Paths.get;

public class ReadFromAFileFilterSortAndWriteToAnotherFile {
    public static void readFileAndWriteAnotherFile(String sourceFile, String targetMonth, String outputFile)
            throws IOException {

        try (
                Stream<String> lines = Files.lines(get(sourceFile));) {

            Stream<String> words =
                    lines.filter(line -> line.contains("-" + targetMonth + "-"))
                            .map(line -> line.replaceAll(",\\d{4}-\\d{2}-\\d{2}", ""))
                            .sorted();

            Files.write(get(outputFile), words.collect(Collectors.toList()));
        }
    }
}