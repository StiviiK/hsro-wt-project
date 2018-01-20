package modules;


import com.google.inject.AbstractModule;
import play.engineio.EngineIOController;
import socketio.ChatEngine;

/**
 * The chat module.
 */
public class SocketIOModule extends AbstractModule {
    protected void configure() {
        bind(EngineIOController.class).toProvider(ChatEngine.class);
    }
}