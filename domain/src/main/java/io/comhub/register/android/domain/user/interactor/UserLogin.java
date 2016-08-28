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
package io.comhub.register.android.domain.user.interactor;

import io.comhub.register.android.domain.executor.PostExecutionThread;
import io.comhub.register.android.domain.executor.ThreadExecutor;
import io.comhub.register.android.domain.interactor.UseCase;
import io.comhub.register.android.domain.user.User;
import io.comhub.register.android.domain.user.UserRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * login a {@link User}
 */
public class UserLogin extends UseCase {

  private final UserRepository userRepository;
  private String email;
  private String password;

  @Inject
  public UserLogin(UserRepository userRepository, ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.userRepository = userRepository;
  }

  @Override
  public Observable buildUseCaseObservable() {
    if (this.email == null || this.password == null) {
      throw new UseCaseNotInitialisedException(
        "init(email, password) not called, or called with null argument.");
    }
    return this.userRepository.userEntity(email, password);
  }

  /**
   * Initializes the interactor with the email and password to use for the authentication.
   * @param email - email for the user
   * @param password - password for the user.
   */
  public UserLogin init(final String email, final String password) {
    this.email = email;
    this.password = password;

    return this;
  }
}
