package code.generationStrategy;

import java.util.concurrent.atomic.AtomicLong;

public class Base62Generator implements GenerationStrategy {
    private static final char[] ALPHABET =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private final AtomicLong sequence = new AtomicLong(System.currentTimeMillis());

    @Override
    public String generateCode() {
        long value = sequence.getAndIncrement();
        if (value < 0) {
            throw new IllegalStateException("Base62 code sequence is exhausted");
        }
        return encode(value);
    }

    private String encode(long value) {
        StringBuilder code = new StringBuilder();
        do {
            code.append(ALPHABET[(int) (value % ALPHABET.length)]);
            value /= ALPHABET.length;
        } while (value > 0);
        return code.reverse().toString();
    }
}
