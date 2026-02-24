package com.portafolio.my_portafolio_backend.repository;

import com.portafolio.my_portafolio_backend.model.PersonalInfo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class PersonalInfoRepository implements  IPersonalInfoRepository {

    private  final JdbcTemplate jdbcTemplate;
//    private final RowMapper<PersonalInfo> personalInfoRowMapper = (rs, rowNum) -> {;
//
//        PersonalInfo personalInfo = new PersonalInfo();
//        personalInfo.setId(rs.getLong("id"));
//        personalInfo.setFirstName(rs.getString("name"));
//        personalInfo.setEmail(rs.getString("email"));
//        personalInfo.setTitle(rs.ge);
//
//        return personalInfo;
//    };

    public PersonalInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        return null;
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql ="SELECT * FROM personal_info";
        //jdbcTemplate.query(sql);

        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }
}
