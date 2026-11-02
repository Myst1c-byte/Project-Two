void main() throws Exception {
    FASTAReader reader = new FASTAReader();
    Ribosome ribosome = new Ribosome();

    String dna = reader.readSequence().toString();

    if (!NTchecker.isValidDna(dna)) {
        System.out.println("Invalid DNA sequence");
        return;
    }

    String mRNA = ribosome.transcription(dna);

    List<Ribosome.FoundGene> genes = Ribosome.translation(mRNA);

    System.out.print("mRNA:");
    System.out.println(mRNA);
    System.out.println("Genes found: " + genes.size());

    FASTAExporter.exportGenes(
            genes,
            "nucleotide_genes.fasta",
            "amino_acid_genes.fasta",
            "short_orfs.fasta"
    );
}