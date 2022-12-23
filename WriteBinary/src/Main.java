import java.io.*;

class TestFileStream {
    public static void main(String[] args) throws IOException {
        try (FileOutputStream output = new FileOutputStream("temp.exe");) {
            for (int i = 1; i <= 10; i++)
                output.write(i);
        }

        try (FileInputStream input = new FileInputStream("temp.exe");) {
            int value;
            while ((value = input.read()) != -1)
                System.out.print(value + " ");
        }
    }
}