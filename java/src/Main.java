import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dummy_data.csv"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("processed_data.csv"));

            String line = reader.readLine();
            String[] headers = line.split(",");
            writer.write("Username,Birthdate,Age,Income,Debt,IncomeMinusDebt\n");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String username = values[0];
                Date birthdate = dateFormat.parse(values[1]);
                double income = Double.parseDouble(values[2]);
                double debt = Double.parseDouble(values[3]);

                long ageInMillis = currentDate.getTime() - birthdate.getTime();
                int age = (int) (ageInMillis / (1000 * 60 * 60 * 24 * 365.25));

                double incomeMinusDebt = income - debt;

                writer.write(username + "," + values[1] + "," + age + "," + income + "," + debt + "," + incomeMinusDebt + "\n");
            }

            reader.close();
            writer.close();

            System.out.println("ETL process completed.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
