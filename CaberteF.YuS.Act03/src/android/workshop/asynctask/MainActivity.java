package android.workshop.asynctask;

import java.io.InputStream;
import java.net.URL;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private final int maxImg = 5;
	//private ImageStruct[] img = new ImageStruct[maxImg];
	private ImageView[] img = new ImageView[maxImg]; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		img[0]=(ImageView) findViewById(R.id.image1);
        img[1]=(ImageView) findViewById(R.id.image2);
        img[2]=(ImageView) findViewById(R.id.image3);
        img[3]=(ImageView) findViewById(R.id.image4);
        img[4]=(ImageView) findViewById(R.id.image5);
        
		for(int i=0; i<maxImg; i++)
		{
			String url = getImageUrl(i);
			img[i].setTag(url);
		}
		
		new DownloadImageTask().execute(img);
		/*ImageAdapter adapter = new ImageAdapter(this, R.layout.image_list, img);
		ListView list = (ListView) findViewById(R.id.listview);
		list.setAdapter(adapter);*/
	}
	
	private class DownloadImageTask extends AsyncTask<ImageView, Void, Drawable[]> {
		
		Drawable[] response = new Drawable[maxImg];
		ImageView[] iv = new ImageView[maxImg];
		ProgressDialog pd = new ProgressDialog(MainActivity.this);
		
		protected void onPreExecute(){
	        pd.setMessage("Initializing Images...");
	        pd.show();
	    }
		
		@Override
		protected Drawable[] doInBackground(ImageView... imageViews) {
			
			for(int i=0; i<maxImg; i++)
			{
				try {
					iv[i] = imageViews[i];
					URL url = new URL((String) imageViews[i].getTag());
					InputStream input = (InputStream) url.getContent();
					response[i] = Drawable.createFromStream(input, "src");
				} 
				
				catch(Exception e) {
					e.printStackTrace();		
				}
			}
			
			return response;
		}
		
		protected void onPostExecute(Drawable[] drawable) {
			pd.dismiss();
			for(int i=0; i<maxImg; i++)
				iv[i].setImageDrawable(drawable[i]);
		}
	}
	
	private String getImageUrl(int i) {
		String url;
		switch(i)
		{
			case 0: url = "http://ww1.prweb.com/prfiles/2010/05/11/1751474/gI_TodoforiPadAppIcon512.png.jpg";
					break;
			
			case 1: url = "http://cdn4.iosnoops.com/wp-content/uploads/2011/08/Icon-Gmail_large-250x250.png";
					break;
					
			case 2: url = "http://kelpbeds.files.wordpress.com/2012/02/lens17430451_1294953222linkedin-icon.jpg?w=450";
					break;
			
			case 3: url = "http://snapknot.com/blog/wp-content/uploads/2010/03/facebook-icon-copy.jpg";
					break;
			
			case 4: url = "https://lh3.googleusercontent.com/-ycDGy_fZVZc/AAAAAAAAAAI/AAAAAAAAAAc/Q0MmjxPCOzk/s250-c-k/photo.jpg";
					break;
					
			default: url = "";
		}
		return url;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
