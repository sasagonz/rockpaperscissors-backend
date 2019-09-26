package com.santi.rockpaperscissors.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.santi.rockpaperscissors.engine.RoundEngine;
import com.santi.rockpaperscissors.model.RoundRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RockPaperScissorsController {

    private RoundEngine roundCalculus;

    @PostMapping("/rounds")
    public String playRound(@RequestBody RoundRequest roundRequest) {
        return roundCalculus.getWinner(roundRequest);
    }
}
