/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package io.comhub.register.android.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import io.comhub.register.android.presentation.R;
import io.comhub.register.android.presentation.internal.di.HasComponent;
import io.comhub.register.android.presentation.internal.di.components.DaggerUserComponent;
import io.comhub.register.android.presentation.internal.di.components.UserComponent;
import io.comhub.register.android.presentation.internal.di.modules.UserModule;
import io.comhub.register.android.presentation.view.base.BaseActivity;
import io.comhub.register.android.presentation.view.fragment.UserDetailsFragment;

/**
 * Activity that shows details of a certain user.
 */
public class UserDetailsActivity extends BaseActivity implements HasComponent<UserComponent> {

  private static final String INTENT_EXTRA_PARAM_USER_ID = "org.android10.INTENT_PARAM_USER_ID";
  private static final String INSTANCE_STATE_PARAM_USER_ID = "org.android10.STATE_PARAM_USER_ID";

  public static Intent getCallingIntent(Context context, String userId) {
    Intent callingIntent = new Intent(context, UserDetailsActivity.class);
    callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);
    return callingIntent;
  }

  private String userId;
  private UserComponent userComponent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);

    this.initializeActivity(savedInstanceState);
    this.initializeInjector();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    if (outState != null) {
      outState.putString(INSTANCE_STATE_PARAM_USER_ID, this.userId);
    }
    super.onSaveInstanceState(outState);
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      this.userId = getIntent().getStringExtra(INTENT_EXTRA_PARAM_USER_ID);
      addFragment(R.id.fragmentContainer, new UserDetailsFragment());
    } else {
      this.userId = savedInstanceState.getString(INSTANCE_STATE_PARAM_USER_ID);
    }
  }

  private void initializeInjector() {
    this.userComponent = DaggerUserComponent.builder()
                                            .applicationComponent(getApplicationComponent())
                                            .activityModule(getActivityModule())
                                            .userModule(new UserModule(this.userId))
                                            .build();
  }

  @Override
  public UserComponent getComponent() {
    return userComponent;
  }
}
