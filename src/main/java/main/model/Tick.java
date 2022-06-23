package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TICK")
public class Tick {

  /**
   * id тика
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private int id;

  /**
   * цена тика
   */
  @Column(name = "PRICE")
  private double price;

  /**
   * время тика
   */
  @Column(name = "TIMESTAMP")
  private long timestamp;

  /**
   * направление тика относительно предыдущего
   */
  @Column(name = "TREND")
  private int trend;

  /**
   * флаг порога жабы
   */
  @Column(name = "FLAG_FROG")
  private boolean flagFrog;

}
