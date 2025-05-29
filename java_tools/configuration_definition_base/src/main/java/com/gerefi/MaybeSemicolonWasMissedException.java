package com.gerefi;

public class MaybeSemicolonWasMissedException extends IllegalArgumentException {
    public MaybeSemicolonWasMissedException(String s) {
        super(s);
    }
}
