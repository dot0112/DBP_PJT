package DBP_equipmentRentalService.main.repository.procedure;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcProcedureRepository implements ProcedureRepository {
    private final DataSource dataSource;

    @Autowired
    public JdbcProcedureRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setBorrowLimit(String userId) {
        String sql = "{CALL SET_BORROW_LIMIT(?)}";
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, userId);
            cstmt.execute();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, cstmt, rs);
        }
    }

    @Override
    public List<Map<String, Object>> equipmentHistory(String itemId) {
        String sql = "{CALL EQUIPEMENT_HISTORY(?, ?)}";
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> procedureResult = new ArrayList<>();

        try {
            conn = getConnection();
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, itemId);
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.execute();

            rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {
                LocalDate eventDate = rs.getDate("EVENT_DATE").toLocalDate();
                String eventType = rs.getString("EVENT_TYPE"),
                        details = rs.getString("DETAILS");
                Map<String, Object> event = new HashMap<>();
                event.put("eventDate", eventDate);
                event.put("eventType", eventType);
                event.put("details", details);
                procedureResult.add(event);
            }
            return procedureResult;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, cstmt, rs);
        }
    }


    @Override
    public void manageItems(String adminId, Integer quantity, String itemName,
                            @Nullable String itemType, @Nullable String roomNumber,
                            @Nullable String buildingName, @Nullable String rentableStatus,
                            @Nullable String rentalStatus) {
        // 필수 매개변수 검증
        if (adminId == null || quantity == null || itemName == null) {
            throw new IllegalArgumentException("adminId, quantity, and itemName must not be null");
        }

        String sql = "{CALL MANAGE_ITEMS(?, ?, ?, ?, ?, ?, ?, ?)}";
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, itemName);
            setNullableString(cstmt, 2, itemType);
            cstmt.setString(3, adminId);
            cstmt.setInt(4, quantity);
            setNullableString(cstmt, 5, roomNumber);
            setNullableString(cstmt, 6, buildingName);
            setNullableString(cstmt, 7, rentableStatus);
            setNullableString(cstmt, 8, rentalStatus);

            cstmt.execute();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, cstmt, rs);
        }
    }

    private void setNullableString(CallableStatement cstmt, int parameterIndex, String value) throws SQLException {
        if (value == null) {
            cstmt.setNull(parameterIndex, Types.VARCHAR);
        } else {
            cstmt.setString(parameterIndex, value);
        }
    }

    protected Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
        //하나의 일관된 connection 관리용. close도 마찬가지
    }

    protected void close(Connection conn, CallableStatement cstmt, ResultSet rs) {
        //rs, stmt, conn 순서대로 처리
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (cstmt != null) {
                cstmt.close();
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
    }
}
