@echo off
chcp 1252 >nul
echo Compilando o projeto...
rem Como já citei antes eu setei o JAVA_HOME nas variaveis de ambiente ligada a pasta downloads por preferência pessoal
set JAVA_HOME=%userprofile%\Downloads\jdk-11.0.25_windows-x64_bin\jdk-11.0.25
set PATH=%JAVA_HOME%\bin;%PATH%

rem Cria diretório de saída se não existir, coloquei para ignorar no .gitignore para não entrar o projeto no repositório
if not exist ".\classes" mkdir classes

rem Verifica se o MySQL Connector existe
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

rem Compila as classes do projeto
javac -d classes -cp classes;./lib/mysql-connector-j-8.0.32.jar ../src/br/com/biblioteca/modelo/*.java ../src/br/com/biblioteca/util/*.java ../src/br/com/biblioteca/dao/*.java ../src/br/com/biblioteca/*.java

if %ERRORLEVEL% == 0 (
  echo Compilação concluída com sucesso!
) else (
  echo Erro na compilação!
)

pause
