package com.codeversed.meettheteam.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeversed.meettheteam.R;
import com.codeversed.meettheteam.ui.bio.BioFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.design.widget.CollapsingToolbarLayout.LayoutParams;
import static com.codeversed.meettheteam.utils.UiHelper.fixAppBarOnDrag;
import static com.codeversed.meettheteam.utils.UiHelper.getStatusBarHeight;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class BioActivity extends AppCompatActivity {

  public static final String ARG_TM = "ARG_TM";
  public static final String TAG_BIO = "TAG_BIO";

  @BindView(R.id.collapsing_toolbar) public CollapsingToolbarLayout collapsingToolbar;
  @BindView(R.id.title) public TextView title;
  @BindView(R.id.toolbar_hero_image) public ImageView avatar;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.app_bar) AppBarLayout appBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bio);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    int titleColor = ContextCompat.getColor(this, R.color.colorToolbarText);
    collapsingToolbar.setCollapsedTitleTextColor(titleColor);
    collapsingToolbar.setExpandedTitleColor(titleColor);

    // Dynamically offset toolbar for translucent status
    LayoutParams lp = (LayoutParams) toolbar.getLayoutParams();
    lp.setMargins(0, getStatusBarHeight(this), 0, 0);
    toolbar.setLayoutParams(lp);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.container, new BioFragment(), TAG_BIO)
          .commitAllowingStateLoss();
      getSupportFragmentManager().executePendingTransactions();
    }

  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    appBar.post(() -> {
      // Removes onDrag (scrolling) when touching the appBar itself
      fixAppBarOnDrag(appBar);
    });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

}
