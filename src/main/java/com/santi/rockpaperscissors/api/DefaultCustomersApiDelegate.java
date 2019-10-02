package com.santi.rockpaperscissors.api;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import com.santi.rockpaperscissors.model.RoundRequest;
import com.santi.rockpaperscissors.model.RoundResult;
import com.santi.rockpaperscissors.processor.RoundResultTransformer;
import com.santi.rockpaperscissors.storage.RockPaperScissorsStorage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultCustomersApiDelegate implements CustomersApiDelegate {

    private NativeWebRequest nativeWebRequest;
    private RockPaperScissorsStorage storage;
    private RoundResultTransformer roundResultTransformer;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(nativeWebRequest);
    }

    @Override
    public ResponseEntity<String> createCustomer() {
        return new ResponseEntity<>(storage.createUser().toString(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RoundResult> createRoundByCustomer(Long customerId,
        RoundRequest roundRequest) {
        RoundResult roundResult = roundResultTransformer.transform(roundRequest);
        storage.saveRound(customerId, roundResult);
        return new ResponseEntity<>(roundResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RoundResult>> getRoundsByCustomer(Long customerId) {
        return new ResponseEntity<>(storage.getRoundsByUserId(customerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> removeRoundsByCustomer(Long customerId) {
        storage.removeRoundsByUserId(customerId);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
