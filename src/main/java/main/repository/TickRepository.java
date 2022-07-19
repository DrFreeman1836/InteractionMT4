package main.repository;

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
  Optional<Integer> getNextFlag(@Param("lastId") Integer lastId);

  @Transactional
  @Query(value = "SELECT * " +
          "FROM tick " +
          "WHERE id <= :lastId AND id >= :lastId - :count", nativeQuery = true)
  List<Tick> getLastPointFrog(@Param("lastId") Integer lastId, @Param("count") Integer count);

  @Transactional
  @Query(value = "SELECT * "
      + "FROM tick "
      + "WHERE timestamp >= :from AND timestamp <= :to", nativeQuery = true)
  List<Tick> getTicksFromToTime(@Param("from") Long from, @Param("to") Long to);

  @Transactional
  @Query(value = "SELECT * "
      + "FROM tick "
      + "WHERE id >= :idTick - :tolerance AND id <= :idTick + :tolerance", nativeQuery = true)
  List<Tick> getHistoryTick(@Param("idTick") Integer idTick, @Param("tolerance") Integer tolerance);

}