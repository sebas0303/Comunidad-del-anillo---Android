# Laboratorio UT5 - La Comunidad del Anillo (Android PMDM - DAM)

Aplicación Android nativa desarrollada para el ciclo formativo de grado superior de **Desarrollo de Aplicaciones Multiplataforma (DAM)**, asignatura de *Programación Multimedia y Dispositivos Móviles*.

## 🎯 Requisitos Implementados
1. **SplashActivity**: Pantalla de arranque con `ProgressBar` de 3 segundos mediante `Handler(Looper.getMainLooper())`. Llamada obligatoria a `finish()` para no dejar rastro en el backstack.
2. **MainActivity & EtapaAdapter**: `ListView` optimizado con reciclaje de vistas mediante `convertView` y patrón `ViewHolder`.
3. **Menú de Opciones (Toolbar)**: Opción "Abandonar Misión" con diálogo de confirmación y cierre mediante `finishAffinity()`.
4. **Menú Contextual Flotante**: Registro mediante `registerForContextMenu(lvEtapas)`. Al mantener pulsado, abre opción "Inspeccionar Terreno".
5. **DetalleActivity & Intents**: Navegación con `Intent` explícito e inyección de extras (`EXTRA_NOMBRE`, `EXTRA_PELIGRO`, etc.), desempaquetados con `intent.getStringExtra()`.

## 🚀 Cómo abrir en Android Studio
1. Abre **Android Studio** (Hedgehog, Iguana, Jellyfish, Koala o superior).
2. Haz clic en **File -> Open...** y selecciona esta carpeta descomprimida.
3. Espera a que termine la sincronización de Gradle (`Sync Project with Gradle Files`).
4. Selecciona un dispositivo virtual (AVD con API 24 o superior) o dispositivo físico.
5. Haz clic en **Run 'app' (Shift + F10)**.

Profesor: Ciclo DAM - Programación Multimedia y Dispositivos Móviles
