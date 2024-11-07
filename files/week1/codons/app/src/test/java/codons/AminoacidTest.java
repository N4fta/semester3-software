package codons;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AminoacidTest {
    @Test
    void isPresent_shouldReturnFalse_whenSequenceIsNull() {
        Aminoacid aminoacid = new Aminoacid(Set.of(new Codon("GGG")));
        assertFalse(aminoacid.isPresent(null));
    }

    @Test
    void isPresent_shouldReturnFalse_whenSequenceIsEmpty() {
        Aminoacid aminoacid = new Aminoacid(Set.of(new Codon("GGG")));
        assertFalse(aminoacid.isPresent(Collections.emptyList()));
    }

    @Test
    void isPresent_shouldReturnFalse_whenSequenceDoesNotContainAminoacid() {
        Aminoacid aminoacid = new Aminoacid(Set.of(new Codon("GGG")));
        List<Codon> codonSequence = List.of(new Codon("GTA"), new Codon("CCC"));
        assertFalse(aminoacid.isPresent(codonSequence));
    }

    @Test
    void isPresent_shouldReturnTrue_whenSequenceDoesContainAminoacid() {
        Aminoacid aminoacid = new Aminoacid(Set.of(new Codon("ATG")));
        List<Codon> codonSequence = List.of(new Codon("GTA"), new Codon("ATG"));
        assertTrue(aminoacid.isPresent(codonSequence));
    }

    @Test
    void getPossibleCodons_shouldReturnEmpty_whenPossibleCodonsIsNull() {
        Aminoacid aminoacid = new Aminoacid(null);
        Set<Codon> possibleCodons = aminoacid.getPossibleCodons();
        assertEquals(0, possibleCodons.size());
    }

    @Test
    void getPossibleCodons_shouldReturnUnmodifiableSet() {
        Aminoacid aminoacid = new Aminoacid(Set.of(new Codon("ATT")));
        Set<Codon> possibleCodons = aminoacid.getPossibleCodons();
        assertThrows(UnsupportedOperationException.class, () -> possibleCodons.add(new Codon("AAA")));
    }
}