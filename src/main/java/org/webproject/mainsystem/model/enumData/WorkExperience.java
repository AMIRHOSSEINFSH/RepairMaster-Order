package org.webproject.mainsystem.model.enumData;

public enum WorkExperience {
    UNDER_ONE(1),
    BETWEEN_ONE_TO_THREE(2),
    UPPER_THREE(3);
    private final int id;
    WorkExperience(int id) {
        this.id = id;
    }
    WorkExperience getWorkExperienceTypeFromYear(int year) {
        if(year < 0) return null;
        if(year <= 1) {
            return UNDER_ONE;
        }else if(year <= 3) {
            return BETWEEN_ONE_TO_THREE;
        }else {
            return UPPER_THREE;
        }
    }

    WorkExperience getWorkExperienceTypeById(int id) {
        for(WorkExperience workExperience : WorkExperience.values()) {
            if(workExperience.id == id) {
                return workExperience;
            }
        }
        return null;
    }

}
