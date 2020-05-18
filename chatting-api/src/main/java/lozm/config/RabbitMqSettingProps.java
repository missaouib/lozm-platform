package lozm.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbitmq.setting")
public class RabbitMqSettingProps {

    private String url;
    private String host;
    private int port;
    private String clientId;
    private String clientPassword;

}
