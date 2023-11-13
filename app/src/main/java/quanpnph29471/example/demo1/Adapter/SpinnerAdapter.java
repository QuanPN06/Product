package quanpnph29471.example.demo1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import quanpnph29471.example.demo1.Model.Category;
import quanpnph29471.example.demo1.Model.Product;
import quanpnph29471.example.demo1.R;

public class SpinnerAdapter extends BaseAdapter {
    List<Category> list;
    Context context;

    public SpinnerAdapter(List<Category> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view==null){
            row =View.inflate(context,R.layout.layout_item_cat,null);
        }else
            row =view;
        TextView tv_id = row.findViewById(R.id.tv_id);
        TextView tv_cat = row.findViewById(R.id.tv_name);

        tv_id.setText(list.get(i).getId()+"");
        tv_cat.setText(list.get(i).getName());
        return row;
    }
}
