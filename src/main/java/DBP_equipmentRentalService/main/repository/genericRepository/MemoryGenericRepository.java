package DBP_equipmentRentalService.main.repository.genericRepository;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

public class MemoryGenericRepository<T> {

    protected boolean matchesCriteria(T entity, Map<String, Object> criteria) {
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            try {
                Field field = entity.getClass().getDeclaredField(key);
                field.setAccessible(true);

                Object fieldValue = field.get(entity);

                if (!Objects.equals(fieldValue, value)) {
                    return false;
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return true;
    }

}
