# Documentación de API Cars - Colección de Insomnia

Esta carpeta contiene la colección de Insomnia para probar la API de Cars del Parcial 2.

## Archivos incluidos

- `Insomnia_Car_API_Collection.json`: Colección en formato JSON
- `Insomnia_Car_API_Collection.yaml`: Colección en formato YAML
- `Insomnia_Car_API_Collection.har`: Registro de solicitudes HTTP

## Cómo importar la colección

1. Abre Insomnia
2. Haz clic en "Create" y selecciona "Import from File"
3. Selecciona el archivo `Insomnia_Car_API_Collection.json` o `Insomnia_Car_API_Collection.yaml`
4. La colección se importará con todas las solicitudes configuradas

## Endpoints incluidos

La colección incluye los siguientes endpoints:

1. **Get All** - `GET http://localhost:8080/api/cars`
   - Obtiene todos los coches disponibles

2. **Get By Id** - `GET http://localhost:8080/api/cars/{id}`
   - Obtiene un coche específico por su ID

3. **Create** - `POST http://localhost:8080/api/cars`
   - Crea un nuevo coche
   - Requiere un cuerpo JSON con los datos del coche

4. **Update** - `PUT http://localhost:8080/api/cars/{id}`
   - Actualiza un coche existente
   - Requiere un cuerpo JSON con los datos actualizados

5. **Delete** - `DELETE http://localhost:8080/api/cars/{id}`
   - Elimina un coche por su ID

## Cómo ejecutar las pruebas

1. Asegúrate de que la aplicación esté en ejecución
2. Abre la colección en Insomnia
3. Ejecuta cada solicitud en el orden recomendado:
   - Primero crea un coche (Create)
   - Luego obtén todos los coches (Get All)
   - Obtén un coche específico (Get By Id)
   - Actualiza un coche (Update)
   - Finalmente elimina un coche (Delete)
4. Verifica que cada solicitud devuelva el código de estado esperado y los datos correctos