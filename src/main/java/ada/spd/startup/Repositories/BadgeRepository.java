package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Badge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends CrudRepository<Badge, Long> {
}
