package com.epam.task04.reader;

import com.epam.task04.exception.CustomException;

public interface StringReader {

    String read(String pathToFile) throws CustomException;
}
