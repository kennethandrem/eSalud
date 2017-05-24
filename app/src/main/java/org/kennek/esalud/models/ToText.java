package org.kennek.esalud.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by kenne on 5/23/2017.
 */

public class ToText {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("DiagnosticStr")
    @Expose
    private String diagnostico;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

}
