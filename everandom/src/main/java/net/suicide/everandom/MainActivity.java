package net.suicide.everandom;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity
{
   static {
      System.loadLibrary("msg");
   }
   public native String msg();
   /*
   String msg(){
      return "Wednesday is Sunny.";
   }
   */

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView txt = (TextView)findViewById(R.id.txt);
        txt.setText(msg());
    }
}
