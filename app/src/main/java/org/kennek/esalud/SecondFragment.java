package org.kennek.esalud;

import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kennek.esalud.interfaces.SpeechInterface;
import org.kennek.esalud.models.Audio;
import org.kennek.esalud.models.Config;
import org.kennek.esalud.models.SpeechApiRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    private  String outputFile = null;
    Date fecha;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://speech.googleapis.com/v1")
                .addConverterFactory(GsonConverterFactory
                        .create())
                .build();
        SpeechInterface service = retrofit.create(SpeechInterface.class);

        File folder = new File(Environment.getExternalStorageDirectory() + "/eSalud");
        boolean success = true;
        if (!folder.exists()) {
            //Toast.makeText(MainActivity.this, "Directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        if (success) {
            //Toast.makeText(getActivity(), "Directory Created", Toast.LENGTH_SHORT).show();
            outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() +"/eSalud/"+fecha+".flac";
        } else {
            //Toast.makeText(MainActivity.this, "Failed - Error", Toast.LENGTH_SHORT).show();
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
                Audio audio = new Audio();
                //audio.setUri();
                Config config = new Config();
                //config.setEncoding();
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
