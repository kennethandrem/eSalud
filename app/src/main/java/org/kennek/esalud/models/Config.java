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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "encoding",
        "sampleRateHertz",
        "languageCode",
        "maxAlternatives",
        "profanityFilter"
})
public class Config {
    @SerializedName("encoding")
    @Expose
    @JsonProperty("encoding")
    private String encoding;
    @SerializedName("sampleRateHertz")
    @Expose
    @JsonProperty("sampleRateHertz")
    private Integer sampleRateHertz;
    @SerializedName("languageCode")
    @Expose
    @JsonProperty("languageCode")
    private String languageCode;
    @SerializedName("maxAlternatives")
    @Expose
    @JsonProperty("maxAlternatives")
    private Integer maxAlternatives;
    @SerializedName("profanityFilter")
    @Expose
    @JsonProperty("profanityFilter")
    private Boolean profanityFilter;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("encoding")
    public String getEncoding() {
        return encoding;
    }

    @JsonProperty("encoding")
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @JsonProperty("sampleRateHertz")
    public Integer getSampleRateHertz() {
        return sampleRateHertz;
    }

    @JsonProperty("sampleRateHertz")
    public void setSampleRateHertz(Integer sampleRateHertz) {
        this.sampleRateHertz = sampleRateHertz;
    }

    @JsonProperty("languageCode")
    public String getLanguageCode() {
        return languageCode;
    }

    @JsonProperty("languageCode")
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @JsonProperty("maxAlternatives")
    public Integer getMaxAlternatives() {
        return maxAlternatives;
    }

    @JsonProperty("maxAlternatives")
    public void setMaxAlternatives(Integer maxAlternatives) {
        this.maxAlternatives = maxAlternatives;
    }

    @JsonProperty("profanityFilter")
    public Boolean getProfanityFilter() {
        return profanityFilter;
    }

    @JsonProperty("profanityFilter")
    public void setProfanityFilter(Boolean profanityFilter) {
        this.profanityFilter = profanityFilter;
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