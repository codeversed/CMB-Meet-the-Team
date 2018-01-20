package com.codeversed.meettheteam.ui.team;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.codeversed.meettheteam.MainActivity;
import com.codeversed.meettheteam.R;
import com.codeversed.meettheteam.ui.BioActivity;
import com.codeversed.meettheteam.utils.AssetReader;
import com.codeversed.meettheteam.utils.AutoClearedValue;
import com.codeversed.meettheteam.viewmodel.TeamViewModel;

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
  private AutoClearedValue<TeamListAdapter> adapter;
  private TeamListAdapter teamListAdapter;
  private LinearLayoutManager layoutManager;
  private boolean isCards;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;

  public TeamFragment() {}

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override public View onCreateView(LayoutInflater li, ViewGroup container, Bundle state) {
    View rootView = li.inflate(R.layout.fragment_team, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    activity = ((MainActivity) getActivity());

    if (activity != null) {

      TeamViewModel teamViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);
      AssetReader.loadJSONFromAsset(activity.getAssets(), teamViewModel);

      // Creates a new TeamListAdapter instance with item click callback
      // We pass the MainActivity list of teammates in constructor
      teamListAdapter = new TeamListAdapter(activity, isCards, teammate -> {
        // Pass parcelable Teammate object to BioActivity
        Intent intent = new Intent(activity, BioActivity.class);
        intent.putExtra(ARG_TM, teammate);
        activity.startActivity(intent);
      });

      adapter = new AutoClearedValue<>(this, teamListAdapter);

      setupAdapters();

      teamViewModel.getTeam().observe(this, teamList -> {
        if (teamList == null) return;
        adapter.get().setData(teamList);
      });

    }

  }

  private void setupAdapters() {
    adapter = new AutoClearedValue<>(this, teamListAdapter);

    recyclerView.setAdapter(adapter.get());
    layoutManager = new LinearLayoutManager(getActivity());
    layoutManager.setItemPrefetchEnabled(true);
    recyclerView.setLayoutManager(layoutManager);
    if (!isCards) {
      recyclerView.addItemDecoration(new DividerItemDecoration(activity, VERTICAL));
      recyclerView.setHasFixedSize(true);
    } else {
      if (recyclerView.getItemDecorationCount() >= 0) {
        recyclerView.removeItemDecorationAt(0);
      }
      recyclerView.setHasFixedSize(false);
    }
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.team_menu, menu);
  }

  @Override public void onPrepareOptionsMenu(Menu menu) {
    MenuItem item = menu.findItem(R.id.action_list_style);
    item.setIcon(!isCards ? R.drawable.ic_cards_24dp : R.drawable.ic_list_24dp);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_list_style) {
      isCards = !isCards;
      item.setIcon(!isCards ? R.drawable.ic_cards_24dp : R.drawable.ic_list_24dp);
      teamListAdapter.setIsCards(isCards);
      recyclerView.getRecycledViewPool().clear();
      setupAdapters();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
