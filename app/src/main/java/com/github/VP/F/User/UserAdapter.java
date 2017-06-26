package com.github.VP.F.User;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.M.Entity.User;
import com.github.R;
import com.imageloader.zqxImageLoader;

import java.util.List;

/**
 * Created by axnshy on 2017/6/21.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    List<User> users;
    LayoutInflater inflater;
    Context context;
    private OnItemClickLister mListener;

    public UserAdapter(Context context,List<User> users) {
        if(users ==null){
            throw new RuntimeException("List<User> parameters can't be null");
        }
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.item_recycler_user,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.itemView.setTranslationZ(viewHolder.itemView.getTranslationZ()+2);

        String url = users.get(i).getAvatarUrl();
        zqxImageLoader.Builder.build(context).bindBitmap(url,viewHolder.avatar, 0, 0);
        viewHolder.login.setText(users.get(i).getLogin());
        viewHolder.type.setText(users.get(i).getType());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null) {
                    mListener.onClick(v,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addOnItemClickListener(OnItemClickLister listener) {
        this.mListener = listener;
    }

    public interface OnItemClickLister{
        void onClick(View view,int positon);
    }


    protected class ViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView login;
        TextView type;


        public ViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            login = (TextView) itemView.findViewById(R.id.tv_login);
            type = (TextView) itemView.findViewById(R.id.tv_type);
        }
    }


}
