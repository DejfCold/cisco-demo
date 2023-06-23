package cz.dejfcold.cisco_demo.restapi.controllers.requestParams;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TaggedContentRequestParams {
    @NotEmpty
    private String tag;
}
