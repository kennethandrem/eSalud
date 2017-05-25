package org.kennek.esalud.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "alternatives"
})
public class SpeechApiResponse {

    @JsonProperty("alternatives")
    private List<org.kennek.esalud.Alternative> alternatives = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("alternatives")
    public List<org.kennek.esalud.Alternative> getAlternatives() {
        return alternatives;
    }

    @JsonProperty("alternatives")
    public void setAlternatives(List<org.kennek.esalud.Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

