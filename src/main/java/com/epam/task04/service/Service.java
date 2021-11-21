package com.epam.task04.service;

import com.epam.task04.entity.TextComponent;
import com.epam.task04.entity.TextComposite;
import com.epam.task04.exception.CustomException;

import java.util.List;

public interface Service {

    List<TextComponent> sortParagraphs(TextComposite composite) throws CustomException;

    List<TextComponent> findSentencesWithLongWord(TextComposite composite) throws CustomException;


    void deleteSentencesWithLessWords(TextComposite composite, int wordsAmount);


    List<String> countRepeatWords(TextComposite composite);


    int countVowels(TextComponent sentence) throws CustomException;


    int countConsonants(TextComponent sentence) throws CustomException;
}
