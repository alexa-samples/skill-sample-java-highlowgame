package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NumberGuessIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        boolean isCurrentlyPlaying = false;
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        if (sessionAttributes.get("gameState") != null && sessionAttributes.get("gameState").equals("STARTED")) {
            isCurrentlyPlaying = true;
        }

        return isCurrentlyPlaying && input.matches(intentName("NumberGuessIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        int guessNum = Integer.parseInt(intentRequest.getIntent().getSlots().get("number").getValue(), 10);
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        int targetNum = (int) sessionAttributes.get("guessNumber");

        if (guessNum > targetNum) {
            return input.getResponseBuilder()
                    .withSpeech(guessNum + " is too high")
                    .withReprompt("Try saying a smaller number.")
                    .build();
        } else if (guessNum < targetNum) {
            return input.getResponseBuilder()
                    .withSpeech(guessNum + " is too low")
                    .withReprompt("Try saying a larger number.")
                    .build();
        } else if (guessNum == targetNum) {
            int gamesPlayed = (int) sessionAttributes.get("gamesPlayed") + 1;
            sessionAttributes.put("gamesPlayed", gamesPlayed);
            sessionAttributes.put("gameState", "ENDED");
            input.getAttributesManager().setPersistentAttributes(sessionAttributes);
            input.getAttributesManager().savePersistentAttributes();
            return input.getResponseBuilder()
                    .withSpeech(guessNum + " is correct! Would you like a play a new game?")
                    .withReprompt("Say yes to start a new game, or no to end the game")
                    .build();
        }
        return input.getResponseBuilder()
                .withSpeech("Sorry I didn't get that. Try saying a number")
                .withReprompt("Try saying a number")
                .build();
    }
}
