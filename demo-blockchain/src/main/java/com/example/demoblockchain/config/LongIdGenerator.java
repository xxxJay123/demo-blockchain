package com.example.demoblockchain.config;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import com.example.demoblockchain.entity.User;
import com.example.demoblockchain.utils.Identifiable;



@Component
public class LongIdGenerator extends AbstractMongoEventListener<User> {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public void onBeforeConvert(BeforeConvertEvent<User> event) {
    User user = event.getSource();
    if (isNew(user)) {
      setNextLongId(user);
    }
  }

  private boolean isNew(Object source) {

    MongoPersistentEntity<?> entity = mongoTemplate.getConverter()
        .getMappingContext().getPersistentEntity(source.getClass());

    if (entity == null) {
      // Handle case where entity is not mapped
      return true;
    }

    return entity.getIdentifierAccessor(source).getIdentifier() == null;

  }

  private void setNextLongId(Object source) {
    if (source instanceof Identifiable) {
      Identifiable identifiable = (Identifiable) source;
      Long nextId = getNextLongId(source.getClass());
      identifiable.setId(nextId);
    }
  }

  private Long getNextLongId(Class<?> entityClass) {
    UUID uuid = UUID.randomUUID();
    return uuid.getMostSignificantBits();
  }

}
