package DBP_equipmentRentalService.main.repository.lectureRoom;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.domain.LectureRoomId;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class JdbcLectureRoomRepository extends JdbcGenericRepository<LectureRoom> implements LectureRoomRepository {
    @Autowired
    public JdbcLectureRoomRepository(DataSource dataSource) {
        super(dataSource, LectureRoom.class);
    }


    @Override
    public Optional<LectureRoom> findById(LectureRoomId lectureRoomId) {
        String sql = "SELECT * FROM LECTUREROOM WHERE ROOMNUMBER = ? AND BUILDINGNAME = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, lectureRoomId.getRoomNumber());
            pstmt.setString(2, lectureRoomId.getBuildingName());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                LectureRoom lectureRoom = new LectureRoom();
                lectureRoom.setRoomNumber(rs.getString("ROOMNUMBER"));
                lectureRoom.setBuildingName(rs.getString("BUILDINGNAME"));
                lectureRoom.setDepartmentName(rs.getString("DEPARTMENTNAME"));
                lectureRoom.setAdminId(rs.getString("ADMINID"));
                return Optional.of(lectureRoom);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
