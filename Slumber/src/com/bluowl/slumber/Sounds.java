package com.bluowl.slumber;


import com.badlogic.gdx.Gdx;  
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;  

public class Sounds {  
    
  public static Music music;  

    
  public static void load () {  
        
      music = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/creepy_menu.mp3"));
 
  }  
    
  private static Sound loadSound (String filename) {  
      return Gdx.audio.newSound(Gdx.files.internal("assets/sounds/" + filename));  
  }  
    


}  