package no.uia.yannis11.skompispt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class WatchVideoActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_watch_video);
		
		// Set a dummy video
		final VideoView videoView = (VideoView) findViewById(R.id.videoView);
		videoView.setVideoPath("http://media.uia.no/files/344-multimedia-teaser.mp4");
		
		// Create playback controls
		MediaController mediaController = new MediaController(this);
		mediaController.setAnchorView(videoView);
		videoView.setMediaController(mediaController);
		
		// Make sure the view is at front and focused
		videoView.bringToFront();
		videoView.requestFocus();
		
		// Start playback
		videoView.start();
	}
}
