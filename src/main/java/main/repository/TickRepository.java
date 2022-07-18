package main.repository;

import java.util.List;
import javax.transaction.Transactional;
import main.model.Tick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TickRepository extends JpaRepository<Tick, Integer> {

  @Transactional
  @Query(value = "SELECT MIN(id) "
      + "FROM tick "
      + "WHERE id > :lastId AND flag_frog = 1", nativeQuery = true)
  int getIdNextFlag(@Param("lastId") Integer lastId);

  @Transactional
  @Query(value = "SELECT * " +
          "FROM tick " +
          "WHERE id > :lastId AND flag_frog = 1", nativeQuery = true)
  List<Tick> getLastPointFrog(@Param("lastId") Integer lastId, @Param("count") Integer count);//проверить запросами к БД иначе решать вопрос двумя запросами

}


//  @Query("SELECT u FROM User u WHERE u.status = 1")
//  Collection<User> findAllActiveUsers();