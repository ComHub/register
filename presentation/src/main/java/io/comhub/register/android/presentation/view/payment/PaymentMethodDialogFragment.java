package io.comhub.register.android.presentation.view.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import io.comhub.register.android.presentation.R;
import io.comhub.register.android.presentation.internal.di.HasComponent;
import io.comhub.register.android.presentation.internal.di.components.DaggerHomeComponent;
import io.comhub.register.android.presentation.internal.di.components.DaggerPaymentComponent;
import io.comhub.register.android.presentation.internal.di.components.HomeComponent;
import io.comhub.register.android.presentation.internal.di.components.PaymentComponent;
import io.comhub.register.android.presentation.internal.di.modules.HomeModule;
import io.comhub.register.android.presentation.view.base.BaseDialogFragment;
import javax.inject.Inject;

public class PaymentMethodDialogFragment extends BaseDialogFragment implements HasComponent<PaymentComponent> {

  @Inject LayoutInflater layoutInflater;

  private HomeComponent paymentComponent;

  public static PaymentMethodDialogFragment newInstance() {
    PaymentMethodDialogFragment fragment = new PaymentMethodDialogFragment();

    Bundle args = new Bundle();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.initializeInjector();
    this.getComponent(PaymentComponent.class).inject(this);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = layoutInflater.inflate(R.layout.fragment_payment_method, container);

    ButterKnife.bind(this, rootView);

    return rootView;
  }

  private void initializeInjector() {
    this.paymentComponent = DaggerPaymentComponent.builder()
                                                  .applicationComponent(getApplicationComponent())
                                                  .activityModule(getActivityModule())
                                                  .homeModule(new HomeModule())
                                                  .build();
  }

  @Override
  public PaymentComponent getComponent() {
    return this.paymentComponent;
  }
}
