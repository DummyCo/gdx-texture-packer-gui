package com.crashinvaders.texturepackergui.config.attributes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.action.ActorConsumer;
import com.github.czyzby.lml.parser.tag.LmlAttribute;
import com.github.czyzby.lml.parser.tag.LmlTag;

public class OnRightClickLmlAttribute implements LmlAttribute<Actor> {

    @Override
    public Class<Actor> getHandledType() {
        return Actor.class;
    }

    @Override
    public void process(final LmlParser parser, final LmlTag tag, final Actor actor, final String rawAttributeData) {
        final ActorConsumer<?, Params> action = parser.parseAction(rawAttributeData, tmpParams);
        if (action == null) {
            parser.throwError("Could not find action for: " + rawAttributeData + " with actor: " + actor);
        }
        actor.addListener(new ClickListener(1) {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                tmpParams.actor = actor;
                tmpParams.x = x;
                tmpParams.y = y;
                tmpParams.stageX = event.getStageX();
                tmpParams.stageY = event.getStageY();
                action.consume(tmpParams);
            }
        });
    }

    private static Params tmpParams = new Params();
    public static class Params {
        public Actor actor;
        public float x, y;
        public float stageX, stageY;
    }
}
