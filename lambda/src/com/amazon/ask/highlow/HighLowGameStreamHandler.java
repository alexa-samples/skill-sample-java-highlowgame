package com.amazon.ask.highlow;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazon.ask.highlow.handlers.LaunchRequestHandler;
import com.amazon.ask.highlow.handlers.NumberGuessIntentHandler;
import com.amazon.ask.highlow.handlers.YesIntentHandler;
import com.amazon.ask.highlow.handlers.ExceptionHandler;
import com.amazon.ask.highlow.handlers.ExitHandler;
import com.amazon.ask.highlow.handlers.FallbackIntentHandler;
import com.amazon.ask.highlow.handlers.HelpIntentHandler;
import com.amazon.ask.highlow.handlers.NoIntentHandler;
import com.amazon.ask.highlow.handlers.SessionEndedRequestHandler;
import com.amazon.ask.highlow.handlers.UnsupportedRequestHandler;

public class HighLowGameStreamHandler extends SkillStreamHandler {
    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                    new LaunchRequestHandler(),
                    new YesIntentHandler(),
                    new NoIntentHandler(),
                    new NumberGuessIntentHandler(),
                    new HelpIntentHandler(),
                    new ExitHandler(),
                    new SessionEndedRequestHandler(),
                    new FallbackIntentHandler(),
                    new UnsupportedRequestHandler())
                .addExceptionHandler(new ExceptionHandler())
                .withTableName("HighLowGame")
                .withAutoCreateTable(true)
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public HighLowGameStreamHandler() { super(getSkill()); }
}
