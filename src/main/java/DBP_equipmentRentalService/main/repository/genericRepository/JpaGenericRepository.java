package DBP_equipmentRentalService.main.repository.genericRepository;

import DBP_equipmentRentalService.main.repository.util.ReflectionUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class JpaGenericRepository<T> {
    protected final EntityManager em;
    private final Class<T> entityType;

    public JpaGenericRepository(EntityManager em, Class<T> entityType) {
        this.em = em;
        this.entityType = entityType;
    }

    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    public List<T> findAll() {
        String jpql = "SELECT e FROM " + entityType.getSimpleName() + " e";
        return em.createQuery(jpql, entityType).getResultList();
    }

    public List<T> findByCriteria(Map<String, Object> criteria) {
        Set<String> validFields = ReflectionUtil.getFieldNames(entityType);
        StringBuilder jpql = new StringBuilder("SELECT e FROM ").append(entityType.getSimpleName()).append(" e WHERE 1=1");
        Map<String, Object> parameters = new HashMap<>();

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (!validFields.contains(key)) {
                throw new IllegalArgumentException("Invalid field: " + key);
            }

            if (value instanceof String && ((String) value).contains("%")) {
                jpql.append(" AND e.").append(key).append(" LIKE :").append(key);
            } else {
                jpql.append(" AND e.").append(key).append(" = :").append(key);
            }
            parameters.put(key, value);
        }

        TypedQuery<T> query = em.createQuery(jpql.toString(), entityType);

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }
}
