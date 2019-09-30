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

    private Map<Long, List<RoundResult>> userMap;

    public InMemoryMapStorage() {
        this.userMap = new ConcurrentHashMap<>();
    }

    public InMemoryMapStorage(Map<Long, List<RoundResult>> map) {
        this();
        this.userMap.putAll(map);
    }

    @Override
    public Long createUser() {
        return userMap
            .keySet()
            .stream()
            .max(Comparator.naturalOrder())
            .map(max -> {
                long newId = max + 1;
                userMap.put(newId, new ArrayList<>());
                return newId;
            })
            .orElseGet(() -> {
                userMap.put(INITIAL_ID, new ArrayList<>());
                return INITIAL_ID;
            });
    }

    @Override
    public List<RoundResult> getRoundsByUserId(Long userId) {
        Assert.notNull(userId, "userId cannot be null");
        return Optional
            .ofNullable(userMap.get(userId))
            .orElse(Collections.emptyList());
    }

    @Override
    public void saveRound(Long userId, RoundResult roundResult) {
        Assert.notNull(userId, "userId cannot be null");
        Assert.notNull(roundResult, "roundResult cannot be null");
        userMap.putIfAbsent(userId, new ArrayList<>());
        userMap.get(userId).add(roundResult);
    }

    @Override
    public List<RoundResult> getRounds() {
        return userMap
            .values()
            .stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    @Override
    public void removeRoundsByUserId(Long userId) {
        userMap.computeIfPresent(
            userId,
            (key, list) -> {
                list.clear();
                return list;
            });
    }
}
