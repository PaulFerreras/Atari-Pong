package ataripong;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {

	private Clip player_bounce_clip, wall_bounce_clip, score_clip;
	
	public Sounds() {
		FileInputStream sound_file;
		BufferedInputStream buffered_input_stream;
		AudioInputStream audio_input_stream;
		
		//Player Bounce
		try {
			sound_file = new FileInputStream("D:/Users/pferr/Desktop/Workspace/SuperAwesomePingPong/Sounds/player bounce.wav");
			buffered_input_stream = new BufferedInputStream(sound_file);
			audio_input_stream = AudioSystem.getAudioInputStream(buffered_input_stream);
			player_bounce_clip = AudioSystem.getClip();
			player_bounce_clip.open(audio_input_stream);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			System.out.println("Could not read player bounce.wav file");
			e.printStackTrace();
		}
		
		//Wall Bounce
		try {
			sound_file = new FileInputStream("D:/Users/pferr/Desktop/Workspace/SuperAwesomePingPong/Sounds/wall bounce.wav");
			buffered_input_stream = new BufferedInputStream(sound_file);
			audio_input_stream = AudioSystem.getAudioInputStream(buffered_input_stream);
			wall_bounce_clip = AudioSystem.getClip();
			wall_bounce_clip.open(audio_input_stream);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			System.out.println("Could not read wall bounce.wav file");
			e.printStackTrace();
		}
		
		//Score
		try {
			sound_file = new FileInputStream("D:/Users/pferr/Desktop/Workspace/SuperAwesomePingPong/Sounds/score.wav");
			buffered_input_stream = new BufferedInputStream(sound_file);
			audio_input_stream = AudioSystem.getAudioInputStream(buffered_input_stream);
			score_clip = AudioSystem.getClip();
			score_clip.open(audio_input_stream);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			System.out.println("Could not read score.wav file");
			e.printStackTrace();
		}
		
	}
	
	public void playerBounce() {
		player_bounce_clip.start();
		player_bounce_clip.setFramePosition(0);
	}
	
	public void wallBounce() {
		wall_bounce_clip.start();
		wall_bounce_clip.setFramePosition(0);
	}
	
	public void score() {
		score_clip.start();
		score_clip.setFramePosition(0);
	}
	
}
