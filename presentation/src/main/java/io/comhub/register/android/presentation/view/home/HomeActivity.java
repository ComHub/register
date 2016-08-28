package io.comhub.register.android.presentation.view.home;

import android.os.Bundle;
import io.comhub.register.android.presentation.R;
import io.comhub.register.android.presentation.internal.di.HasComponent;
import io.comhub.register.android.presentation.internal.di.components.DaggerHomeComponent;
import io.comhub.register.android.presentation.internal.di.components.HomeComponent;
import io.comhub.register.android.presentation.internal.di.modules.HomeModule;
import io.comhub.register.android.presentation.view.base.BaseActivity;
import io.comhub.register.android.presentation.view.home.order.OrderListFragment;
import io.comhub.register.android.presentation.view.home.product.ProductGridFragment;

/**
 * Home application screen. This is the app entry point.
 */
public class HomeActivity extends BaseActivity implements HasComponent<HomeComponent> {

  HomeComponent homeComponent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    this.initializeActivity(savedInstanceState);
    this.initializeInjector();
  }

  @Override
  public HomeComponent getComponent() {
    return this.homeComponent;
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    addFragment(R.id.fragmentProductGridContainer, new ProductGridFragment());
    addFragment(R.id.fragmentOrderListContainer, new OrderListFragment());
  }

  private void initializeInjector() {
    this.homeComponent = DaggerHomeComponent.builder()
                                            .applicationComponent(getApplicationComponent())
                                            .activityModule(getActivityModule())
                                            .homeModule(new HomeModule())
                                            .build();
  }
}
