import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Actividad9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println("Ingrese una contraseña para validar (o 'exit' para salir):");
        String input;

        while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")) {
            final String password = input;
            executorService.execute(() -> {
                String result = isValidPassword(password)
                        ? "válida"
                        : "no válida";
                System.out.println("La contraseña \"" + password + "\" es " + result + ".");
            });
            System.out.println("Ingrese otra contraseña para validar (o 'exit' para salir):");
        }

        executorService.shutdown();
        System.out.println("Validación de contraseñas finalizada.");
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*\\d.*") &&
                password.chars().filter(Character::isUpperCase).count() >= 2 &&
                password.chars().filter(Character::isLowerCase).count() >= 3 &&
                password.matches(".*[^a-zA-Z0-9].*");
    }
}