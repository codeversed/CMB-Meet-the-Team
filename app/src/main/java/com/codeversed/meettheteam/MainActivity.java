package com.codeversed.meettheteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.codeversed.meettheteam.ui.team.TeamFragment;
import com.codeversed.meettheteam.utils.AssetReader;
import com.codeversed.meettheteam.vo.Teammate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.design.widget.AppBarLayout.LayoutParams;
import static com.codeversed.meettheteam.utils.UiHelper.getStatusBarHeight;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class MainActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;

  List<Teammate> teammates = new ArrayList<>();

  public List<Teammate> getTeammates() {
    return teammates;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    // Dynamically offset toolbar for translucent status
    LayoutParams lp = (LayoutParams) toolbar.getLayoutParams();
    lp.setMargins(0, getStatusBarHeight(this), 0, 0);
    toolbar.setLayoutParams(lp);

    // Load json from assets folder using AssetManager
    String jsonString = AssetReader.loadJSONFromAsset(this);

    // Use TypeToken to load as a list with 'Teammate' object
    teammates = new Gson().fromJson(
        jsonString, new TypeToken<List<Teammate>>() {}.getType());

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.container, new TeamFragment())
          .commitAllowingStateLoss();
      getSupportFragmentManager().executePendingTransactions();
    }

  }

}
