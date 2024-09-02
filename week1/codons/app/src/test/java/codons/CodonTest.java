package codons;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodonTest {
    @Test
    void toString_shouldReturnCodonNucleotideSequenceAsString() {
        Codon codon = new Codon(Nucleotide.T, Nucleotide.C, Nucleotide.A);
        String actual = codon.toString();
        assertEquals("TCA", actual); 
    }

    @Test
    void nucleotideConstructor_shouldSetNucleotideFields(){
        Codon codon = new Codon(Nucleotide.A, Nucleotide.G, Nucleotide.C);
        assertEquals(Nucleotide.A, codon.getFirst());
        assertEquals(Nucleotide.G, codon.getSecond());
        assertEquals(Nucleotide.C, codon.getThird());
    }
    
    @Test
    void charsConstructor_shouldSetNucleotideFields(){
        Codon codon = new Codon('G', 'T', 'A');
        assertEquals(Nucleotide.G, codon.getFirst());
        assertEquals(Nucleotide.T, codon.getSecond());
        assertEquals(Nucleotide.A, codon.getThird());
    }

    @Test
    void stringConstructor_shouldSetNucleotideFields(){
        Codon codon = new Codon("CTA");
        assertEquals(Nucleotide.C, codon.getFirst());
        assertEquals(Nucleotide.T, codon.getSecond());
        assertEquals(Nucleotide.A, codon.getThird());    
    }
}
