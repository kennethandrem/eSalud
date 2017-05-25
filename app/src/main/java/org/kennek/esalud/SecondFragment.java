package org.kennek.esalud;

import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.kennek.esalud.interfaces.SpeechInterface;
import org.kennek.esalud.models.SpeechApiRequest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import at.markushi.ui.CircleButton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MediaRecorder grabacion;
    private final String encoding = "FLAC";
    private final String sampleRateHertz = "16000";
    private final String languageCode = "en-ES";
    private  String outputFile = null;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    Date fecha;
    private Uri downloadUrl;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SecondFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance() {
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView2 = inflater.inflate(R.layout.fragment_second, container, false);
        final CircleButton btnRec = (CircleButton) rootView2.findViewById(R.id.btnRec);
        final CircleButton btnStop = (CircleButton) rootView2.findViewById(R.id.btnStop);
        //agregar fecha para identificar los diferentes audios
        fecha = new Date();

        File folder = new File(Environment.getExternalStorageDirectory() + "/eSalud");
        boolean success = true;
        if (!folder.exists()) {
            //Toast.makeText(MainActivity.this, "Directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        if (success) {
            outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() +"/eSalud/"+fecha+".mp3";
            Toast.makeText(getActivity(), outputFile, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Failed - Error", Toast.LENGTH_SHORT).show();
        }

        grabacion = new MediaRecorder();
        grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabacion.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        grabacion.setAudioEncoder(MediaRecorder.OutputFormat.MPEG_4);
        grabacion.setOutputFile(outputFile);

        btnStop.setEnabled(false);

        btnRec.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "¡Estoy escuchando al paciente!", Snackbar.LENGTH_SHORT);
                snackbar.show();
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                try {
                    grabacion.prepare();
                    grabacion.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                btnStop.setEnabled(true);

                return false;
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabacion.stop();
                grabacion.release();
                grabacion = null;
                btnStop.setEnabled(false);
                btnRec.setEnabled(true);
                Snackbar snackbar = Snackbar
                        .make(v, "¡Grabado con exito!", Snackbar.LENGTH_SHORT);
                snackbar.show();
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                byte[] buffer = new byte[1024];
                SpeechApiRequest speechApi = new SpeechApiRequest();

                StorageReference storageRef = storage.getReferenceFromUrl("gs://esalud-f8523.appspot.com");
                Uri file = Uri.fromFile(new File("storage/emulated/0/eSalud/"+fecha+".mp3"));

                // Create the file metadata
                StorageMetadata metadata = new StorageMetadata.Builder()
                        .setContentType("audio/mpeg")
                        .build();
                // Upload file and metadata to the path 'audio/audio.mp3'
                UploadTask uploadTask = storageRef.child("eSalud/"+file.getLastPathSegment()).putFile(file, metadata);

// Listen for state changes, errors, and completion of the upload.
                uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        System.out.println("Upload is % IN PROGRESS");
                    }
                }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                        System.out.println("Upload is paused");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://speech.googleapis.com/v1/")
                                .addConverterFactory(GsonConverterFactory
                                        .create())
                                .build();
                        SpeechInterface speechInterface = retrofit.create(SpeechInterface.class);

                        /*JSONObject json = new JSONObject();
                        JSONObject audiojson = new JSONObject();
                        JSONObject configjson = new JSONObject();

                        try {
                            audiojson.put("uri", "gs://esalud-f8523.appspot.com/eSalud" + fecha + ".mp3");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            configjson.put("encoding", "FLAC");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            configjson.put("sampleRateHertz", 16000);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            configjson.put("languageCode", "es_ES");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            configjson.put("maxAlternatives", 1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            configjson.put("profanityFilter", false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            json.put("config", configjson);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            json.put("audio", audiojson);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/


                    }
                });
            }
        });
        return rootView2;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
