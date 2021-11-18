package com.epam.task04.parser.impl;

import com.epam.task04.entity.ComponentType;
import com.epam.task04.entity.LetterAndSymbol;
import com.epam.task04.entity.TextComposite;
import com.epam.task04.parser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser implements TextParser {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComposite parse(String text) {
        TextComposite symbolComposite = new TextComposite(ComponentType.SYMBOL);
        char[] symbols = text.toCharArray();
        for (char symbol : symbols) {
            LetterAndSymbol leaf = new LetterAndSymbol(symbol);
            symbolComposite.add(leaf);
        }
        return symbolComposite;
    }
}
