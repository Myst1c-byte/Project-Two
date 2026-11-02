import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FASTAExporter {
    private static final int MIN_GENE_LENGTH_NT = 99;

    public static String formatToFasta(String header, String sequence) {
        StringBuilder fasta = new StringBuilder();

        fasta.append(">").append(header).append("\n");

        int lineLength = 60;

        for (int i = 0; i < sequence.length(); i += lineLength) {
            int endIndex = Math.min(i + lineLength, sequence.length());
            fasta.append(sequence, i, endIndex).append("\n");
        }

        return fasta.toString();
    }

    public static void saveToFile(String filename, String header, String sequence) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(formatToFasta(header, sequence));
            System.out.println("Successfully saved FASTA to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public static void exportGenes(List<Ribosome.FoundGene> genes,
                                   String nucleotideFile,
                                   String aminoAcidFile,
                                   String nonsenseFile) {

        for (Ribosome.FoundGene gene : genes) {
            String header = "gene_start=" + gene.startPosition
                    + " length_nt=" + gene.nucleotideSequence.length();

            if (gene.nucleotideSequence.length() >= MIN_GENE_LENGTH_NT) {
                saveToFile(nucleotideFile, header, gene.nucleotideSequence);
                saveToFile(aminoAcidFile, header, gene.aminoAcidSequence);
            } else {
                saveToFile(nonsenseFile, header, gene.nucleotideSequence);
            }
        }
    }
}