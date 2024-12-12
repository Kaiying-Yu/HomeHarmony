package hh.homeharmony.config;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.service.templates.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Configuration class for registering DefaultChoreTemplate beans.
 * Allows Spring to manage and inject templates into services.
 *
 * This setup ensures that all chore templates are registered
 * dynamically and available for the CreateChoreService.
 *
 * @author hh
 */
@Configuration
public class TemplateConfig {

  /**
   * Bean for KitchenChoreTemplate.
   *
   * @param choreMapper The mapper for handling database operations related to chores.
   * @return The KitchenChoreTemplate bean.
   */
  @Bean
  @Lazy
  public KitchenChoreTemplate kitchenChoreTemplate(ChoreMapper choreMapper) {
    return new KitchenChoreTemplate(choreMapper);
  }

  /**
   * Bean for BathroomChoreTemplate.
   *
   * @param choreMapper The mapper for handling database operations related to chores.
   * @return The BathroomChoreTemplate bean.
   */
  @Bean
  @Lazy
  public BathroomChoreTemplate bathroomChoreTemplate(ChoreMapper choreMapper) {
    return new BathroomChoreTemplate(choreMapper);
  }

  /**
   * Bean for BedroomChoreTemplate.
   *
   * @param choreMapper The mapper for handling database operations related to chores.
   * @return The BedroomChoreTemplate bean.
   */
  @Bean
  @Lazy
  public BedroomChoreTemplate bedroomChoreTemplate(ChoreMapper choreMapper) {
    return new BedroomChoreTemplate(choreMapper);
  }

  /**
   * Bean for LivingRoomChoreTemplate.
   *
   * @param choreMapper The mapper for handling database operations related to chores.
   * @return The LivingRoomChoreTemplate bean.
   */
  @Bean
  @Lazy
  public LivingRoomChoreTemplate livingRoomChoreTemplate(ChoreMapper choreMapper) {
    return new LivingRoomChoreTemplate(choreMapper);
  }

  /**
   * Bean for DiningRoomChoreTemplate.
   *
   * @param choreMapper The mapper for handling database operations related to chores.
   * @return The DiningRoomChoreTemplate bean.
   */
  @Bean
  @Lazy
  public DiningRoomChoreTemplate diningRoomChoreTemplate(ChoreMapper choreMapper) {
    return new DiningRoomChoreTemplate(choreMapper);
  }

  /**
   * Bean for HomeOfficeChoreTemplate.
   *
   * @param choreMapper The mapper for handling database operations related to chores.
   * @return The HomeOfficeChoreTemplate bean.
   */
  @Bean
  @Lazy
  public HomeOfficeChoreTemplate homeOfficeChoreTemplate(ChoreMapper choreMapper) {
    return new HomeOfficeChoreTemplate(choreMapper);
  }

  /**
   * Bean for GarageChoreTemplate.
   *
   * @param choreMapper The mapper for handling database operations related to chores.
   * @return The GarageChoreTemplate bean.
   */
  @Bean
  @Lazy
  public GarageChoreTemplate garageChoreTemplate(ChoreMapper choreMapper) {
    return new GarageChoreTemplate(choreMapper);
  }

  /**
   * Bean for BalconyChoreTemplate.
   *
   * @param choreMapper The mapper for handling database operations related to chores.
   * @return The BalconyChoreTemplate bean.
   */
  @Bean
  @Lazy
  public BalconyChoreTemplate balconyChoreTemplate(ChoreMapper choreMapper) {
    return new BalconyChoreTemplate(choreMapper);
  }
}