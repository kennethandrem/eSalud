package org.kennek.esalud.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kenne on 5/23/2017.
 */

public class Config {
    @SerializedName("encoding")
    @Expose
    private String encoding;
    @SerializedName("sampleRateHertz")
    @Expose
    private Integer sampleRateHertz;
    @SerializedName("languageCode")
    @Expose
    private String languageCode;

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Integer getSampleRateHertz() {
        return sampleRateHertz;
    }

    public void setSampleRateHertz(Integer sampleRateHertz) {
        this.sampleRateHertz = sampleRateHertz;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
