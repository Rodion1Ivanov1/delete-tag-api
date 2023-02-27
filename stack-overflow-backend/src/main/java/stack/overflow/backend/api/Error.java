package stack.overflow.backend.api;

import lombok.Getter;

@Getter
public final class Error {

    private final String text;

    private Error(String text) {
        this.text = text;
    }

    public static Error build(String text) {
        return new Error(text);
    }
}
