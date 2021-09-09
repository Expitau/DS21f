import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.*;

// https://gist.github.com/ragklaat/28dad2f3346ae11e6a6b

class MainPolyTest{
  private final ByteArrayOutputStream output = new ByteArrayOutputStream();

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(output));
  }

  @AfterEach
  public void cleanUpStreams() {
    System.setOut(null);
  }

  @Test
  void main() throws IOException{
    String fileNo = "01";
    String inputFile = "./public/test" + fileNo;
    String outputFile = "./public/test" + fileNo + ".out";

    Path outputPath = Paths.get(outputFile);
    String expectedOut = Files.readString(outputPath);

    MainPoly.main(new String[]{inputFile});

    assertEquals(expectedOut, output.toString());
  }
}