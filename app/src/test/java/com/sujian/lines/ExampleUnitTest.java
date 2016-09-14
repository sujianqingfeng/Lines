package com.sujian.lines;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        System.err.println(String.valueOf(Integer.valueOf(df.format(new Date())) + 1));
    }
}