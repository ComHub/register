package io.comhub.register.android.data.user.entity.mapper;

import com.google.firebase.auth.FirebaseUser;
import io.comhub.register.android.data.user.entity.UserEntity;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserEntityFirebaseMapper {

  @Inject
  public UserEntityFirebaseMapper() {
  }

  /**
   * Transform from valid {@link FirebaseUser} string to {@link UserEntity}.
   *
   * @param firebaseUser A Firebase User representing a user profile.
   * @return {@link UserEntity}.
   */
  public UserEntity transformUserEntity(FirebaseUser firebaseUser) {

    UserEntity userEntity = null;
    if (firebaseUser != null) {
      userEntity = new UserEntity();
      userEntity.setEmail(firebaseUser.getEmail());
      //userEntity.setCoverUrl(firebaseUser.getPhotoUrl().toString());
      userEntity.setFullname(firebaseUser.getDisplayName());
      userEntity.setUserId(firebaseUser.getUid());
    }

    return userEntity;
  }
}
