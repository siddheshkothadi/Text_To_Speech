package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    EditText input;
    Button spk_button;
    TextToSpeech TTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TTS = new TextToSpeech(this, this);
        input = findViewById(R.id.input_field);
        spk_button = findViewById(R.id.speak_button);
        spk_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS){
            int result = TTS.setLanguage(Locale.getDefault());
            TTS.setSpeechRate(1);
            TTS.setPitch(1);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Language not supported.");
            }
            else{
                spk_button.setEnabled(true);
                speak();
            }
        }else{
            Log.e("TextToSpeech","Initialization Failed");
        }
    }

    public void speak(){
        String message = input.getText().toString();
        TTS.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
    }

}