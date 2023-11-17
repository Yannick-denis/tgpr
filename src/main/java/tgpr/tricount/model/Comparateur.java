package tgpr.tricount.model;

import java.util.Comparator;

public class Comparateur implements Comparator<Operation> {
    @Override
    public int compare(Operation o1, Operation o2) {
        return o2.getCreatedAt().compareTo(o1.getCreatedAt());
    }
}
