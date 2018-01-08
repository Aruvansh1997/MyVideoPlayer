package com.aruvansh.myvideoplayer;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = "VideoActivity";
    static final String googleapikey="enter your key here";
    static final String videoid="2Vv-BfVoq4g";
    static final String playlistid="PLI_7Mg2Z_-4I-W_lI55D9lBUkC66ftHMg";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_video);
//        ConstraintLayout constraintLayout=(ConstraintLayout)findViewById(R.id.videoplayer);
        //or you can use this
        ConstraintLayout layout=(ConstraintLayout)getLayoutInflater().inflate(R.layout.activity_video,null);
        setContentView(layout);

//       button declaring width=800dp and height=80dp
//        Button button1=new Button(this);
//        button1.setLayoutParams(new ConstraintLayout.LayoutParams(300,80));
//        button1.setText("addedfromjava");
//        layout.addView(button1);
//       button code done

        YouTubePlayerView playerView=new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        playerView.initialize(googleapikey,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasrestored) {
         
        Log.d(TAG,"oninitiazationsuccess"+provider.getClass().toString());
        Toast.makeText(this,"initialization was a success",Toast.LENGTH_LONG).show();
        youTubePlayer.setFullscreen(true);
        youTubePlayer.setPlaybackEventListener(event);
        if(!wasrestored)
        {
            youTubePlayer.cuePlaylist(videoid);
        }





    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        final int requestcode=1;//any arbiitrary value
        if(youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(this,requestcode).show();

        }
        else
        {
            String error=String.format("While initialization this is the error (%1$s)",youTubeInitializationResult.toString());
            Toast.makeText(this,error,Toast.LENGTH_LONG);   //used for notifying users performs its exact definition
        }




    }


    private YouTubePlayer.PlaybackEventListener event=new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(VideoActivity.this,"it is playing great hustle",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(VideoActivity.this,"it is paused concentrate man",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onStopped() {
            Toast.makeText(VideoActivity.this,"video has ended or it is a playback error",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onBuffering(boolean b) {
            Toast.makeText(VideoActivity.this,"net is slow peeps",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onSeekTo(int i) {
            Toast.makeText(VideoActivity.this,"it is seeking the right moment",Toast.LENGTH_LONG).show();

        }
    };



}
