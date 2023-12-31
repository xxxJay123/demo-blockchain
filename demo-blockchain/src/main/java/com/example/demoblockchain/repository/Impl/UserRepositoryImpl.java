package com.example.demoblockchain.repository;

import com.example.demoblockchain.entity.User;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {
  @Autowired
  private MongoTemplate mongoTemplate;

  /**
   * 创建对象
   * 
   * @param user
   */
  @Override
  public void saveUser(User user) {
    mongoTemplate.save(user);
  }

  /**
   * 根据用户名查询对象
   * 
   * @param userName
   * @return
   */
  @Override
  public User findUserByUserName(String userName) {
    Query query = new Query(Criteria.where("userName").is(userName));
    User user = mongoTemplate.findOne(query, User.class);
    return user;
  }

  /**
   * 更新对象
   * 
   * @param user
   */
  @Override
  public long updateUser(User user) {
    Query query = new Query(Criteria.where("id").is(user.getId()));
    Update update = new Update().set("userName", user.getUserName())
        .set("passWord", user.getPassWord()).set("score", user.getScore());
    // 更新查询返回结果集的第一条
    UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
    // 更新查询返回结果集的所有
    // mongoTemplate.updateMulti(query,update,UserEntity.class);
    if (result != null)
      return result.getMatchedCount();
    else
      return 0;
  }

  /**
   * 删除对象
   * 
   * @param id
   */
  @Override
  public void deleteUserById(Long id) {
    Query query = new Query(Criteria.where("id").is(id));
    mongoTemplate.remove(query, User.class);
  }

  @Override
  public User findUserById(Long id) {
    Query query = new Query(Criteria.where("id").is(id));
    User user = mongoTemplate.findOne(query, User.class);
    return user;
  }

  @Override
  public void saveUserOnChainProfile(User user, double score) {
    user.getOnChainProfile().saveScoreToBlockchain(score);
    Query query = new Query(Criteria.where("id").is(user.getId()));
    Update update =
        new Update().set("onChainProfile", user.getOnChainProfile());
    mongoTemplate.updateFirst(query, update, User.class);
  }

  @Override
  public void updateUserOnChainProfile(User user, double score) {
    user.getOnChainProfile().saveScoreToBlockchain(score);
    Query query = new Query(Criteria.where("id").is(user.getId()));
    Update update =
        new Update().set("onChainProfile", user.getOnChainProfile());
    mongoTemplate.updateFirst(query, update, User.class);

  }
}


