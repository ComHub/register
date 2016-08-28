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
package io.comhub.register.android.presentation.view.home.product;

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
public class ProductGridPresenter implements Presenter {

  private ProductGridView productGridView;

  @Inject
  public ProductGridPresenter() {
  }

  public void setView(@NonNull ProductGridView view) {
    this.productGridView = view;
  }

  @Override
  public void resume() {}

  @Override
  public void pause() {}

  @Override
  public void destroy() {
    this.productGridView = null;
  }

  /**
   * Initializes the presenter
   */
  public void initialize() {
  }

  private void showViewLoading() {
    this.productGridView.showLoading();
  }

  private void hideViewLoading() {
    this.productGridView.hideLoading();
  }

  private void showViewRetry() {
    this.productGridView.showRetry();
  }

  private void hideViewRetry() {
    this.productGridView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.productGridView.context(),
                                                     errorBundle.getException());
    this.productGridView.showError(errorMessage);
  }
}
