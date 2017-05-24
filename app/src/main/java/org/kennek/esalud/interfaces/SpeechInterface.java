package org.kennek.esalud.interfaces;

import org.kennek.esalud.models.SpeechApiRequest;
import org.kennek.esalud.models.SpeechApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by kenne on 5/24/2017.
 */

public interface SpeechInterface {
    @POST("/speech:recognize")
    Call<SpeechApiResponse>getSpeechApi(@Body SpeechApiRequest speechApiRequest);
}
