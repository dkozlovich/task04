package com.epam.task04.parser.impl;

import com.epam.task04.entity.ComponentType;
import com.epam.task04.entity.TextComposite;
import com.epam.task04.parser.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {

    private final String MATH_REGEX = "([-+/*|%$&~><^.)(\\d]+){2,}";
    private final String LEXEME_REGEX = "\\s";
    private final String WORD_DELIMITER_REGEX = "[А-я\\p{Alpha}]+";
    private final String PRE_PUNCTUATION_REGEX = "^\\p{Punct}(?!\\d)";
    private final String POST_PUNCTUATION_REGEX = "(?<!\\d)\\p{Punct}$";
    private final TextParser wordParser = new WordParser();
    private final TextParser symbolParser = new SymbolParser();

    @Override
    public TextComposite parse(String data) {
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
        String[] lexemes = data.split(LEXEME_REGEX);

        for (String lexeme : lexemes) {
            Pattern pattern = Pattern.compile(MATH_REGEX);
            Matcher matcher = pattern.matcher(lexeme);
            List<String> math = new ArrayList<>();
            while (matcher.find()) {math.add(matcher.group());}
            if (!math.isEmpty()) {
                for (String symbol : math) {
                    TextComposite mathComposite = symbolParser.parse(symbol);
                    lexemeComposite.add(mathComposite);
                }
            } else {
                pattern = Pattern.compile(PRE_PUNCTUATION_REGEX);
                matcher = pattern.matcher(lexeme);
                List<String> punctuation = new ArrayList<>();
                while (matcher.find()) {punctuation.add(matcher.group());}
                for (String symbol : punctuation) {
                    TextComposite punctuationComposite = symbolParser.parse(symbol);
                    lexemeComposite.add(punctuationComposite);
                }
                pattern = Pattern.compile(WORD_DELIMITER_REGEX);
                matcher = pattern.matcher(lexeme);
                if (matcher.find()) {
                    TextComposite wordComposite = wordParser.parse(matcher.group());
                    lexemeComposite.add(wordComposite);
                }
                pattern = Pattern.compile(POST_PUNCTUATION_REGEX);
                matcher = pattern.matcher(lexeme);
                punctuation = new ArrayList<>();
                while (matcher.find()) {punctuation.add(matcher.group());}
                for (String symbol : punctuation) {
                    TextComposite punctuationComposite = symbolParser.parse(symbol);
                    lexemeComposite.add(punctuationComposite);
                }
            }
        }
        return lexemeComposite;
    }
}
