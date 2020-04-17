package ada.spd.startup.Repositories;

import ada.spd.startup.Domains.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
