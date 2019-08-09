package ua.alexch.task.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CachingCharCounterDecoratorTest {

    @Mock
    Cache mockCache;

    @Mock
    CharCounter mockCounter;

    @InjectMocks
    CachingCharCounterDecorator test;

    @Test
    void shouldReturnNewResultOnceAndCachedResultNextTimesWhenGivenSameStringSeveralTimes() {
        String text = "Kitty say)";
        String result = "Meow!";

        doReturn(false).when(mockCache).isContain(text);
        doReturn(result).when(mockCounter).count(text);

        String actual = test.count(text);

        assertEquals(result, actual);

        doReturn(true).when(mockCache).isContain(text);
        doReturn(result).when(mockCache).getValue(text);

        test.count(text);
        actual = test.count(text);

        verify(mockCounter, times(1)).count(text);
        verify(mockCache, times(2)).getValue(text);

        assertEquals(result, actual);
    }
}
