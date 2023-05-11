package com.ifsc.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    ImageButton bntPlay,stop, pausa;
    SeekBar seekBarTime, seekBarVolume;
    TextView tvTime, tvNome;
    private AudioManager audioManager;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.seminovos);

        bntPlay = findViewById(R.id.bntPlay);
        stop = findViewById(R.id.stop);
        pausa = findViewById(R.id.pausa);
        bntPlay.setOnClickListener(this);
        pausa.setOnClickListener(this);
        stop.setOnClickListener(this);
        seekBarVolume =findViewById(R.id.seekBarVolume);
        seekBarTime = findViewById(R.id.seekBarTime);
        tvNome=findViewById(R.id.tvNome);
        tvTime = findViewById(R.id.tvTime);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        inicializaSeekBar();
        timerCounter();

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,AudioManager.FLAG_SHOW_UI);
                seekBar.setMax(mediaPlayer.getDuration());
                mediaPlayer.seekTo(seekBarTime.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void inicializaSeekBar(){
        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBarVolume.setMax(volumeMaximo);
        seekBarVolume.setProgress(volumeAtual);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bntPlay:
                mediaPlayer.start();
                timerCounter();
                break;
            case R.id.pausa:
                mediaPlayer.pause();
                break;
            case R.id.stop:
                if (this.mediaPlayer!=null && mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.seminovos);
                }
                break;
        }
    }

    public String convertDurationMillis(Integer getDurationInMillis){

        int getDurationMillis = getDurationInMillis;
        String convertHours = String.format("%02d", TimeUnit.MILLISECONDS.toHours(getDurationMillis));
        String convertMinutes = String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(getDurationMillis));
        String convertSeconds = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(getDurationMillis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getDurationMillis)));

        String getDuration = convertHours + ":" + convertMinutes + ":" + convertSeconds;

        return getDuration;
    }

    public  void atualizaTime() {
        tvTime.setText(convertDurationMillis(mediaPlayer.getCurrentPosition()) +
                "/" + convertDurationMillis(mediaPlayer.getDuration()));
        mediaPlayer.getCurrentPosition();

        seekBarTime.setMax(mediaPlayer.getDuration());
    }
    private Timer timer;
    private void timerCounter(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        atualizaTime();
                        seekBarTime.setProgress(mediaPlayer.getCurrentPosition());
                    }
                });
            }
        }, 0, 1000);
    }

}