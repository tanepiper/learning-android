package org.ifies.android;

import android.app.Activity; 
import android.content.Context; 
import android.content.res.TypedArray; 
import android.database.Cursor; 
import android.net.Uri; 
import android.os.Bundle; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.AdapterView; 
import android.widget.BaseAdapter; 
import android.widget.Gallery; 
import android.widget.ImageView; 
import android.widget.Toast; 
import android.widget.AdapterView.OnItemClickListener; 
import android.provider.MediaStore; 

public class GalleryExample extends Activity { 
           
    private Context mContext; 
    private Cursor cursor; 
    private int column_index; 
    private Gallery g; 

    @Override 
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.gallery_example1); 
           
        String [] proj={MediaStore.Images.Thumbnails._ID}; 
        cursor = managedQuery( MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, 
                proj, // Which columns to return 
                null,       // WHERE clause; which rows to return (all rows) 
                null,       // WHERE clause selection arguments (none) 
                null); // Order-by clause (ascending by name) 
        column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID); 
        // Reference the Gallery view 
        g = (Gallery) findViewById(R.id.gallery); 

        // Set the adapter to our custom adapter (below) 
        g.setAdapter(new ImageAdapter(this)); 
        
        // Set a item click listener, and just Toast the clicked position 
        g.setOnItemClickListener(new OnItemClickListener() { 
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) { 
                
               String [] proj={MediaStore.Images.Media.DATA}; 
                    cursor = managedQuery( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
                          proj, // Which columns to return 
                          null,       // WHERE clause; which rows to return (all rows) 
                          null,       // WHERE clause selection arguments (none) 
                          null); // Order-by clause (ascending by name) 
                  column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA); 
                     
                  cursor.moveToPosition((int) g.getSelectedItemId()); 
                  String filename = cursor.getString(column_index); 
                Toast.makeText(GalleryExample.this, filename, Toast.LENGTH_SHORT).show(); 
            } 
        });         
    } 

    public class ImageAdapter extends BaseAdapter { 
        int mGalleryItemBackground; 

        public ImageAdapter(Context c) { 
            mContext = c; 
            // See res/values/attrs.xml for the <declare-styleable> that defines 
            // Gallery1. 
            TypedArray a = obtainStyledAttributes(R.styleable.Gallery1); 
            mGalleryItemBackground = a.getResourceId( 
                    R.styleable.Gallery1_android_galleryItemBackground, 0); 
            a.recycle(); 
        } 

        public int getCount() { 
          return cursor.getCount(); 
        } 

        public Object getItem(int position) { 
            return position; 
        } 

        public long getItemId(int position) { 
            return position; 
        } 

        public View getView(int position, View convertView, ViewGroup parent) { 
          ImageView i = new ImageView(mContext); 
          if (convertView == null) { 
               cursor.moveToPosition(position); 
                    int id = cursor.getInt(column_index); 
                    i.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, ""+id)); 
                    i.setScaleType(ImageView.ScaleType.FIT_XY); 
                    i.setLayoutParams(new Gallery.LayoutParams(136, 88)); 
                    // The preferred Gallery item background 
                    i.setBackgroundResource(mGalleryItemBackground); 
          } 
               return i; 
        } 
      } 
} 