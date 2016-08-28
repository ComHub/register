/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.comhub.register.android.presentation.view.home.order;

import android.support.annotation.NonNull;
import io.comhub.register.android.domain.exception.ErrorBundle;
import io.comhub.register.android.presentation.exception.ErrorMessageFactory;
import io.comhub.register.android.presentation.internal.di.PerActivity;
import io.comhub.register.android.presentation.presenter.Presenter;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class OrderListPresenter implements Presenter {

  private OrderListView orderListView;

  @Inject
  public OrderListPresenter() {
  }

  public void setView(@NonNull OrderListView view) {
    this.orderListView = view;
  }

  @Override
  public void resume() {}

  @Override
  public void pause() {}

  @Override
  public void destroy() {
    this.orderListView = null;
  }

  /**
   * Initializes the presenter
   */
  public void initialize() {

  }

  private void showViewLoading() {
    this.orderListView.showLoading();
  }

  private void hideViewLoading() {
    this.orderListView.hideLoading();
  }

  private void showViewRetry() {
    this.orderListView.showRetry();
  }

  private void hideViewRetry() {
    this.orderListView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.orderListView.context(),
                                                     errorBundle.getException());
    this.orderListView.showError(errorMessage);
  }
}
