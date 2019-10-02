package com.santi.rockpaperscissors.storage;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.santi.rockpaperscissors.model.RoundResult;

public class InMemoryMapStorageTest {

    @Test
    public void shouldCreateUserWhenEmpty() {
        // GIVEN
        InMemoryMapStorage inMemoryMapStorage = new InMemoryMapStorage();

        // WHEN
        Long userId = inMemoryMapStorage.createUser();

        // THEN
        assertEquals(Long.valueOf(1), userId);
    }

    @Test
    public void shouldCreateUserWhenNotEmpty() {
        // GIVEN
        Map<Long, List<RoundResult>> map = new HashMap<>();
        map.put(1l, Collections.emptyList());
        InMemoryMapStorage inMemoryMapStorage = new InMemoryMapStorage(map);

        // WHEN
        Long userId = inMemoryMapStorage.createUser();

        // THEN
        assertEquals(Long.valueOf(2), userId);
    }

    @Test
    public void shouldGetRoundsByUserId() {
        // GIVEN
        RoundResult roundResult = new RoundResult();
        List<RoundResult> storedList = Collections.singletonList(roundResult);
        Map<Long, List<RoundResult>> map = new HashMap<>();
        map.put(1l, storedList);
        InMemoryMapStorage inMemoryMapStorage = new InMemoryMapStorage(map);

        // WHEN
        List<RoundResult> roundsFromUser = inMemoryMapStorage.getRoundsByUserId(1l);

        // THEN
        assertEquals(storedList, roundsFromUser);
    }

    @Test
    public void shouldSaveRoundWhenUserExists() {
        // GIVEN
        RoundResult roundResult = new RoundResult();
        Map<Long, List<RoundResult>> map = new HashMap<>();
        map.put(1l, new ArrayList<>());
        InMemoryMapStorage inMemoryMapStorage = new InMemoryMapStorage(map);

        // WHEN
        inMemoryMapStorage.saveRound(1l, roundResult);

        // THEN
        assertEquals(roundResult, inMemoryMapStorage.getRounds().get(0));
    }

    @Test
    public void shouldSaveRoundWhenUserDontExists() {
        // GIVEN
        RoundResult roundResult = new RoundResult();
        InMemoryMapStorage inMemoryMapStorage = new InMemoryMapStorage();

        // WHEN
        inMemoryMapStorage.saveRound(1l, roundResult);

        // THEN
        assertEquals(roundResult, inMemoryMapStorage.getRounds().get(0));
    }

    @Test
    public void shouldGetRounds() {
        // GIVEN
        Map<Long, List<RoundResult>> map = new HashMap<>();
        List<RoundResult> roundResults = Collections.singletonList(new RoundResult());
        map.put(1l, roundResults);
        InMemoryMapStorage inMemoryMapStorage = new InMemoryMapStorage(map);

        // WHEN
        List<RoundResult> retrievedRoundResults = inMemoryMapStorage.getRounds();

        // THEN
        assertEquals(roundResults, retrievedRoundResults);
    }

    @Test
    public void shouldRemoveRoundsByUserId() {
        // GIVEN
        Map<Long, List<RoundResult>> map = new HashMap<>();
        List<RoundResult> roundResults = new ArrayList<>();
        roundResults.add(new RoundResult());
        map.put(1l, roundResults);
        InMemoryMapStorage inMemoryMapStorage = new InMemoryMapStorage(map);

        // WHEN
        inMemoryMapStorage.removeRoundsByUserId(1l);

        // THEN
        assertEquals(1, inMemoryMapStorage.getRounds().size());
        assertEquals(0, inMemoryMapStorage.getRoundsByUserId(1l).size());
    }

}
