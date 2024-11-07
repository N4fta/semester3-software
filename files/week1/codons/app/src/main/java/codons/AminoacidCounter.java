package codons;

import java.util.*;

public class AminoacidCounter {
    public Map<Aminoacid, Integer> countAminoacids(List<Codon> sequence, Set<Aminoacid> aminoacids) {
        Map<Aminoacid, Integer> countPerAminoacid = new HashMap<>();

        for (Aminoacid aminoacid : aminoacids) {
            List<Codon> copySequence = new ArrayList<Codon>(sequence);
            copySequence.retainAll(aminoacid.getPossibleCodons());
            countPerAminoacid.put(aminoacid, copySequence.size());
        }

        return countPerAminoacid;
    }
}