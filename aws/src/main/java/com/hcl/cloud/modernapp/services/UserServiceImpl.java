//package com.hcl.cloud.modernapp.services;
//
//import com.hcl.cloud.modernapp.model.UserEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.hcl.cloud.modernapp.Repo.UserRepo;
//import com.hcl.cloud.modernapp.model.UserModel;
//
//@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserRepo userRepo;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    /**
//     * todo: need to prevent duplicate registration
//     * @param user
//     */
//    public void save(UserModel user) {
//        UserModel userEntity = new UserModel();
//        userEntity.setUsername(user.getUsername());
//        // userEntity.setPassword(user.getPassword());
//        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepo.save(userEntity);
//    }
//
//    public UserModel findByUsername(String username) {
//        UserModel userEntity = userRepo.findByUsername(username);
//        //found
//        if (userEntity != null) {
//
//            return userEntity;
//        }
//        return null;
//    }
//}
//
////package com.hcl.cloud.modernapp.services;
////
////        import com.hcl.cloud.modernapp.model.UserEntity;
////        import org.springframework.beans.factory.annotation.Autowired;
////        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////        import org.springframework.stereotype.Service;
////
////        import com.hcl.cloud.modernapp.Repo.UserRepo;
////        import com.hcl.cloud.modernapp.model.UserModel;
////
////@Service
////public class UserServiceImpl implements UserService {
////    @Autowired
////    private UserRepo userRepo;
////    @Autowired
////    private BCryptPasswordEncoder bCryptPasswordEncoder;
////    private
////
////    /**
////     * todo: need to prevent duplicate registration
////     * @param user
////     */
////    public void save(UserModel user) {
////        UserEntity userEntity = new UserEntity();
////        userEntity.setUsername(user.getUsername());
////        // userEntity.setPassword(user.getPassword());
////        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
////        userRepo.save(userEntity);
////    }
////
////    public UserModel findByUsername(String username) {
////        UserEntity userEntity = userRepo.findByUsername(username);
////
////        if (userEntity != null) {
////            UserModel user = new UserModel();
////            user.setUsername(userEntity.getUsername());
////            user.setPassword(bCryptPasswordDec;
////            return user;
////        }
////        return null;
////    }
////}
