package DBP_equipmentRentalService.main.repository.genericRepository;

import DBP_equipmentRentalService.main.repository.util.ReflectionUtil;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public abstract class JdbcGenericRepository<T> {
    protected final DataSource dataSource;
    private final Class<T> entityType;

    public JdbcGenericRepository(DataSource dataSource, Class<T> entityType) {
        this.dataSource = dataSource;
        this.entityType = entityType;
    }

    public T save(T entity) {
        Field[] fields = entityType.getDeclaredFields();
        List<Field> validFields = new ArrayList<>();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value != null) {
                    validFields.add(field);
                    columns.append(field.getName()).append(", ");
                    values.append("?, ");
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        columns.setLength(columns.length() - 2);
        values.setLength(values.length() - 2);

        String sql = "INSERT INTO " + entityType.getSimpleName().toLowerCase() +
                " (" + columns + ") VALUES (" + values + ")";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            int index = 1;
            for (Field field : validFields) {
                pstmt.setObject(index++, field.get(entity));
            }
            int num = pstmt.executeUpdate();
            if (num == 1) {
                return entity;
            } else {
                throw new SQLException("Failed to insert");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public List<T> findAll() {
        String sql = "SELECT * FROM " + entityType.getSimpleName().toLowerCase();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<T> objects = new ArrayList<>();
            while (rs.next()) {
                T object = entityType.getDeclaredConstructor().newInstance();

                for (Field field : entityType.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = rs.getObject(field.getName());
                    if (value instanceof Timestamp) {
                        value = ((Timestamp) value).toLocalDateTime().toLocalDate();
                    }
                    if (value instanceof BigDecimal) {
                        value = ((BigDecimal) value).intValue();
                    }
                    field.set(object, value);
                }

                objects.add(object);
            }
            return objects;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public List<T> findByCriteria(Map<String, Object> criteria) {
        Set<String> validFields = ReflectionUtil.getFieldNames(entityType);
        StringBuilder sql = new StringBuilder("SELECT * FROM " + entityType.getSimpleName().toLowerCase() + " WHERE 1=1");
        Map<String, Object> parameters = new HashMap<>();

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
            if (value instanceof LocalDate)
                parameters.put(key, Date.valueOf((LocalDate) value));
            else
                parameters.put(key, value);
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql.toString());

            int index = 1;
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                pstmt.setObject(index++, entry.getValue());
            }

            rs = pstmt.executeQuery();
            List<T> objects = new ArrayList<>();
            while (rs.next()) {
                T object = entityType.getDeclaredConstructor().newInstance();

                for (Field field : entityType.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = rs.getObject(field.getName());
                    if (value instanceof Timestamp) {
                        value = ((Timestamp) value).toLocalDateTime().toLocalDate();
                    }
                    if (value instanceof BigDecimal) {
                        value = ((BigDecimal) value).intValue();
                    }
                    field.set(object, value);
                }

                objects.add(object);
            }
            return objects;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public Boolean update(Map<String, Object> criteria, Map<String, Object> changeValues) {
        Set<String> validFields = ReflectionUtil.getFieldNames(entityType);
        StringBuilder sql = new StringBuilder("UPDATE ").append(entityType.getSimpleName().toLowerCase()).append(" SET ");
        Map<String, Object> parameters = new HashMap<>();

        boolean firstSet = true;
        for (Map.Entry<String, Object> entry : changeValues.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (!validFields.contains(key)) {
                throw new IllegalArgumentException("Invalid field: " + key);
            }

            if (firstSet) {
                sql.append(key).append(" = ?");
                firstSet = false;
            } else {
                sql.append(", ").append(key).append(" = ?");
            }
            parameters.put(key, value);
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
            parameters.put(key, value);
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql.toString());

            int index = 1;
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                pstmt.setObject(index++, entry.getValue());
            }

            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
        return true;
    }

    protected Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
        //하나의 일관된 connection 관리용. close도 마찬가지
    }

    protected void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        //rs, stmt, conn 순서대로 처리
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);  // private method
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }    //스프링부트용 DB Connection 풀관리 :
}