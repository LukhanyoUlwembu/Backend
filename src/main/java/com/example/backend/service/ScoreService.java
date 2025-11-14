package com.example.backend.service;

import com.example.backend.model.*;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    public double calculateApplicationScore(Applicant applicant, JobRequisition jobRequisition, PreScreeningResult result) {
        double skillScore = matchSkills(applicant.getApplicantSkill(), jobRequisition.getJobSkills());
        double educationScore = matchEducation(applicant.getApplicantEducations(), jobRequisition.getJobEducation());
        double experienceScore = matchExperience(applicant.getWorkExperiences(), jobRequisition);


        double finalScore= (skillScore * 0.25) + (educationScore * 0.375) + (experienceScore * 0.375);
        return roundToTwoDecimal(finalScore);
    }

    private double matchSkills(List<ApplicantSkill> applicantSkills, List<JobSkill> requiredSkills) {
        if (requiredSkills == null || requiredSkills.isEmpty()) return 1.0;
        if (applicantSkills == null || applicantSkills.isEmpty()) return 1.0;

            long matched = requiredSkills.stream()
            .filter(req -> applicantSkills.stream()
                .anyMatch(app -> 
                    req.getSkillName() != null && app.getSkillName() != null &&
                    req.getSkillName().getName().equalsIgnoreCase(app.getSkillName().getName())
                )
            ).count();

        double percentage = ((double) matched / requiredSkills.size()) * 100.0;
        return normalize(percentage);
    }

    private double matchEducation(List<ApplicantEducation> applicantEducations, List<JobEducation> requiredEducations) {
        if (requiredEducations == null || requiredEducations.isEmpty()) return 1.0;
        if (applicantEducations == null || applicantEducations.isEmpty()) return 1.0;

        for (JobEducation jobEdu : requiredEducations) {
            for (ApplicantEducation appEdu : applicantEducations) {
                if (jobEdu.getEducationName() != null && appEdu.getEducationName() != null &&
                    jobEdu.getEducationName().equalsIgnoreCase(appEdu.getEducationName())) {
                    return 5.0; 
                }
            }
        }

        return 1.0;
    }

    private double matchExperience(List<WorkExperience> workExperiences, JobRequisition jobRequisition) {
    if (workExperiences == null || workExperiences.isEmpty()) return 1.0;

    ExperienceLevel level = jobRequisition.getLevel();
    if (level == null || level.getName() == null) {
        return 0.0; // no level info, no experience score
    }

    String levelName = level.getName().trim().toUpperCase();
    int requiredYears = getRequiredYearsFromLevelName(levelName);

    List<String> targetKeywords = extractKeywordsFromJob(jobRequisition);

    double relevantYears = 0.0;

    for (WorkExperience exp : workExperiences) {
        String jobTitle = (exp.getJobTitle() != null) ? exp.getJobTitle().toLowerCase() : "";
        boolean isRelevant = targetKeywords.stream().anyMatch(jobTitle::contains);

        if (isRelevant) {
            Date start = exp.getStartDate();
            Date end = (Boolean.TRUE.equals(exp.getCurrent()) || exp.getEndDate() == null) ? new Date() : exp.getEndDate();

            if (start != null && end != null && !end.before(start)) {
                // Calculate exact years as decimal
                long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(
                    start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                );
                relevantYears += daysBetween / 365.0;
            }
        }
    }

    if (requiredYears == 0) {
        // For entry-level, just check if any relevant experience exists
        return relevantYears > 0 ? 4.0 : 3.0;
    }

    // Cap at 100%
    double percentage = Math.min((relevantYears / requiredYears) * 100.0, 100.0);

    return normalize(percentage);
}

private double normalize(double percentageScore) {
    // Scale from 0–100 to 1–5
    return Math.max(1.0, (percentageScore / 100.0) * 4.0 + 1.0);
}

private double roundToTwoDecimal(double value) {
    return Math.round(value * 100.0) / 100.0;
}

private int getRequiredYearsFromLevelName(String levelName) {
    switch (levelName) {
        case "ENTRY_LEVEL": return 0;
        case "ONE_TO_TWO_YEARS": return 2;
        case "THREE_TO_FIVE_YEARS": return 5;
        case "FIVE_TO_SEVEN_YEARS": return 7;
        case "EIGHT_PLUS_YEARS": return 8;
        default: return 0; // fallback
    }
}

private List<String> extractKeywordsFromJob(JobRequisition jobRequisition) {
    String text = "";
    if (jobRequisition.getJobTitle() != null) text += jobRequisition.getJobTitle().toLowerCase() + " ";
    if (jobRequisition.getResponsibilities() != null) text += jobRequisition.getResponsibilities().toLowerCase();

    return Arrays.stream(text.split("\\s+"))
            .distinct()
            .filter(word -> word.length() > 2) // filter out short words
            .collect(Collectors.toList());
}


}