package android.workshop.asynctask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends ArrayAdapter<ImageStruct>{
	
	Context context;
	int resourceId;
	ImageStruct[] imageArray;
	
	public ImageAdapter(Context context, int resourceId, ImageStruct[] imageArray) {
		super(context, resourceId, imageArray);
		this.context = context;
		this.resourceId = resourceId;
		this.imageArray = imageArray;
	}
	
	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(resourceId, parent, false);
		TextView name = (TextView)row.findViewById(R.id.text);
		ImageView image = (ImageView)row.findViewById(R.id.image);
		ImageStruct img = imageArray[pos];
		name.setText(img.name);
		image.setImageDrawable(img.image);
		return row;
	}
}
