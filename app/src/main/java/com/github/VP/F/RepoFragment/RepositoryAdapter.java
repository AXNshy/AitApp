package com.github.VP.F.RepoFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.M.Entity.Repository;
import com.github.R;

import java.util.List;

/**
 * Created by axnshy on 2017/6/21.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    LayoutInflater inflater;
    private OnItemClickListener mClickListener;

    private static final int TYPE_CONTENT = 1;
    private static final int TYPE_FOOTER = 2;


    //判断是否显示Footer
    int footerVisibility = View.GONE;


    public RepositoryAdapter(Context context, List<Repository> mData) {
        if(mData == null){
            throw new RuntimeException("the mData parameters can't be null,please check program");
        }
        this.mData = mData;
        inflater = LayoutInflater.from(context);
    }

    private List<Repository> mData;

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return TYPE_FOOTER;
        }else {
            return TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if(i == TYPE_CONTENT)
            return new ContentViewHolder(inflater.inflate(R.layout.item_recycler_repo, viewGroup, false));
        else if(i == TYPE_FOOTER){
            return new FooterViewHolder(inflater.inflate(R.layout.recyclerview_footer, viewGroup, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if(getItemViewType(i) == TYPE_FOOTER){
            ( (FooterViewHolder)viewHolder).setVisibility(footerVisibility==View.GONE?false:true);
        }else {

            ( (ContentViewHolder)viewHolder).text.setText(mData.get(i).getName());
            ( (ContentViewHolder)viewHolder).owner.setText(mData.get(i).getOwner().getLogin());
            ( (ContentViewHolder)viewHolder).des.setText(mData.get(i).getDescription());

            ( (ContentViewHolder)viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick(v, i);
                    }
                }
            });

            ( (ContentViewHolder)viewHolder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mClickListener != null)
                        mClickListener.onItemLongClick(v, i);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size()+1;
    }

    public void setFooterVisibility(int visible) {
        footerVisibility = visible;
        notifyItemChanged(getItemCount()-1);
    }

    protected class ContentViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        TextView des;
        TextView owner;
        public ContentViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv_name);
            des = (TextView) itemView.findViewById(R.id.tv_des);
            owner = (TextView) itemView.findViewById(R.id.tv_owner);

        }
    }

    protected class FooterViewHolder extends RecyclerView.ViewHolder{
        FrameLayout load;

        public FooterViewHolder(View itemView) {
            super(itemView);
            load = (FrameLayout) itemView.findViewById(R.id.frame_load);
        }
        public void setVisibility(boolean isVisible){
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)itemView.getLayoutParams();
            if (isVisible){
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                param.width = LinearLayout.LayoutParams.MATCH_PARENT;
                itemView.setVisibility(View.VISIBLE);
                footerVisibility = View.VISIBLE;
            }else{
                itemView.setVisibility(View.GONE);
                param.height = 0;
                param.width = 0;
                footerVisibility = View.GONE;
            }
            itemView.setLayoutParams(param);
        }
    }

    /*
    * OnclickListener 接口
    *
    * */

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
        void onItemLongClick(View v,int position);
    }

    public void addOnItemClickListener(OnItemClickListener listener){
        this.mClickListener = listener;
    }

    public void removeOnItemClickListener(){
        this.mClickListener = null;
    }



}
