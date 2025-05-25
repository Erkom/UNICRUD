@echo off
chcp 65001 >nul
echo Executando o Sistema de Gerenciamento de Biblioteca Escolar...
rem Configura o JAVA_HOME e PATH
rem Altere o caminho abaixo para o local onde o JDK está instalado
rem Exemplo: set JAVA_HOME=C:\Program Files\Java\jdk-11.0.25
rem Eu particulamente preferi deixar em downloads por preferência pessoal minha
set JAVA_HOME=%userprofile%\Downloads\jdk-11.0.25_windows-x64_bin\jdk-11.0.25
set PATH=%JAVA_HOME%\bin;%PATH%

rem Cuidado pois como usa o um script de download do PowerShell, ele pode não funcionar em versões mais antigas do Windows
rem Por conta do script também pode bloquear com suspeita de vírus, então se não funcionar, baixe manualmente o MySQL Connector
rem Verifica se o MySQL Connector existe na pasta lib
if not exist ".\lib\mysql-connector-j-8.0.32.jar" (
  echo ERRO: Driver JDBC do MySQL nao encontrado.
  echo Baixando o MySQL Connector...
  mkdir lib 2>nul
  powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.32/mysql-connector-j-8.0.32.jar' -OutFile './lib/mysql-connector-j-8.0.32.jar'"
  if not exist ".\lib\mysql-connector-j-8.0.32.jar" (
    echo Falha ao baixar o MySQL Connector. Por favor, baixe manualmente.
    echo URL: https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.32/mysql-connector-j-8.0.32.jar
    echo Salve o arquivo como lib\mysql-connector-j-8.0.32.jar
    pause
    exit /b 1
  ) else (
    echo MySQL Connector baixado com sucesso!
  )
)

rem Executa a aplicação
java -cp classes;./lib/mysql-connector-j-8.0.32.jar br.com.biblioteca.BibliotecaApp

pause
