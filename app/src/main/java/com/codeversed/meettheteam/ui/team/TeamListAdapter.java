package com.codeversed.meettheteam.ui.team;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.codeversed.meettheteam.R;
import com.codeversed.meettheteam.vo.Teammate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class TeamListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int TYPE_ITEM = 0;

  private final LayoutInflater inflater;
  private final ClickCallback callback;

  private List<Teammate> teamData = new ArrayList<>();

  public TeamListAdapter(Context context, ClickCallback callback) {
    inflater = LayoutInflater.from(context);
    this.callback = callback;
    Collections.sort(this.teamData, (item, t1) -> {
      String s1 = item.getFullName();
      String s2 = t1.getFullName();
      return s1.compareToIgnoreCase(s2);
    });
  }

  public void setData(List<Teammate> teamData) {
    if (teamData.size() == 0) return;
    this.teamData.clear();
    this.teamData.addAll(teamData);
    notifyDataSetChanged();
  }

  private Object getItem(int position) {
    return teamData.get(position);
  }

  @Override public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    switch (viewType) {
      case TYPE_ITEM:
        return new ItemViewHolder(inflater.inflate(R.layout.item_teammate, parent, false));
    }

    return null;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    // Currently only one type but setup for easy expansion
    switch (getItemViewType(position)) {
      case TYPE_ITEM:
        bindItemHolder((ItemViewHolder) holder, position);
    }

  }

  @Override public int getItemViewType(int position) {
    return TYPE_ITEM;
  }

  @Override public int getItemCount() {
    return teamData.size();
  }

  private void bindItemHolder(ItemViewHolder vh, int position) {
    Teammate tm = (Teammate) getItem(position);
    if (tm != null) {
      vh.name.setText(tm.getFullName());
      vh.title.setText(tm.getTitle());
      vh.bio.setText(tm.getBio());

      if (!TextUtils.isEmpty(tm.getAvatar())) {
        Glide.with(vh.avatar.getContext())
            .load(tm.getAvatar())
            .apply(RequestOptions
                       .circleCropTransform()
                       .diskCacheStrategy(DiskCacheStrategy.ALL))
            .into(vh.avatar);
      }

      vh.constraintLayout.setTag(tm);
      vh.constraintLayout.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          if (callback != null) {
            callback.onClick((Teammate) view.getTag());
          }
        }
      });
    }
  }

  static final class ItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.constraint_layout) ConstraintLayout constraintLayout;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.bio) TextView bio;
    @BindView(R.id.avatar) ImageView avatar;

    ItemViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface ClickCallback {
    void onClick(Teammate teammate);
  }

}