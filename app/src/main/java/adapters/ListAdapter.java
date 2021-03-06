package adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import Class.*;
import java.util.ArrayList;
import java.util.List;
import DTO.DtoUser;
import com.edibca.databasemanagement.*;
import com.squareup.picasso.Picasso;

/**
 * Created by DIEGO CASALLAS  on 23/02/2016.
 */
public class ListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<DtoUser> data;
    private View view;
    private RelativeLayout relativeLayout;
    private int iWidth, iHeight;

    public ListAdapter(Context context, ArrayList<DtoUser> data) {
        this.context = context;
        this.data = data;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();

        iWidth = display.getWidth();
        iHeight = context.getResources().getDimensionPixelSize(R.dimen.template_img_height);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        view=convertView;
        try {
            if(view==null)
            {
                LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view=layoutInflater.inflate(R.layout.list_adapter,null);
            }
            DtoUser dtoUser=(DtoUser)  getItem(position);
            TextView textView=(TextView)view.findViewById(R.id.titleName);
            textView.setText(dtoUser.getsName() + "  " + dtoUser.getsLast_Name());

            final TextView textViewMail=(TextView)view.findViewById(R.id.textEmail);
            textViewMail.setText(dtoUser.getsMail());

            final ImageView imageView=(ImageView)view.findViewById(R.id.img);
            imageView.setImageURI(Uri.parse(dtoUser.getsUri()));
            //imageView.setImageBitmap(sizeImage(context.getResources(), dtoUser.getiImgUrl()));
           /* imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Is  mail :" + textViewMail.getText(), Toast.LENGTH_LONG).show();
                }
            });*/
            relativeLayout=(RelativeLayout)view.findViewById(R.id.container);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "Is  mail :" + textViewMail.getText(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, UserDetailActivity.class);
                    //intent.putExtra("email",textViewMail.getText());

                    General.EMAIL_USER=textViewMail.getText().toString();
                    context.startActivity(intent);
                }
            });
            //Picasso.with(context).load(postre.getSimgurl()).resize(iWidth,iHeight).centerCrop().into(imageView);
        }
        catch(Exception e)
        {
            Log.w("Is  error:", e.getMessage());
        }

        return view;
    }
    public Bitmap  sizeImage(Resources resources,int iID){

        BitmapFactory.Options options= new BitmapFactory.Options();
        options.inDither = false;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = 3;
        options.inPurgeable = true;
        Bitmap icon = BitmapFactory.decodeResource(resources,iID,options);
        return icon;
    }
}
