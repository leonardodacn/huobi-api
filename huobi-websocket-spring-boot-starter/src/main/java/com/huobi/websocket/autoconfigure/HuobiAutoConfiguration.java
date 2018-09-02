package com.huobi.websocket.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import com.huobi.websocket.entity.Kline;
import com.huobi.websocket.handler.DefaultHuobiWebSocketHandler;
import com.huobi.websocket.handler.message.DefaultHandleMessage;
import com.huobi.websocket.handler.message.IHandleMessage;

/**
 * @author kyle.zeng
 * @date 2017/12/28 15:21
 */
@Configuration
@AutoConfigureAfter(WebSocketAutoConfiguration.class)
public class HuobiAutoConfiguration {
    private String webSocketUri = "wss://api.huobi.pro/ws";

    @Bean
    @ConditionalOnMissingBean
    public WebSocketConnectionManager wsConnectionManager(StandardWebSocketClient client, WebSocketHandler handler) {
        WebSocketConnectionManager manager = new WebSocketConnectionManager(client,
                handler, this.webSocketUri);
        manager.setAutoStartup(true);
        return manager;
    }

    @Bean
    @ConditionalOnMissingBean
    public StandardWebSocketClient client() {
        return new StandardWebSocketClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketHandler handler() {
        return new DefaultHuobiWebSocketHandler(handleMessage());
    }

    @Bean
    @ConditionalOnMissingBean
    public IHandleMessage handleMessage() {
        return new DefaultHandleMessage(Kline.class);
    }


}
