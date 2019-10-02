package com.santi.rockpaperscissors.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import com.santi.rockpaperscissors.model.RoundRequest;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.processor.RoundResultTransformer;
import com.santi.rockpaperscissors.storage.RockPaperScissorsStorage;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCustomersApiDelegateTest {

    private DefaultCustomersApiDelegate defaultCustomersApiDelegate;

    @Mock
    private NativeWebRequest nativeWebRequest;

    @Mock
    private RockPaperScissorsStorage storage;

    @Mock
    private RoundResultTransformer roundResultTransformer;

    @Before
    public void setUp() {
        defaultCustomersApiDelegate = new DefaultCustomersApiDelegate(
            nativeWebRequest,
            storage,
            roundResultTransformer);
    }

    @Test
    public void shouldCreateCustomer() {
        // GIVEN
        when(storage.createUser()).thenReturn(1l);

        // WHEN
        ResponseEntity<String> responseEntity = defaultCustomersApiDelegate.createCustomer();

        // THEN
        assertEquals("1", responseEntity.getBody());
    }

    @Test
    public void shouldCreateRound() {
        // GIVEN
        RoundResult expectedRoundResult = new RoundResult();
        when(roundResultTransformer.apply(any())).thenReturn(expectedRoundResult);

        // WHEN
        ResponseEntity<RoundResult> responseEntity =
            defaultCustomersApiDelegate.createRoundByCustomer(1l, new RoundRequest());

        // THEN
        assertEquals(expectedRoundResult, responseEntity.getBody());
        verify(storage, times(1)).saveRound(1l, expectedRoundResult);
    }

    @Test
    public void shouldGetRoundsByCustomer() {
        // GIVEN
        List<RoundResult> expectedRoundResults = new ArrayList<>();
        when(storage.getRoundsByUserId(1l)).thenReturn(expectedRoundResults);

        // WHEN
        ResponseEntity<List<RoundResult>> responseEntity =
            defaultCustomersApiDelegate.getRoundsByCustomer(1l);

        // THEN
        assertEquals(expectedRoundResults, responseEntity.getBody());
    }

    @Test
    public void shouldRemoveRounds() {
        // WHEN
        defaultCustomersApiDelegate.removeRoundsByCustomer(1l);

        // THEN
        verify(storage, times(1)).removeRoundsByUserId(1l);
    }

}
