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
package io.comhub.register.android.presentation.internal.di.components;

import dagger.Component;
import io.comhub.register.android.presentation.internal.di.PerActivity;
import io.comhub.register.android.presentation.internal.di.modules.ActivityModule;
import io.comhub.register.android.presentation.internal.di.modules.UserModule;
import io.comhub.register.android.presentation.view.fragment.UserDetailsFragment;
import io.comhub.register.android.presentation.view.fragment.UserListFragment;
import io.comhub.register.android.presentation.view.user.login.UserLoginFragment;

/**
 * A scope {@link io.comhub.register.android.presentation.internal.di.PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class, UserModule.class })
public interface UserComponent extends ActivityComponent {

  void inject(UserLoginFragment userLoginFragment);
  void inject(UserListFragment userListFragment);
  void inject(UserDetailsFragment userDetailsFragment);
}
