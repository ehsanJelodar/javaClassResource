"C:\Program Files\Java\jdk1.8.0_45\bin\javac" "dbToJavaClass.java"

::create jar file
"C:\Program Files\Java\jdk1.8.0_45\bin\jar" cfe dbToJavaClass.jar dbToJavaClass dbToJavaClass.class 

::run class
::"C:\Program Files\Java\jdk1.8.0_45\bin\java"  dbToJavaClass > out.java  ::Run and store output data in 'out.java' file.

:: run jar file
java -jar dbToJavaClass.jar  "4000" "data" "file.fw"  "binary" > db_fw.java

pause