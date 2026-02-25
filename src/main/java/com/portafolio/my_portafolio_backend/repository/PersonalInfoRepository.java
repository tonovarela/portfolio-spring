package com.portafolio.my_portafolio_backend.repository;

import com.portafolio.my_portafolio_backend.model.PersonalInfo;

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

public class PersonalInfoRepository implements  IPersonalInfoRepository {

    private  final JdbcTemplate jdbcTemplate;
    private final RowMapper<PersonalInfo> personalInfoRowMapper = (rs, rowNum) -> {;
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(rs.getLong("id"));
        personalInfo.setFirstName(rs.getString("first_name"));
        personalInfo.setLastName(rs.getString("last_name"));
        personalInfo.setProfileDescription(rs.getString("profile_description"));
        personalInfo.setProfileImageUrl(rs.getString("profile_image_url"));
        personalInfo.setYearsOfExperience(rs.getObject("years_of_experience", Integer.class));
        personalInfo.setEmail(rs.getString("email"));
        personalInfo.setPhone(rs.getString("phone"));
        personalInfo.setLinkedinUrl(rs.getString("linkedin_url"));
        personalInfo.setGithubUrl(rs.getString("github_url"));
         personalInfo.setTitle(rs.getString("title"));
        return personalInfo;
    };

    public PersonalInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        if (personalInfo.getId() ==null){
            KeyHolder keyHolder = new GeneratedKeyHolder();

            String sql = "INSERT INTO personal_info (first_name, last_name, profile_description, profile_image_url, years_of_experience, email, phone, linkedin_url, github_url, title) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(connection -> {
                var ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, personalInfo.getFirstName());
                ps.setString(2, personalInfo.getLastName());
                ps.setString(3, personalInfo.getProfileDescription());
                ps.setString(4, personalInfo.getProfileImageUrl());
                if (personalInfo.getYearsOfExperience() != null) {
                    ps.setInt(5, personalInfo.getYearsOfExperience());
                } else {
                    ps.setNull(5, java.sql.Types.INTEGER);
                }
                ps.setString(6, personalInfo.getEmail());
                ps.setString(7, personalInfo.getPhone());
                ps.setString(8, personalInfo.getLinkedinUrl());
                ps.setString(9, personalInfo.getGithubUrl());
                 ps.setString(10, personalInfo.getTitle());
                return ps;
            }, keyHolder);
            personalInfo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        }else{
            String sql = "UPDATE personal_info SET first_name=?, last_name=?, profile_description=?, profile_image_url=?, years_of_experience=?, email=?, phone=?, linkedin_url=?, github_url=?, title=? WHERE id=?";
            jdbcTemplate.update(sql, personalInfo.getFirstName(), personalInfo.getLastName(), personalInfo.getProfileDescription(), personalInfo.getProfileImageUrl(), personalInfo.getYearsOfExperience(), personalInfo.getEmail(), personalInfo.getPhone(), personalInfo.getLinkedinUrl(), personalInfo.getGithubUrl(),personalInfo.getTitle(),personalInfo.getId());

        }
        return personalInfo;
    }

//    @Override
//    public Optional<PersonalInfo> findById(Long id) {
//        String sql= "SELECT * FROM personal_info WHERE id=?";
//        List<PersonalInfo> results = jdbcTemplate.query(sql, personalInfoRowMapper, id);
//        return results.stream().findFirst();
//    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        String sql= "SELECT * FROM personal_info WHERE id=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, personalInfoRowMapper, id));
        }
        catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql ="SELECT * FROM personal_info";
        return jdbcTemplate.query(sql,personalInfoRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql="delete from personal_info where id=?";
        jdbcTemplate.update(sql,id);
    }
}
