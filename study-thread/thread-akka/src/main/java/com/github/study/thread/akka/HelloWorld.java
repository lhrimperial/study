package com.github.study.thread.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 *
 */
public class HelloWorld extends UntypedActor {

    @Override
    public void preStart() {
        // cre e the greeter actor
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        // tell it to perform the greeting
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Greeter.Msg.DONE) {
            // when the greeter is done, stop this actor and with it the application
            getContext().stop(getSelf());
        } else
            unhandled(msg);
    }
}
