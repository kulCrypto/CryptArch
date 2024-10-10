CryptArch
10/6/2024 3:58 PM 
•	The initial requirement was to secure my sensitive files.
•	Started checking for available libraries for Java encryption.
•	There were two options, 
1 using ‘javax.crypto’ for encryption
2 ‘net.lingala.zip4j’ available for creating password-protected zip.
•	The second option was found convenient so started with ZIP4J.
•	As the issue is security, the web app is not an option.
•	JavaFX used for GUI.
•	Application is ready but there is an issue-
We always save our files with some meaningful names.
An unauthorized person can traverse through a compressed zip file.
•	There is a solution-
encrypt the zip file using the AES256 algorithm.
10/10/2024
zip file encrypted using the AES256 algorithm with the password.
SHA256 hashing function used for password.
