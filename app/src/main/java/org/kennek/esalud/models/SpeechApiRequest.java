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
}
