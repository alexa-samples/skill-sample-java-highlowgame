package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

public class UnsupportedRequestHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String outputSpeech = "Say yes to continue, or no to end the game.";
        return input.getResponseBuilder()
                .withSpeech(outputSpeech)
                .withReprompt(outputSpeech)
                .build();
    }

}
