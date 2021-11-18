package com.epam.task04.parser.impl;

import com.epam.task04.entity.ComponentType;
import com.epam.task04.entity.TextComposite;
import com.epam.task04.parser.TextParser;

public class WordParser implements TextParser {

    private final TextParser symbolParser = new SymbolParser();

    @Override
    public TextComposite parse(String data) {

        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        TextComposite nextComposite = symbolParser.parse(data);
        wordComposite.add(nextComposite);
        return wordComposite;
    }
}
