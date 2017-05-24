package org.kennek.esalud.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kenne on 5/24/2017.
 */

public class Result {
    @SerializedName("alternatives")
    @Expose
    private List<Alternative> alternatives = null;

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }
}
