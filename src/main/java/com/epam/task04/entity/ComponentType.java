package com.epam.task04.entity;

public enum ComponentType {
    TEXT("\n\t"),
    PARAGRAPH("\n"),
    SENTENCE(""),
    LEXEME(" "),
    WORD(""),
    SYMBOL("");

    private final String delimiter;

    ComponentType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
