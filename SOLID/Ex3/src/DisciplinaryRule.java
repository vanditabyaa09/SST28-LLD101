public class DisciplinaryRule implements Eligibility {
    @Override
    public String check(StudentProfile s) {
        if (s.disciplinaryFlag != LegacyFlags.NONE) {
            return "disciplinary flag present";
        }
        return null;
    }
}