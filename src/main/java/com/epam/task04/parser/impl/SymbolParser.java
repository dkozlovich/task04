package com.epam.task04.parser.impl;

import com.epam.task04.entity.ComponentType;
import com.epam.task04.entity.Symbol;
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
            Symbol leaf = new Symbol(symbol);
            symbolComposite.add(leaf);
        }
        return symbolComposite;
    }
}
