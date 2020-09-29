package com.temp3.eportfolioapplication.repository;

import com.temp3.eportfolioapplication.model.User;
import com.temp3.eportfolioapplication.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findById(long id);

    UserInfo findByUser(User user);
}
