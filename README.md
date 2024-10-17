# BioTech Monitoring System

## Descripción del Proyecto

El **Sistema de Monitoreo BioTech** es una plataforma diseñada para gestionar la captura, almacenamiento y sincronización de datos ambientales en áreas rurales, con un enfoque en la conservación de la biodiversidad. El sistema permite a biomonitores capturar imágenes, crear reportes y sincronizar información desde dispositivos móviles con conectividad limitada, asegurando que los datos sean accesibles y seguros en todo momento.

Este proyecto está compuesto por una aplicación móvil para la captura de datos y un servidor remoto para la sincronización y gestión de la información. Los principales usuarios incluyen científicos de campo, analistas de datos y administradores que participan en proyectos de monitoreo ambiental.

## Funcionalidades Principales

- **Gestión de Usuarios:** Registro, autenticación y gestión de perfiles de usuarios.
- **Captura de Imágenes:** Utiliza la cámara del dispositivo móvil para capturar imágenes y asociarlas a reportes.
- **Gestión de Reportes:** Los usuarios pueden crear, editar y eliminar reportes con imágenes y descripciones.
- **Sincronización de Datos:** Permite almacenar datos localmente en el dispositivo cuando no hay conexión y sincronizarlos automáticamente cuando hay conexión disponible.
- **Búsqueda y Filtrado:** Los usuarios pueden buscar reportes y filtrar información por criterios como fecha, ubicación o palabras clave.

## Estructura del Proyecto

### Componentes del Sistema

1. **Gestión de Usuarios:**
   - Registro de nuevos usuarios y autenticación segura con credenciales cifradas.
   - Gestión de perfiles de usuario con diferentes roles (biomonitor, administrador).

2. **Captura de Imágenes:**
   - Captura de imágenes utilizando la cámara del dispositivo móvil, con registro de ubicación (GPS), fecha y hora.
   - Almacenamiento local de las imágenes hasta que puedan ser sincronizadas con el servidor.

3. **Gestión de Reportes:**
   - Organización de las imágenes capturadas en reportes, que incluyen descripciones y comentarios.
   - Búsqueda y filtrado avanzado de reportes por múltiples criterios.

4. **Sincronización de Datos:**
   - Sincronización automática de datos almacenados localmente con el servidor remoto cuando haya una conexión a Internet disponible.
   - Registro de la última fecha de sincronización.

5. **Seguridad:**
   - Cifrado de contraseñas y protección de datos mediante protocolos seguros (HTTPS) durante la transmisión de la información.

### Estructura de Base de Datos

El sistema utiliza una base de datos relacional con las siguientes tablas principales:

- **Usuarios:** Información de los usuarios del sistema (nombre, correo, contraseña cifrada, rol).
- **Reportes:** Almacena los reportes creados por los usuarios y los vincula a las imágenes.
- **Imágenes:** Contiene las imágenes capturadas junto con la ubicación GPS y la fecha.
- **Sincronización:** Registra la última fecha de sincronización para cada dispositivo.

## Requisitos del Sistema

- **Android:** Dispositivos con Android para la captura de datos.
- **Servidor:** Un servidor remoto con capacidad para almacenar y procesar los datos sincronizados.
- **Conexión a Internet:** Se requiere conexión a Internet para la sincronización de datos, aunque la funcionalidad offline permite el uso en áreas sin conectividad.

## Instalación y Configuración

1. **Instalación de la aplicación móvil:**
   - Clona el repositorio en tu máquina local:
     ```bash
     git clone https://github.com/Acortes1403/SecurePath
     ```
   - Compila la aplicación en Android Studio y ejecuta en un dispositivo o emulador compatible.

2. **Configuración del servidor:**
   - Configura el servidor remoto para el almacenamiento de datos, utilizando una base de datos relacional.
   - Asegúrate de configurar la API de sincronización y el cifrado para proteger la transmisión de datos.

## Uso de la Aplicación

1. **Registro e inicio de sesión:**
   - Los usuarios nuevos deben registrarse proporcionando su nombre, correo y una contraseña segura.
   - Los usuarios registrados pueden iniciar sesión en la aplicación para acceder a sus reportes y capturar nuevas imágenes.

2. **Captura de imágenes y creación de reportes:**
   - Los biomonitores pueden capturar imágenes directamente desde la aplicación y crear reportes describiendo las observaciones.

3. **Sincronización de datos:**
   - Los datos se almacenan localmente en el dispositivo y se sincronizan con el servidor una vez que se detecta una conexión a Internet.

