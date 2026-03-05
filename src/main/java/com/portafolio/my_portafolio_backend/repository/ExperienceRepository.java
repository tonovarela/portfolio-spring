package com.portafolio.my_portafolio_backend.repository;

import com.portafolio.my_portafolio_backend.model.Experience;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ExperienceRepository implements IExperienceRepository {


    private final JdbcTemplate jdbcTemplate;

    public ExperienceRepository(JdbcTemplate jdbcTemplate) {
          this.jdbcTemplate = jdbcTemplate;
      }
    private final RowMapper<Experience> experienceRowMapper = (rs, rowNum) -> {;
        Experience experience = new Experience();
        experience.setId(rs.getLong("id"));
        experience.setJobTitle(rs.getString("job_title"));
        experience.setPersonalInfoId(rs.getLong("personal_info_id"));
        experience.setCompanyName(rs.getString("company_name"));
        experience.setStartDate(rs.getDate("start_date").toLocalDate());
        experience.setEndDate(rs.getDate("end_date") != null ? rs.getDate("end_date").toLocalDate() : null);
        experience.setDescription(rs.getString("description"));
        return  experience;
    };
    @Override
    public Experience save(Experience experience) {
         if (experience.getId() ==null){
             KeyHolder keyHolder = new GeneratedKeyHolder();
             String sql = "INSERT INTO experiences (job_title, company_name, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
                jdbcTemplate.update(connection -> {
                    var ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, experience.getJobTitle());
                    ps.setString(2, experience.getCompanyName());
                    ps.setDate(3, java.sql.Date.valueOf(experience.getStartDate()));
                    if (experience.getEndDate() != null) {
                        ps.setDate(4, java.sql.Date.valueOf(experience.getEndDate()));
                    } else {
                        ps.setNull(4, java.sql.Types.DATE);
                    }
                    ps.setString(5, experience.getDescription());
                    ps.setLong(6, experience.getPersonalInfoId());
                    return ps;
                }, keyHolder);
                experience.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
         }
         else {
             String sql = "UPDATE experiences SET job_title=?, company_name=?, start_date=?, end_date=?, description=?, personal_info_id=? WHERE id=?";
             jdbcTemplate.update(sql, experience.getJobTitle(), experience.getCompanyName(), java.sql.Date.valueOf(experience.getStartDate()), experience.getEndDate() != null ? java.sql.Date.valueOf(experience.getEndDate()) : null, experience.getDescription(), experience.getPersonalInfoId(), experience.getId());
         }
         return experience;
    }

    @Override
    public Optional<Experience> findById(Long id) {
        String sql= "SELECT * FROM experiences WHERE id=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, experienceRowMapper, id));
        }
        catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<Experience> findAll() {
        String sql = "SELECT * FROM experiences";
        return jdbcTemplate.query(sql, experienceRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM experiences WHERE id=?";
        jdbcTemplate.update(sql, id);

    }
}
