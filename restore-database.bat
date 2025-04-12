@echo off
set BACKUP_FILE=parcial2db_backup_2025-04-12.sql

echo Copiando archivo de respaldo al contenedor...
docker cp backups\%BACKUP_FILE% mariadb:/tmp/%BACKUP_FILE%

echo Restaurando base de datos desde el respaldo...
docker-compose exec mariadb sh -c "mariadb -u root -proot parcial2db < /tmp/%BACKUP_FILE%"

echo Restauración completada.