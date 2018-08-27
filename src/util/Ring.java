package util;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Ring {
    private final int modulo;

    public Ring(int modulo) {
        this.modulo = modulo;
    }

    public int mod(int v) {
        int r = v % modulo;
        return r < 0 ? modulo + r : r;
    }

    public int add(int a, int b) {
        return mod(mod(a) + mod(b));
    }

    public int mult(int a, int b) {
        return mod(mod(a) * mod(b));
    }

    private final Map<Pair<Integer, Integer>, Integer> powers = new HashMap<>();
    public int pow(int a, int power) {
        if (power == 0) return 1;
        if (power == 1) return mod(a);
        Pair<Integer, Integer> key = new Pair<>(a, power);
        if (powers.containsKey(key)) {
            return powers.get(key);
        }
        int res = mult(
                pow(a, power / 2 + power % 2),
                pow(a, power / 2)
        );
        powers.put(key, res);
        return res;
    }
}

