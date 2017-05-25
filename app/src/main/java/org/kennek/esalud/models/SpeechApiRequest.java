package org.kennek.esalud.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.kennek.esalud.Audio;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "config",
        "audio"
})
public class SpeechApiRequest {
    @SerializedName("config")
    @Expose
    @JsonProperty("config")
    private Config config;
    @SerializedName("audio")
    @Expose
    @JsonProperty("audio")
    private Audio audio;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("config")
    public Config getConfig() {
        return config;
    }

    @JsonProperty("config")
    public void setConfig(Config config) {
        this.config = config;
    }

    @JsonProperty("audio")
    public Audio getAudio() {
        return audio;
    }

    @JsonProperty("audio")
    public void setAudio(Audio audio) {
        this.audio = audio;
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
