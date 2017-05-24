package org.kennek.esalud.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kenne on 5/23/2017.
 */

public class SpeechApiRequest {
    @SerializedName("config")
    @Expose
    private Config config;
    @SerializedName("audio")
    @Expose
    private Audio audio;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

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

    public class Audio {
        @SerializedName("uri")
        @Expose
        private String uri;

        public String getUri() {
            return uri;
        }

        public String setUri(String uri) {
            this.uri = uri;
            return uri;
        }

    }
}
