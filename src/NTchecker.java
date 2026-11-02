import java.util.List;

public class NTchecker {
    private static final List<String> VALID_NT = List.of("A", "C", "G", "T");

    public static boolean isValidDna(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        String cleanInput = input.toUpperCase().replaceAll("\\s", "");
        for (int i = 0; i < cleanInput.length(); i++) {
            if (!VALID_NT.contains(String.valueOf(cleanInput.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
