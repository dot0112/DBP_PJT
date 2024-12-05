package DBP_equipmentRentalService.main.repository.procedure;

import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateProcedureRepository implements ProcedureRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateProcedureRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void setBorrowLimit(String userId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SET_BORROW_LIMIT");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("p_userid", userId);

        try {
            Map<String, Object> result = jdbcCall.execute(paramMap);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Map<String, Object>> equipmentHistory(String itemId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("EQUIPEMENT_HISTORY");
        jdbcCall.addDeclaredParameter(new SqlParameter("p_itemid", Types.NUMERIC));
        jdbcCall.addDeclaredParameter(new SqlParameter("p_result", OracleTypes.CURSOR));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("p_itemid", itemId);

        ResultSet rs = null;
        try {
            Map<String, Object> result = jdbcCall.execute(paramMap);
            rs = (ResultSet) result.get("p_result");

            List<Map<String, Object>> procedureResult = new ArrayList<>();

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
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    @Override
    public void manageItems(String itemName, String itemType, String adminId, Integer quantity, String roomNumber, String buildingName) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("MANAGE_ITEMS");
        jdbcCall.addDeclaredParameter(new SqlParameter("p_itemname", Types.VARCHAR));
        jdbcCall.addDeclaredParameter(new SqlParameter("p_itemtype", Types.VARCHAR));
        jdbcCall.addDeclaredParameter(new SqlParameter("p_adminid", Types.VARCHAR));
        jdbcCall.addDeclaredParameter(new SqlParameter("p_quantity", Types.INTEGER));
        jdbcCall.addDeclaredParameter(new SqlParameter("p_roomnumber", Types.VARCHAR));
        jdbcCall.addDeclaredParameter(new SqlParameter("p_buildingname", Types.VARCHAR));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("p_itemname", itemName);
        paramMap.put("p_itemtype", itemType);
        paramMap.put("p_adminid", adminId);
        paramMap.put("p_quantity", quantity);
        paramMap.put("p_roomnumber", roomNumber);
        paramMap.put("p_buildingname", buildingName);

        try {
            Map<String, Object> result = jdbcCall.execute(paramMap);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
