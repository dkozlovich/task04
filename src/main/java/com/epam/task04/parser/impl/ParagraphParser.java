package com.epam.task04.parser.impl;

import com.epam.task04.entity.ComponentType;
import com.epam.task04.entity.TextComponent;
import com.epam.task04.entity.TextComposite;
import com.epam.task04.parser.TextParser;

public class ParagraphParser implements TextParser {
    private static final String PARAGRAPH_REGEX = "[\\n\\t]+";
    private final TextParser sentenceParser = new SentenceParser();

    @Override
    public TextComposite parse(String text) {
        TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);
        String[] paragraphs = text.split(PARAGRAPH_REGEX);

        for (String paragraph : paragraphs) {
            TextComponent nextComposite = sentenceParser.parse(paragraph);
            paragraphComposite.add(nextComposite);
        }
        return paragraphComposite;
    }
}
