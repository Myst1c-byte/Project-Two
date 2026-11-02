import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FASTAReader {

    public StringBuilder readSequence() throws FileNotFoundException {

        File file = new File("FASTAfiles\\sequence.fasta");
        Scanner scan = new Scanner(file);


        StringBuilder dna = new StringBuilder();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if (!line.startsWith(">")) {
                dna.append(line);
            }
        }
        scan.close();
        return dna;
    }
}
