@echo off
set DATE=%date:~-4,4%-%date:~-7,2%-%date:~-10,2%
set BACKUP_FILE=parcial2db_backup_%DATE%.sql
set BACKUP_DIR=backups

echo Verificando si existe la carpeta de respaldos...
if not exist "%BACKUP_DIR%" (
    echo Creando carpeta de respaldos...
    mkdir "%BACKUP_DIR%"
)

echo Realizando copia de seguridad de la base de datos...
docker exec mariadb sh -c "mariadb-dump -u root -proot parcial2db > /tmp/%BACKUP_FILE%"
docker cp mariadb:/tmp/%BACKUP_FILE% "%BACKUP_DIR%\%BACKUP_FILE%"

echo Copia de seguridad completada: %BACKUP_DIR%\%BACKUP_FILE%