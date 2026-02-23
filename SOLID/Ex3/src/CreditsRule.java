public class CreditsRule implements Eligibility {
    @Override
    public String check(StudentProfile s) {
        if (s.earnedCredits < 20) {
            return "credits below 20";
        }
        return null;
    }
}