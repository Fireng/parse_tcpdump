import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunCommand {

    public static void main(String[] args) {
        // Команда для выполнения
        String command = "tcpdump"; // для Windows можно использовать "ping -n 4 google.com"

        try {
            // Создаем процесс для выполнения команды
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", command); // для Windows используйте: processBuilder.command("cmd.exe", "/c", command);

            // Запускаем процесс
            Process process = processBuilder.start();

            // Читаем вывод команды из потока ввода процесса
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Читаем ошибки команды из потока ошибок процесса
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }

            // Ожидаем завершения процесса
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}