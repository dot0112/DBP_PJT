package DBP_equipmentRentalService.main.repository.genericRepository;

import DBP_equipmentRentalService.main.repository.util.ReflectionUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;

public abstract class JdbcTemplateGenericRepository<T> {
    protected final JdbcTemplate jdbcTemplate;
    private final Class<T> entityType;

    public JdbcTemplateGenericRepository(DataSource dataSource, Class<T> entityType) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.entityType = entityType;
    }

    public T save(T entity) {
        Field[] fields = entityType.getDeclaredFields();
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName(entityType.getSimpleName().toLowerCase());
        Map<String, Object> parameters = new HashMap<>();

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value != null) {
                    parameters.put(field.getName(), value);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        jdbcInsert.execute(parameters);
        return entity;
    }

    public List<T> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + entityType.getSimpleName().toLowerCase(), rowMapper());
    }

    public List<T> findByCriteria(Map<String, Object> criteria) {
        Set<String> validFields = ReflectionUtil.getFieldNames(entityType);
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(entityType.getSimpleName().toLowerCase()).append(" WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (!validFields.contains(key)) {
                throw new IllegalArgumentException("Invalid field: " + key);
            }

            if (value instanceof String && ((String) value).contains("%")) {
                sql.append(" AND ").append(key).append(" LIKE ?");
            } else {
                sql.append(" AND ").append(key).append(" = ?");
            }
            parameters.add(value);
        }

        return jdbcTemplate.query(sql.toString(), rowMapper(), parameters.toArray());
    }

    public Boolean update(Map<String, Object> criteria, Map<String, Object> changeValues) {
        Set<String> validFields = ReflectionUtil.getFieldNames(entityType);
        StringBuilder sql = new StringBuilder("UPDATE ").append(entityType.getSimpleName().toLowerCase()).append(" SET 1 = 1");
        List<Object> parameters = new ArrayList<>();

        for (Map.Entry<String, Object> entry : changeValues.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (!validFields.contains(key)) {
                throw new IllegalArgumentException("Invalid field: " + key);
            }

            sql.append(", ").append(key).append(" = ?");
            parameters.add(value);
        }

        sql.append(" WHERE 1 = 1");

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (!validFields.contains(key)) {
                throw new IllegalArgumentException("Invalid field: " + key);
            }
            if (value instanceof String && (((String) value).contains("%") || ((String) value).contains("_"))) {
                sql.append(" AND ").append(key).append(" LIKE ?");
            } else {
                sql.append(" AND ").append(key).append(" = ?");
            }
            parameters.add(value);
        }

        try {
            jdbcTemplate.update(sql.toString(), rowMapper(), parameters.toArray());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return true;
    }

    abstract protected RowMapper<T> rowMapper();


}
