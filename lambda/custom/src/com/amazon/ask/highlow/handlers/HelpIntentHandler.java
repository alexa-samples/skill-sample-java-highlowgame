package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HelpIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechOutput = "I am thinking of a number between zero and one hundred, try to guess it and I will tell you if it is higher or lower.";
        String reprompt = "Try saying a number.";
        return input.getResponseBuilder()
                .withSpeech(speechOutput)
                .withReprompt(reprompt)
                .build();
    }

}
