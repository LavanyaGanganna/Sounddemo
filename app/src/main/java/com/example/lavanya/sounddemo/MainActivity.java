package com.example.lavanya.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    // controlling volume with seekbar
    SeekBar seekBar;
    SeekBar seekbar2;
    //controlling volume
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(this,R.raw.jack);
        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxvolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curvolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekbar2=(SeekBar) findViewById(R.id.seekBar2);
        seekbar2.setMax(mediaPlayer.getDuration());
      
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekbar2.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0,600);
        seekBar.setMax(maxvolume);
        seekBar.setProgress(curvolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               // Log.i("seekbar changed",Integer.toString(i));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
                seekbar2.setProgress(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                    //mediaPlayer.getCurrentPosition();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               // mediaPlayer.start();
            }
        });
    }
    public void playaudio(View view){

        mediaPlayer.start();
    }
    public void pauseaudio(View view){
        mediaPlayer.pause();
    }
}
