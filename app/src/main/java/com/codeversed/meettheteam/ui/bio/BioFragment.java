package com.codeversed.meettheteam.ui.bio;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.codeversed.meettheteam.R;
import com.codeversed.meettheteam.ui.BioActivity;
import com.codeversed.meettheteam.vo.Teammate;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codeversed.meettheteam.ui.BioActivity.ARG_TM;
import static com.codeversed.meettheteam.utils.AnimationHelper.OnKenBurnsEventListener;
import static com.codeversed.meettheteam.utils.AnimationHelper.animateKenBurns;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class BioFragment extends Fragment implements OnKenBurnsEventListener {

  protected BioActivity activity;

  @BindView(R.id.nested_scroll) NestedScrollView nestedScrollView;
  @BindView(R.id.card_view) CardView cardView;
  @BindView(R.id.bio) TextView bio;

  public BioFragment() {}

  @Override public View onCreateView(LayoutInflater li, ViewGroup container, Bundle state) {
    View rootView = li.inflate(R.layout.fragment_bio, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    activity = ((BioActivity) getActivity());

    // Fetches Teammate object from extras
    if (activity != null && activity.getIntent() != null) {
      Bundle args = activity.getIntent().getExtras();
      if (args != null && args.containsKey(ARG_TM)) {
        Teammate tm = args.getParcelable(ARG_TM);
        if (tm != null) {
          // Setup toolbar and teammate specific fields
          activity.collapsingToolbar.setTitle(tm.getFullName());
          bio.setText(tm.getBio());
          activity.title.setText(tm.getTitle());
          Glide.with(activity.avatar.getContext())
              .load(tm.getAvatar())
              .apply(RequestOptions
                         .centerCropTransform()
                         .diskCacheStrategy(DiskCacheStrategy.ALL))
              .into(activity.avatar);
          animateKenBurns(activity.avatar, this, -99999, -99999, -99999);
        }
      }

      activity.appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {

        float range = appBarLayout.getTotalScrollRange();
        float calc = Math.abs(verticalOffset / range);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          appBarLayout.setElevation(0);
        }

        nestedScrollView.setTranslationY(1f + calc * 200);

      });

    }

  }

  @Override public void onKenBurnsEnd(float toTransX, float toTransY, float toScale) {
    animateKenBurns(activity.avatar, this, toTransX, toTransY, toScale);
  }
}
