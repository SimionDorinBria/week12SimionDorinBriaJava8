package readfromafileandwritetoanotherfile;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReadFromAFileFilterSortAndWriteToAnotherFileTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        folder.delete();
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testReadFilterSortAndWriteAnotherFile() throws IOException {
        File createdFile = folder.newFile("test.txt");

        BufferedWriter writeFileBuffer = new BufferedWriter(new FileWriter(createdFile));
        writeFileBuffer.write("ion,popescu,2019-01-07");
        writeFileBuffer.newLine();
        writeFileBuffer.write("gheorghe,bucur,2018-05-11");
        writeFileBuffer.newLine();
        writeFileBuffer.write("vasilica,pop,2017-09-09");
        writeFileBuffer.newLine();
        writeFileBuffer.write("marin,andreescu,2011-04-06");
        writeFileBuffer.newLine();
        writeFileBuffer.write("anca,bibiloiu,2009-05-01");
        writeFileBuffer.newLine();
        writeFileBuffer.write("grigore,dej,2000-05-07");
        writeFileBuffer.newLine();
        writeFileBuffer.close();

        ReadFromAFileFilterSortAndWriteToAnotherFile.readFileAndWriteAnotherFile(createdFile.toString(),
                "05", "testOutput.txt");

        Scanner fileReader = new Scanner(new FileReader("testOutput.txt"));
        fileReader.useDelimiter("\\s");
        List<String> linesInFile = new ArrayList<String>();

        while (fileReader.hasNextLine()) {
            linesInFile.add(fileReader.nextLine().trim());
        }

        assertThat(linesInFile.get(0), is("anca,bibiloiu"));
        assertThat(linesInFile.get(1), is("gheorghe,bucur"));
        assertThat(linesInFile.get(2), is("grigore,dej"));
        assertThat(linesInFile.size(), is(3));
    }
}