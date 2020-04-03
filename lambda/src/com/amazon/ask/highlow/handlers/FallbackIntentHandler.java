package com.amazon.ask.highlow.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.highlow.Constants;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

// 2018-May-01: AMAZON.FallackIntent is only currently available in en-US locale.
//              This handler will not be triggered except in that locale, so it can be
//              safely deployed for any locale.
public class FallbackIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        // handle fallback intent, yes and no when playing a game
        // for yes and no, will only get here if and not caught by the normal intent handler
        return input.matches(intentName("AMAZON.FallbackIntent").or(intentName("AMAZON.YesIntent")).or(intentName("AMAZON.NoIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        if (sessionAttributes.get("gameState") != null && sessionAttributes.get("gameState").equals("STARTED")) {
            // currently playing
            return input.getResponseBuilder()
                    .withSpeech(Constants.FALLBACK_MESSAGE_DURING_GAME)
                    .withReprompt(Constants.FALLBACK_REPROMPT_DURING_GAME)
                    .build();
        }

        // not playing
        return input.getResponseBuilder()
                .withSpeech(Constants.FALLBACK_MESSAGE_OUTSIDE_GAME)
                .withReprompt(Constants.FALLBACK_REPROMPT_OUTSIDE_GAME)
                .build();
    }

}
