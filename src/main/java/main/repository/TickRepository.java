package main.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
  Optional<Integer> getIdNextFlag(@Param("lastId") Integer lastId);

  @Transactional
  @Query(value = "SELECT * " +
          "FROM tick " +
          "WHERE id > :lastId AND flag_frog = 1", nativeQuery = true)
  Collection<Tick> getLastPointFrog(@Param("lastId") Integer lastId, @Param("count") Integer count);

}


//  @Query("SELECT u FROM User u WHERE u.status = 1")
//  Collection<User> findAllActiveUsers();