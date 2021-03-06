package com.codeversed.meettheteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.codeversed.meettheteam.ui.team.TeamFragment;

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

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.drawable.ic_import_contacts_24dp);

    // Dynamically offset toolbar for translucent status
    LayoutParams lp = (LayoutParams) toolbar.getLayoutParams();
    lp.setMargins(0, getStatusBarHeight(this), 0, 0);
    toolbar.setLayoutParams(lp);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.container, new TeamFragment())
          .commitAllowingStateLoss();
      getSupportFragmentManager().executePendingTransactions();
    }

  }

}
