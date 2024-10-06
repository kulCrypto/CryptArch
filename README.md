CryptArch
10/6/2024 3:58 PM 
•	Initial requirement was to secure my sensitive files.
•	Started checking for available libraries for java encryption.
•	There were two options, 
1 using ‘javax.crypto’ for encryption
2 ‘net.lingala.zip4j’ available for creating password protected zip.
•	Second option found convenient so started with ZIP4J.
•	As issue is security, web app is not an option.
•	JavaFX used for GUI.
•	Application is ready but there is an issue-
We always save our files with some meaningful names.
Unauthorised person can traverse through compressed zip file.
•	There is a solution-
compress that directory multiple times.
zip file is treated as a file so if we compress it second time, unauthorised user will not be able to traverse through it.
•	So, next version of application will have additional input parameter- Rounds.
•	Rounds- will have integer value.
