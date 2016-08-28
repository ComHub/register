package io.comhub.register.android.presentation.view.user.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import io.comhub.register.android.presentation.R;
import io.comhub.register.android.presentation.internal.di.HasComponent;
import io.comhub.register.android.presentation.internal.di.components.DaggerUserComponent;
import io.comhub.register.android.presentation.internal.di.components.UserComponent;
import io.comhub.register.android.presentation.internal.di.modules.UserModule;
import io.comhub.register.android.presentation.view.activity.BaseActivity;

public class UserRegisterActivity extends BaseActivity implements HasComponent<UserComponent> {

  private UserComponent userComponent;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, UserRegisterActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_layout);

    this.initializeActivity(savedInstanceState);
    this.initializeInjector();
  }

  @Override
  public UserComponent getComponent() {
    return this.userComponent;
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    addFragment(R.id.fragmentContainer, new UserRegisterFragment());
  }

  private void initializeInjector() {
    this.userComponent = DaggerUserComponent.builder()
                                            .applicationComponent(getApplicationComponent())
                                            .activityModule(getActivityModule())
                                            .userModule(new UserModule())
                                            .build();
  }
}
