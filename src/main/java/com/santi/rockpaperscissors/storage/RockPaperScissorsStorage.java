package com.santi.rockpaperscissors.storage;

import java.util.List;
import com.santi.rockpaperscissors.model.RoundResult;


public interface RockPaperScissorsStorage {

    Long createUser();

    List<RoundResult> getRoundsByUserId(Long userId);

    void saveRound(Long userId, RoundResult roundResult);

    List<RoundResult> getRounds();

    void removeRoundsByUserId(Long userId);
}
