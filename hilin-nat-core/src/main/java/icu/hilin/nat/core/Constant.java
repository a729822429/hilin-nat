package icu.hilin.nat.core;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class Constant {

    public static final Vertx VERTX = Vertx.vertx();

    public static final EventBus EVENT_BUS = VERTX.eventBus();

}
