import java.io.*;

// Custom Decorator to convert characters to lowercase
class LowerCaseInputStream extends FilterInputStream {

    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == -1) return c;
        return Character.toLowerCase(c);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = super.read(b, off, len);
        if (result == -1) return result;

        for (int i = off; i < off + result; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}

public class DecoratorUserInput {
    public static void main(String[] args) {
        System.out.print("Enter text: ");

        try {
            // Apply decorator on System.in
            LowerCaseInputStream lci = new LowerCaseInputStream(System.in);
            BufferedReader br = new BufferedReader(new InputStreamReader(lci));

            // Read input line
            String line = br.readLine();

            System.out.println("Converted: " + line);

            br.close();
            lci.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
