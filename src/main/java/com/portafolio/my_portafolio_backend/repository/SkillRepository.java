package com.portafolio.my_portafolio_backend.repository;


import com.portafolio.my_portafolio_backend.model.Skill;
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
public class SkillRepository  implements  ISkillRepository{
   private  final JdbcTemplate jdbcTemplate;
   private final RowMapper<Skill> skillRowMapper = (rs, rowNum) -> {;
        Skill skill = new Skill();
        skill.setId(rs.getLong("id"));
        skill.setName(rs.getString("name"));
        skill.setLevelPercentage(rs.getInt("level_percentage"));
        skill.setIconClass(rs.getString("icon_class"));
        skill.setPersonalInfoId(rs.getLong("personal_info_id"));
        return  skill;
    };

    public SkillRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Skill save(Skill skill) {
        if (skill.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql = "INSERT INTO skills (name, level_percentage, icon_class, personal_info_id) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(connection -> {
                var ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, skill.getName());
                    ps.setInt(2, skill.getLevelPercentage());
                    ps.setString(3, skill.getIconClass());
                    ps.setLong(4, skill.getPersonalInfoId());
                    return ps;
            }, keyHolder);
            skill.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE skills SET name=?, level_percentage=?, icon_class=?, personal_info_id=? WHERE id=?";
            jdbcTemplate.update(sql, skill.getName(), skill.getLevelPercentage(), skill.getIconClass(), skill.getPersonalInfoId(), skill.getId());
        }
        return skill;
    }

    @Override
    public Optional<Skill> findById(Long id) {
        String sql= "SELECT * FROM skills WHERE id=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, skillRowMapper, id));
        }
        catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<Skill> findAll() {
        String sql ="SELECT * FROM skills";
        return jdbcTemplate.query(sql,skillRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM skills WHERE id=?";
        jdbcTemplate.update(sql, id);

    }
}
