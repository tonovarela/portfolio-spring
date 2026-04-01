package com.portafolio.my_portafolio_backend.repository.implementation;

import com.portafolio.my_portafolio_backend.model.Project;

import com.portafolio.my_portafolio_backend.repository.interfaces.IProjectRepository;
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
public class ProjectRepository  implements IProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Project> projectRowMapper =(rs, rowNum) -> {
       Project project = new Project();
       project.setId(rs.getLong("id"));
       project.setDescription(rs.getString("description"));
       project.setProjectUrl(rs.getString("project_url"));
       project.setTitle(rs.getString("title"));
       project.setImageUrl(rs.getString("image_url"));
       project.setPersonalInfoId(rs.getLong("personal_info_id"));

       return project;
    };

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Project> findAll() {
        String sql ="SELECT * FROM projects";
        return jdbcTemplate.query(sql,projectRowMapper);
    }

    @Override
    public Optional<Project> findById(Long id) {
        String sql ="SELECT * FROM projects WHERE id=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, projectRowMapper, id));
        }
        catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public Project save(Project project) {
        if (project.getId() ==null){
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String sql ="INSERT INTO projects (title, description, image_url, project_url, personal_info_id) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(connection -> {
                var ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, project.getTitle());
                ps.setString(2, project.getDescription());
                ps.setString(3, project.getImageUrl());
                ps.setString(4, project.getProjectUrl());
                ps.setLong(5, project.getPersonalInfoId());
                return ps;
            }, keyHolder);
            project.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());


        }else{
            String sql ="UPDATE projects SET title=?, description=?, image_url=?, project_url=?, personal_info_id=? WHERE id=?";
            jdbcTemplate.update(sql, project.getTitle(), project.getDescription(), project.getImageUrl(), project.getProjectUrl(), project.getPersonalInfoId(), project.getId());

        }
        return project;
    }

    @Override
    public void deleteById(Long id) {

        String sql="delete from projects where id=?";
        jdbcTemplate.update(sql,id);
    }
}
