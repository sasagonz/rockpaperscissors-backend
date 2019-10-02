package com.santi.rockpaperscissors.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import com.santi.rockpaperscissors.model.Statistic;
import com.santi.rockpaperscissors.processor.StatisticMapper;
import com.santi.rockpaperscissors.storage.RockPaperScissorsStorage;

@RunWith(MockitoJUnitRunner.class)
public class DefaultRoundsApiDelegateTest {

    private DefaultRoundsApiDelegate defaultRoundsApiDelegate;

    @Mock
    private NativeWebRequest nativeWebRequest;

    @Mock
    private RockPaperScissorsStorage rockPaperScissorsStorage;

    @Mock
    private StatisticMapper statisticMapper;

    @Before
    public void setUp() {
        defaultRoundsApiDelegate = new DefaultRoundsApiDelegate(
            nativeWebRequest,
            rockPaperScissorsStorage,
            statisticMapper);
    }

    @Test
    public void shouldGetStatistics() {
        // GIVEN
        Statistic expectedStatistic = new Statistic();
        when(statisticMapper.from(any())).thenReturn(expectedStatistic);

        // WHEN
        ResponseEntity<Statistic> responseEntity = defaultRoundsApiDelegate.getStatistics();

        // THEN
        assertEquals(expectedStatistic, responseEntity.getBody());
        verify(rockPaperScissorsStorage, times(1)).getRounds();
    }

}
