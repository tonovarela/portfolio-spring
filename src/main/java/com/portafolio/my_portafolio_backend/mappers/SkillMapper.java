package com.portafolio.my_portafolio_backend.mappers;

import com.portafolio.my_portafolio_backend.dto.SkillDTO;
import com.portafolio.my_portafolio_backend.model.Skill;

public class SkillMapper {

    public static SkillDTO toDTO(Skill skill) {

        if (skill == null) {
            return null;
        }
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setId(skill.getId());
        skillDTO.setName(skill.getName());
        skillDTO.setLevelPercentage(skill.getLevelPercentage());
        skillDTO.setIconClass(skill.getIconClass());
        skillDTO.setPersonalInfoId(skill.getPersonalInfoId());
        return  skillDTO;
    }

    public static Skill toEntity(SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setId(skillDTO.getId());
        skill.setName(skillDTO.getName());
        skill.setLevelPercentage(skillDTO.getLevelPercentage());
        skill.setIconClass(skillDTO.getIconClass());
        skill.setPersonalInfoId(skillDTO.getPersonalInfoId());
        return skill;
    }

}
