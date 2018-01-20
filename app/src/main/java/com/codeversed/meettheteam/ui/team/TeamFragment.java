package com.codeversed.meettheteam.ui.team;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeversed.meettheteam.MainActivity;
import com.codeversed.meettheteam.R;
import com.codeversed.meettheteam.ui.BioActivity;
import com.codeversed.meettheteam.vo.Teammate;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.LinearLayout.VERTICAL;
import static com.codeversed.meettheteam.ui.BioActivity.ARG_TM;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class TeamFragment extends Fragment {

  protected MainActivity activity;

  @BindView(R.id.recycler_view) RecyclerView recyclerView;

  public TeamFragment() {}

  @Override public View onCreateView(LayoutInflater li, ViewGroup container, Bundle state) {
    View rootView = li.inflate(R.layout.fragment_team, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    activity = ((MainActivity) getActivity());

    if (activity != null) {

      // Creates a new TeamListAdapter instance with item click callback
      // We pass the MainActivity list of teammates in constructor
      TeamListAdapter adapter = new TeamListAdapter(
          activity, activity.getTeammates(), new TeamListAdapter.ClickCallback() {

        @Override public void onClick(Teammate teammate) {

          // Pass parcelable Teammate object to BioActivity
          Intent intent = new Intent(activity, BioActivity.class);
          intent.putExtra(ARG_TM, teammate);
          activity.startActivity(intent);

        }

      });

      recyclerView.setAdapter(adapter);
      recyclerView.setLayoutManager(new LinearLayoutManager(activity));
      recyclerView.addItemDecoration(new DividerItemDecoration(activity, VERTICAL));

    }

  }

}
