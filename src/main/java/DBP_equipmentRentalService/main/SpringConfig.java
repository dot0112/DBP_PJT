package DBP_equipmentRentalService.main;

import DBP_equipmentRentalService.main.repository.admin.*;
import DBP_equipmentRentalService.main.repository.item.*;
import DBP_equipmentRentalService.main.repository.lectureRoom.*;
import DBP_equipmentRentalService.main.repository.procedure.JdbcProcedureRepository;
import DBP_equipmentRentalService.main.repository.procedure.JdbcTemplateProcedureRepository;
import DBP_equipmentRentalService.main.repository.procedure.JpaProcedureRepository;
import DBP_equipmentRentalService.main.repository.procedure.ProcedureRepository;
import DBP_equipmentRentalService.main.repository.rental.*;
import DBP_equipmentRentalService.main.repository.repairRecord.*;
import DBP_equipmentRentalService.main.repository.repairRequest.*;
import DBP_equipmentRentalService.main.repository.returns.*;
import DBP_equipmentRentalService.main.repository.users.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    @Value("${repository.type}")
    private String repositoryType;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public AdminRepository adminRepository() {
        return switch (repositoryType) {
            case "MEMORY" -> new MemoryAdminRepository();
            case "JDBC" -> new JdbcAdminRepository(dataSource);
            case "JDBCTEMPLATE" -> new JdbcTemplateAdminRepository(dataSource);
            case "JPA" -> new JpaAdminRepository(em);
            default -> throw new IllegalArgumentException("Invalid Repository Type");
        };
    }

    @Bean
    public ItemRepository itemRepository() {
        return switch (repositoryType) {
            case "MEMORY" -> new MemoryItemRepository();
            case "JDBC" -> new JdbcItemRepository(dataSource);
            case "JDBCTEMPLATE" -> new JdbcTemplateItemRepository(dataSource);
            case "JPA" -> new JpaItemRepository(em);
            default -> throw new IllegalArgumentException("Invalid Repository Type");
        };
    }

    @Bean
    public LectureRoomRepository lectureRoomRepository() {
        return switch (repositoryType) {
            case "MEMORY" -> new MemoryLectureRoomRepository();
            case "JDBC" -> new JdbcLectureRoomRepository(dataSource);
            case "JDBCTEMPLATE" -> new JdbcTemplateLectureRoomRepository(dataSource);
            case "JPA" -> new JpaLectureRoomRepository(em);
            default -> throw new IllegalArgumentException("Invalid Repository Type");
        };
    }

    @Bean
    public RentalRepository rentalRepository() {
        return switch (repositoryType) {
            case "MEMORY" -> new MemoryRentalRepository();
            case "JDBC" -> new JdbcRentalRepository(dataSource);
            case "JDBCTEMPLATE" -> new JdbcTemplateRentalRepository(dataSource);
            case "JPA" -> new JpaRentalRepository(em);
            default -> throw new IllegalArgumentException("Invalid Repository Type");
        };
    }

    @Bean
    public RepairRecordRepository repairRecordRepository() {
        return switch (repositoryType) {
            case "MEMORY" -> new MemoryRepairRecordRepository();
            case "JDBC" -> new JdbcRepairRecordRepository(dataSource);
            case "JDBCTEMPLATE" -> new JdbcTemplateRepairRecordRepository(dataSource);
            case "JPA" -> new JpaRepairRecordRepository(em);
            default -> throw new IllegalArgumentException("Invalid Repository Type");
        };
    }

    @Bean
    public RepairRequestRepository repairRequestRepository() {
        return switch (repositoryType) {
            case "MEMORY" -> new MemoryRepairRequestRepository();
            case "JDBC" -> new JdbcRepairRequestRepository(dataSource);
            case "JDBCTEMPLATE" -> new JdbcTemplateRepairRequestRepository(dataSource);
            case "JPA" -> new JpaRepairRequestRepository(em);
            default -> throw new IllegalArgumentException("Invalid Repository Type");
        };
    }

    @Bean
    public ReturnsRepository returnsRepository() {
        return switch (repositoryType) {
            case "MEMORY" -> new MemoryReturnsRepository();
            case "JDBC" -> new JdbcReturnsRepository(dataSource);
            case "JDBCTEMPLATE" -> new JdbcTemplateReturnsRepository(dataSource);
            case "JPA" -> new JpaReturnsRepository(em);
            default -> throw new IllegalArgumentException("Invalid Repository Type");
        };
    }

    @Bean
    public UsersRepository usersRepository() {
        return switch (repositoryType) {
            case "MEMORY" -> new MemoryUsersRepository();
            case "JDBC" -> new JdbcUsersRepository(dataSource);
            case "JDBCTEMPLATE" -> new JdbcTemplateUsersRepository(dataSource);
            case "JPA" -> new JpaUsersRepository(em);
            default -> throw new IllegalArgumentException("Invalid Repository Type");
        };
    }

    @Bean
    public ProcedureRepository procedureRepository() {
        return switch (repositoryType) {
            case "JDBC" -> new JdbcProcedureRepository(dataSource);
            case "JDBCTEMPLATE" -> new JdbcTemplateProcedureRepository(dataSource);
            case "JPA" -> new JpaProcedureRepository(em);
            default -> throw new IllegalArgumentException("Invalid Repository Type");
        };
    }

}
