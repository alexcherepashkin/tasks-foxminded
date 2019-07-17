package com.foxminded.task.anagram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EachWordTransformerTest {


    @Mock
    TextTransformer mock;

    @InjectMocks
    EachWordTransformer test;


    @Test
    void shouldSplitTextIntoWordsAndAssembleThemBack() {

        doReturn("ping").when(mock).transform("foo");
        doReturn("pong").when(mock).transform("bar");

        String actual = test.transform("foo bar");

        verify(mock, times(1)).transform("foo");
        verify(mock, times(1)).transform("bar");

        Assertions.assertEquals("ping pong", actual);

    }
}