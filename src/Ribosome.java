import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ribosome {
    private static final Map<String, String> aA = new HashMap<>();

    static {
        aA.put("UUU", "F"); aA.put("UUC", "F"); aA.put("UUA", "L"); aA.put("UUG", "L");
        aA.put("CUU", "L"); aA.put("CUC", "L"); aA.put("CUA", "L"); aA.put("CUG", "L");
        aA.put("AUU", "I"); aA.put("AUC", "I"); aA.put("AUA", "I"); aA.put("AUG", "M");
        aA.put("GUU", "V"); aA.put("GUC", "V"); aA.put("GUA", "V"); aA.put("GUG", "V");
        aA.put("UCU", "S"); aA.put("UCC", "S"); aA.put("UCA", "S"); aA.put("UCG", "S");
        aA.put("CCU", "P"); aA.put("CCC", "P"); aA.put("CCA", "P"); aA.put("CCG", "P");
        aA.put("ACU", "T"); aA.put("ACC", "T"); aA.put("ACA", "T"); aA.put("ACG", "T");
        aA.put("GCU", "A"); aA.put("GCC", "A"); aA.put("GCA", "A"); aA.put("GCG", "A");
        aA.put("UAU", "Y"); aA.put("UAC", "Y"); aA.put("UAA", "STOP"); aA.put("UAG", "STOP");
        aA.put("CAU", "H"); aA.put("CAC", "H"); aA.put("CAA", "Q"); aA.put("CAG", "Q");
        aA.put("AAU", "N"); aA.put("AAC", "N"); aA.put("AAA", "K"); aA.put("AAG", "K");
        aA.put("GAU", "D"); aA.put("GAC", "D"); aA.put("GAA", "E"); aA.put("GAG", "E");
        aA.put("UGU", "C"); aA.put("UGC", "C"); aA.put("UGA", "STOP"); aA.put("UGG", "W");
        aA.put("CGU", "R"); aA.put("CGC", "R"); aA.put("CGA", "R"); aA.put("CGG", "R");
        aA.put("AGU", "S"); aA.put("AGC", "S"); aA.put("AGA", "R"); aA.put("AGG", "R");
        aA.put("GGU", "G"); aA.put("GGC", "G"); aA.put("GGA", "G"); aA.put("GGG", "G");
    }

    public String transcription(String input) {
        return input.replace('T', 'U');
    }

    public static class FoundGene {
        public final int startPosition;
        public final String nucleotideSequence;
        public final String aminoAcidSequence;

        public FoundGene(int startPosition,
                         String nucleotideSequence,
                         String aminoAcidSequence) {
            this.startPosition = startPosition;
            this.nucleotideSequence = nucleotideSequence;
            this.aminoAcidSequence = aminoAcidSequence;
        }
    }

    public static List<FoundGene> translation(String rna) {
        List<FoundGene> genesFound = new ArrayList<>();

        if (rna == null || rna.length() < 3) {
            return genesFound;
        }

        boolean isTranslating = false;

        StringBuilder currentGene = new StringBuilder();
        StringBuilder currentAminoAcids = new StringBuilder();

        int i = 0;
        int startPosition = 0;

        while (i <= rna.length() - 3) {
            String codon = rna.substring(i, i + 3);

            if (!isTranslating) {
                if (codon.equals("AUG")) {
                    isTranslating = true;
                    startPosition = i + 1;

                    currentGene.setLength(0);
                    currentAminoAcids.setLength(0);

                    currentGene.append(codon);
                    currentAminoAcids.append(aA.get(codon));

                    i += 3;
                } else {
                    i++;
                }
            } else {
                String aminoAcid = aA.get(codon);

                currentGene.append(codon);

                if ("STOP".equals(aminoAcid)) {
                    genesFound.add(new FoundGene(
                            startPosition,
                            currentGene.toString(),
                            currentAminoAcids.toString()
                    ));

                    isTranslating = false;

                    currentGene.setLength(0);
                    currentAminoAcids.setLength(0);
                } else {
                    currentAminoAcids.append(aminoAcid);
                }

                i += 3;
            }
        }

        return genesFound;
    }
}