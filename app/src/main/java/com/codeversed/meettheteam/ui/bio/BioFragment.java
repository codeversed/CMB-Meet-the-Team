package com.codeversed.meettheteam.ui.bio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeversed.meettheteam.R;
import com.codeversed.meettheteam.ui.BioActivity;
import com.codeversed.meettheteam.vo.Teammate;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codeversed.meettheteam.ui.BioActivity.ARG_TM;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class BioFragment extends Fragment {

  protected BioActivity activity;

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
        }
      }
    }

  }

}
