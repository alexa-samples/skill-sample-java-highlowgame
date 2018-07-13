package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.highlow.Constants;
import com.amazon.ask.model.Response;
import org.slf4j.Logger;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class ExceptionHandler implements com.amazon.ask.dispatcher.exception.ExceptionHandler {
    private static Logger LOG = getLogger(SessionEndedRequestHandler.class);

    @Override
    public boolean canHandle(HandlerInput input, Throwable throwable) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput input, Throwable throwable) {
        LOG.error("Error message is " + throwable.getMessage());
        return input.getResponseBuilder()
                .withSpeech(Constants.EXCEPTION_MESSAGE)
                .withReprompt(Constants.EXCEPTION_MESSAGE)
                .build();
    }

}
