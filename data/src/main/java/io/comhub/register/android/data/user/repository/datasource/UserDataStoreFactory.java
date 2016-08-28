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
package io.comhub.register.android.data.user.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;
import io.comhub.register.android.data.user.entity.mapper.UserEntityFirebaseMapper;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton
public class UserDataStoreFactory {

  private final Context context;

  @Inject
  public UserDataStoreFactory(@NonNull Context context) {
    this.context = context.getApplicationContext();
  }

  /**
   * Create {@link UserDataStore} from a user id.
   */
  public UserDataStore create() {
    return createFirebaseDataStore();
  }

  /**
   * Create {@link UserDataStore} to retrieve data from Firebase.
   */
  public UserDataStore createFirebaseDataStore() {
    UserEntityFirebaseMapper userEntityFirebaseMapper = new UserEntityFirebaseMapper();

    return new FirebaseUserDataStore(userEntityFirebaseMapper);
  }
}
