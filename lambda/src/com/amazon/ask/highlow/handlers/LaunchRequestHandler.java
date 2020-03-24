package com.amazon.ask.highlow.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler  implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        // launch requests as well as any new session, as games are not saved in progress, which makes
        // no one shots a reasonable idea except for help, and the welcome message provides some help.
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> attributes = attributesManager.getPersistentAttributes();
        if (attributes.isEmpty()) {
            attributes.put("endedSessionCount", 0);
            attributes.put("gamesPlayed", 0);
            attributes.put("gameState", "ENDED");
        }

        attributesManager.setSessionAttributes(attributes);

        String speechOutput = String.format("Welcome to High Low guessing game. You have played %s times. would you like to play?", attributes.get("gamesPlayed"));
        String reprompt = "Say yes to start the game or no to quit.";
        return input.getResponseBuilder()
                .withSpeech(speechOutput)
                .withReprompt(reprompt)
                .build();
    }

}
