package org.kennek.esalud.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kenne on 5/24/2017.
 */

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