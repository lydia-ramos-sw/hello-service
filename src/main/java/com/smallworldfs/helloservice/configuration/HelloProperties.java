package com.smallworldfs.helloservice.configuration;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

    private List<String> grantsOperationGet;
    private List<String> grantsOperationUpdate;
    private List<String> grantsOperationCreate;
    private List<Integer> itemsHigherGrants;
    private List<Integer> itemsNotAvailable;
    private List<Integer> itemsFatal;
    private List<Integer> itemsCanNotBeCreated;
}
