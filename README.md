##SecureVault
##Description
SecureVault is a simple and secure application to store and manage your credentials. It uses AES encryption to ensure that your secrets are stored safely. This project is developed using Java and provides a console-based interface for storing and retrieving sensitive data.

##Features
Store new credentials with a label (e.g., Gmail, GitHub).

Retrieve stored credentials by label.

Secure storage using AES encryption.

Technologies Used
Java

AES Encryption

Setup Instructions
Clone the repository:


cd SecureVault
Compile the Java files:

javac SecureVault.java
Run the application:

java SecureVault
Usage
##Store Credential:

Choose the “Store Credential” option.

Enter a label (like Gmail, GitHub, etc.) and the secret you want to save.

The secret will be encrypted and stored securely.

##Retrieve Credential:

Choose the “Retrieve Credential” option.

Enter the label you want to search for.

The stored secret will be decrypted and displayed.