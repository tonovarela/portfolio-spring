package com.portafolio.my_portafolio_backend.repository;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


import com.portafolio.my_portafolio_backend.model.Education;
import org.springframework.stereotype.Repository;

@Repository
public class EducationRepository  implements  IEducationRepository{


    private  final JdbcTemplate jdbcTemplate;

    private final RowMapper<Education> educationRowMapper = (rs, rowNum) -> {;
        Education education = new Education();
        education.setId(rs.getLong("id"));
        education.setDegree(rs.getString("degree"));
        education.setInstitution(rs.getString("institution"));
        education.setStartDate(rs.getDate("start_date").toLocalDate());
        education.setEndDate(rs.getDate("end_date") != null ? rs.getDate("end_date").toLocalDate() : null);
        education.setDescription(rs.getString("description"));
        education.setPersonalInfoId(rs.getLong("personal_info_id"));

        return  education;
    };

    public EducationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Education save(Education education) {
            if (education.getId()==null){
                KeyHolder keyHolder = new GeneratedKeyHolder();
                String sql = "INSERT INTO education (degree, institution, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
                jdbcTemplate.update(connection -> {
                    var ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, education.getDegree());
                    ps.setString(2, education.getInstitution());
                    ps.setDate(3, java.sql.Date.valueOf(education.getStartDate()));
                    if (education.getEndDate() != null) {
                        ps.setDate(4, java.sql.Date.valueOf(education.getEndDate()));
                    } else {
                        ps.setNull(4, java.sql.Types.DATE);
                    }
                    ps.setString(5, education.getDescription());
                    ps.setLong(6, education.getPersonalInfoId());
                    return ps;
                }, keyHolder);
                education.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
            }else {
                String sql = "UPDATE education SET degree=?, institution=?, start_date=?, end_date=?, description=?, personal_info_id=? WHERE id=?";
                jdbcTemplate.update(sql, education.getDegree(), education.getInstitution(), java.sql.Date.valueOf(education.getStartDate()), education.getEndDate() != null ? java.sql.Date.valueOf(education.getEndDate()) : null, education.getDescription(), education.getPersonalInfoId(), education.getId());
            }

            return  education;

    }

    @Override
    public Optional<Education> findById(Long id) {
        String sql= "SELECT * FROM education WHERE id=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, educationRowMapper, id));
        }
        catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<Education> findAll() {
        String sql = "SELECT * FROM education";
        return jdbcTemplate.query(sql, educationRowMapper);
    }

    @Override
    public void deleteById(Long id) {

        String sql = "DELETE FROM education WHERE id=?";
        jdbcTemplate.update(sql, id);

    }
}
