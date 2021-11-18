package com.epam.task04.service.impl;

import com.epam.task04.entity.ComponentType;
import com.epam.task04.entity.TextComponent;
import com.epam.task04.entity.TextComposite;
import com.epam.task04.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompositeService implements Service {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<TextComponent> sortParagraphs(TextComposite composite) {

        List<TextComponent> sortedParagraphs = composite.getChild();
        sortedParagraphs.sort(new Comparator<TextComponent>() {
            public int compare(TextComponent o1, TextComponent o2) {
                return o1.size() - o2.size();
            }
        });
        logger.info("Sorted paragraphs: " + sortedParagraphs);
        return sortedParagraphs;
    }

    @Override
    public List<TextComponent> findSentencesWithLongWord(TextComposite composite) {
        int maxLength = 0;
        List<TextComponent> sentencesWithWord = new ArrayList<>();
        List<TextComponent> paragraphs = composite.getChild();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getChild();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getChild();
                for (TextComponent lexeme : lexemes) {
                    List<TextComponent> wordsAndSymbols = lexeme.getChild();
                    for (TextComponent word : wordsAndSymbols) {
                        if (word.size() > maxLength) {
                            maxLength = word.size();
                            sentencesWithWord.clear();
                            sentencesWithWord.add(sentence);
                        }
                    }
                }
            }
        }
        logger.info("Sentences with the longest word: " + sentencesWithWord);
        return sentencesWithWord;
    }

    @Override
    public void deleteSentencesWithLessWords(TextComposite composite, int wordsAmount) {
        List<TextComponent> sentencesAfterRemoving = new ArrayList<>();
        List<TextComponent> sentences = null;
        int count = 0;
        List<TextComponent> paragraphs = composite.getChild();
        for (TextComponent paragraph : paragraphs) {
            sentences = paragraph.getChild();
            sentencesAfterRemoving.addAll(sentences);
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getChild();
                for (TextComponent lexeme : lexemes) {
                    if (lexeme.getType().equals(ComponentType.WORD)) {
                        ++count;
                    }
                }
                if (count < wordsAmount) {
                    sentencesAfterRemoving.remove(sentence);
                    logger.info("Sentence was removed " + ": " + sentence);
                }
                count = 0;
            }
            paragraph.setComponents(sentencesAfterRemoving);
            sentencesAfterRemoving.removeAll(sentences);
        }
        logger.info("Text without sentence with  words less than " + wordsAmount + ": " + composite);

    }

    @Override
    public List<String> countRepeatWords(TextComposite composite) {
        Map<String, Integer> textWords = new HashMap<>();
        List<TextComponent> paragraphs = composite.getChild();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getChild();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getChild();
                for (TextComponent lexeme : lexemes) {
                    if (lexeme.getType().equals(ComponentType.WORD)) {
                        List<TextComponent> words = lexeme.getChild();
                        for (TextComponent word : words) {
                            int counter = 1;
                            String wordWithoutCase = word.toString().toLowerCase();
                            if (textWords.containsKey(wordWithoutCase)) {
                                counter = textWords.get(wordWithoutCase) + 1;
                            }
                            textWords.put(wordWithoutCase, counter);
                        }
                    }
                }
            }
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> word : textWords.entrySet()) {
            if (word.getValue() > 1) {
                result.add(word.getKey());
            }
        }
        for (String word : result) {
            logger.info("Words that repeat in text: " + word + "/n");
        }
        return result;
    }

    @Override
    public int countVowels(TextComponent sentence) {
        final String VOWEL_REGEX = "[aeiouyаеёиоуыэюя]";
        Pattern pattern = Pattern.compile(VOWEL_REGEX);
        Matcher matcher;
        int counter = 0;
        List<TextComponent> lexemes = sentence.getChild();
        for (TextComponent lexeme : lexemes) {
            if (lexeme.getType().equals(ComponentType.WORD)) {
                List<TextComponent> words = lexeme.getChild();
                for (TextComponent word : words) {
                    List<TextComponent> symbols = word.getChild();
                    for (TextComponent symbol : symbols) {
                        matcher = pattern.matcher(symbol.toString().toLowerCase());
                        if (matcher.matches()) {
                            counter++;
                        }
                    }
                }
            }
        }
        logger.info("Number of vowels in sentence " + sentence + ": " + counter);
        return counter;
    }

    @Override
    public int countConsonants(TextComponent sentence) {
        final String CONSONANT_REGEX = "[bcdfghjklmnpqrstvwxzбвгджзйклмнпрстфхцчшщ]";
        Pattern pattern = Pattern.compile(CONSONANT_REGEX);
        Matcher matcher;
        int counter = 0;
        List<TextComponent> lexemes = sentence.getChild();
        for (TextComponent lexeme : lexemes) {
            if (lexeme.getType().equals(ComponentType.WORD)) {
                List<TextComponent> words = lexeme.getChild();
                for (TextComponent word : words) {
                    List<TextComponent> symbols = word.getChild();
                    for (TextComponent symbol : symbols) {
                        matcher = pattern.matcher(symbol.toString().toLowerCase());
                        if (matcher.matches()) {
                            counter++;
                        }
                    }
                }
            }
        }
        logger.info("Number of consonants in sentence " + sentence + ": " + counter);
        return counter;
    }
}
