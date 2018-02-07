package com.github.study.transaction.jpa.dao;


import com.github.study.transaction.jpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserId(Integer userId);

    User findByUserName(String userName);

    List<User> findByUserNameLike(String userName);

    User findByUserNameIgnoreCase(String userName);

    List<User> findByUserNameOrderByEmailDesc(String email);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    Page<User> findByUserNameLike(String userName, Pageable pageable);

    User findFirstByOrderByUserNameAsc();

    User findTopByOrderByUserIdDesc();

    Page<User> queryFirst10ByUserName(String lastname, Pageable pageable);

    List<User> findFirst10ByUserName(String lastname, Sort sort);

    List<User> findTop10ByUserName(String lastname, Pageable pageable);

    @Modifying
    @Query("update User u set u.userName = ?1 where u.id = ?2")
    int modifyByIdAndUserId(String userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User u where u.userId = ?1")
    void deleteByUserId(Long id);

    @Transactional(timeout = 10)
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);


}
