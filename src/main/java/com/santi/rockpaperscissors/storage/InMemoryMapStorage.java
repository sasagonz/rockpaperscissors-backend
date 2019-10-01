package com.santi.rockpaperscissors.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.util.Assert;
import com.santi.rockpaperscissors.model.RoundResult;

public class InMemoryMapStorage implements RockPaperScissorsStorage {

    private static final Long INITIAL_ID = Long.valueOf(1l);

    private Map<Long, List<RoundResult>> userRounds;
    private List<RoundResult> rounds;

    public InMemoryMapStorage() {
        this.userRounds = new ConcurrentHashMap<>();
        this.rounds = Collections.synchronizedList(new ArrayList<>());
    }

    public InMemoryMapStorage(Map<Long, List<RoundResult>> map) {
        this();
        this.userRounds.putAll(map);
        this.rounds.addAll(
            map
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));
    }

    @Override
    public Long createUser() {
        return userRounds
            .keySet()
            .stream()
            .max(Comparator.naturalOrder())
            .map(max -> {
                long newId = max + 1;
                userRounds.put(newId, new ArrayList<>());
                return newId;
            })
            .orElseGet(() -> {
                userRounds.put(INITIAL_ID, new ArrayList<>());
                return INITIAL_ID;
            });
    }

    @Override
    public List<RoundResult> getRoundsByUserId(Long userId) {
        Assert.notNull(userId, "userId cannot be null");
        return Optional
            .ofNullable(userRounds.get(userId))
            .orElse(Collections.emptyList());
    }

    @Override
    public void saveRound(Long userId, RoundResult roundResult) {
        Assert.notNull(userId, "userId cannot be null");
        Assert.notNull(roundResult, "roundResult cannot be null");
        userRounds.putIfAbsent(userId, new ArrayList<>());
        userRounds.get(userId).add(roundResult);
        rounds.add(roundResult);
    }

    @Override
    public List<RoundResult> getRounds() {
        return this.rounds;
    }

    @Override
    public void removeRoundsByUserId(Long userId) {
        userRounds.computeIfPresent(
            userId,
            (key, list) -> {
                list.clear();
                return list;
            });
    }
}
