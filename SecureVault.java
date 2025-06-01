package SecureVault;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SecureVault {
    private static final String ENCRYPTION_ALGO = "AES";
    private static final byte[] secretKey = new byte[] { 'S', 'e', 'c', 'u', 'r', 'e', 'K', 'e', 'y', '1', '2', '3', '4', '5', '6', '7' };
    private Map<String, String> vault = new HashMap<>();

    public static void main(String[] args) {
        SecureVault vaultApp = new SecureVault();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- SecureVault Menu ---");
            System.out.println("1. Store Credential");
            System.out.println("2. Retrieve Credential");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int option = input.nextInt();
            input.nextLine(); // flush newline

            switch (option) {
                case 1:
                   System.out.print("Enter label (e.g., Gmail, GitHub): ");
                    String label = input.nextLine();
                    System.out.print("Enter your secret: ");
                    String secret = input.nextLine();
                    vaultApp.store(label, secret);
                    break;
                case 2:
                    System.out.print("Enter label to search: ");
                    label = input.nextLine();
                    String result = vaultApp.retrieve(label);
                    System.out.println("Decrypted Secret: " + result);
                    break;
                case 3:
                    System.out.println("Exiting SecureVault. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public void store(String label, String secret) {
        try {
            String encrypted = encrypt(secret);
            vault.put(label, encrypted);
            System.out.println("Secret stored securely under label: " + label);
        } catch (Exception e) {
            System.out.println("Failed to encrypt and store secret.");
            e.printStackTrace();
        }
    }

    public String retrieve(String label) {
        try {
            String encrypted = vault.get(label);
            if (encrypted == null) {
                return "No entry found for: " + label;
            }
            return decrypt(encrypted);
        } catch (Exception e) {
            System.out.println("Failed to decrypt secret.");
            e.printStackTrace();
            return null;
        }
    }

    private String encrypt(String plainText) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey, ENCRYPTION_ALGO);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String decrypt(String encryptedText) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey, ENCRYPTION_ALGO);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}
