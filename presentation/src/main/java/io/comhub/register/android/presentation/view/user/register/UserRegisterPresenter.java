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
package io.comhub.register.android.presentation.view.user.register;

import android.support.annotation.NonNull;
import io.comhub.register.android.domain.exception.ErrorBundle;
import io.comhub.register.android.domain.user.interactor.UserLogin;
import io.comhub.register.android.presentation.exception.ErrorMessageFactory;
import io.comhub.register.android.presentation.internal.di.PerActivity;
import io.comhub.register.android.presentation.mapper.UserModelDataMapper;
import io.comhub.register.android.presentation.presenter.Presenter;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
class UserRegisterPresenter implements Presenter {

  private UserRegisterView viewLogin;

  private final UserLogin userLoginUseCase;
  private final UserModelDataMapper userModelDataMapper;

  @Inject
  public UserRegisterPresenter(UserLogin userLoginUseCase,
                               UserModelDataMapper userModelDataMapper) {
    this.userLoginUseCase = userLoginUseCase;
    this.userModelDataMapper = userModelDataMapper;
  }

  public void setView(@NonNull UserRegisterView view) {
    this.viewLogin = view;
  }

  @Override
  public void resume() {}

  @Override
  public void pause() {}

  @Override
  public void destroy() {
    this.userLoginUseCase.unsubscribe();
    this.viewLogin = null;
  }

  /**
   * Initializes the presenter
   */
  void initialize() {
    this.loadRegistrationUrl();
  }

  void onButtonRetryClicked() {
    this.loadRegistrationUrl();
  }

  private void loadRegistrationUrl() {
    this.viewLogin.loadRegistrationUrl("http://www.google.com", "text/html", "UTF-8");
  }

  private void showViewLoading() {
    this.viewLogin.showLoading();
  }

  private void hideViewLoading() {
    this.viewLogin.hideLoading();
  }

  private void showViewRetry() {
    this.viewLogin.showRetry();
  }

  private void hideViewRetry() {
    this.viewLogin.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewLogin.context(),
                                                     errorBundle.getException());
    this.viewLogin.showError(errorMessage);
  }
}
