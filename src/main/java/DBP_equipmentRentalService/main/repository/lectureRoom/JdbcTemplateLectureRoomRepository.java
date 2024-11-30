package DBP_equipmentRentalService.main.repository.lectureRoom;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcTemplateGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateLectureRoomRepository extends JdbcTemplateGenericRepository<LectureRoom> implements LectureRoomRepository {
    @Autowired
    public JdbcTemplateLectureRoomRepository(DataSource dataSource) {
        super(dataSource, LectureRoom.class);
    }

    @Override
    public Optional<LectureRoom> findById(String roomNumber, String buildingName) {
        List<LectureRoom> result = jdbcTemplate.query("SELECT * FROM LECTUREROOM WHERE ROOMNUMBER = ? AND BUILDINGNAME = ?", rowMapper(), Arrays.asList(roomNumber, buildingName));
        return result.stream().findAny();
    }

    @Override
    protected RowMapper<LectureRoom> rowMapper() {
        return (rs, rowNum) -> {
            LectureRoom lectureRoom = new LectureRoom();
            lectureRoom.setRoomNumber(rs.getString("ROOMNUMBER"));
            lectureRoom.setBuildingName(rs.getString("BUILDINGNAME"));
            lectureRoom.setDepartmentName(rs.getString("DEPARTMENTNAME"));
            lectureRoom.setAdminId(rs.getString("ADMINID"));
            return lectureRoom;
        };
    }
}
