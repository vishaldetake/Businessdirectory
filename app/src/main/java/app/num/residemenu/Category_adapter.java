package app.num.residemenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishal on 5/30/2017.
 */

public class Category_adapter extends ArrayAdapter<Category> {
    Context context;
    int mlayoutid;
    int elementid;
    ArrayList<Category> employeeslist;
    RecordHolder holder = null;

    public Category_adapter(Context context, int resource,int elementid ,ArrayList<Category> employeeslist) {
        super(context, resource,elementid ,employeeslist);
        this.context=context;
        this.mlayoutid=resource;
        this.elementid=elementid;
        this.employeeslist=employeeslist;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        if(row==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row=((LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(mlayoutid,parent,false);
            holder=new RecordHolder();
            holder.txtid=(TextView)row.findViewById(R.id.txtText);
            row.setTag(holder);

        }else {
            holder=(RecordHolder)row.getTag();
        }
        Category data=employeeslist.get(position);
        if(null!=data){
            ((TextView)row.findViewById(R.id.txtText)).setText(data.getCname());
            ImageView imageView=(ImageView)row.findViewById(R.id.imgThumb);
            Picasso.with(context)
                    .load(data.getImg())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(new RoundedTransformation(8,5))
                    .into(imageView);
        }
        return row;
    }

    private class RecordHolder {
        TextView txtid;
        ImageView img;

    }
}
