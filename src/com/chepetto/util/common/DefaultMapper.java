package com.chepetto.util.common;

import java.util.function.UnaryOperator;

public class DefaultMapper implements UnaryOperator<Character> {
    @Override
    public Character apply(Character character) {
        return character;
    }
}
