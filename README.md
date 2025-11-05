Simulador de Planificación de Disco (Interfaz visual)

Resumen
-------
Este proyecto es un simulador educativo de planificación de peticiones de disco. Actualmente la aplicación se centra en la interfaz y la visualización (modo "solo visual").

Características implementadas (visuales)
----------------------------------------
- Pantalla de login con panel informativo a la izquierda y formulario a la derecha.
- Panel izquierdo desplazable (scroll) que contiene:
  - Título del sistema, subtítulo y etiqueta del curso.
  - Información del equipo e integrantes.
  - Descripción del propósito del simulador.
- Panel derecho con formulario de inicio de sesión (usuario/contraseña) y botón estilizado.
- Ventana principal (visual) con:
  - Selector de algoritmo (FCFS, SSTF, SCAN, N-Step SCAN, C-SCAN, Eschenbach).
  - Botones: Generar Peticiones, Simular, Limpiar Historial (estilizados).
  - Visualización gráfica del disco (40 cilindros, 16 sectores) y un cabezal simulado.
  - Panel de log con título y fondo oscuro.

Nota importante
---------------
- Actualmente la mayor parte del código implementa la interfaz y la visualización; la simulación completa (procesamiento en tiempo real de peticiones con todas las reglas de cada algoritmo) está parcialmente preparada pero puede no estar finalizada. El objetivo solicitado fue primero que "se vea" bien; la lógica se puede completar después sobre esta base.

Cómo ejecutar (Windows)
-----------------------
Requisitos:
- JDK 8+ instalado
- Compilar y ejecutar desde tu IDE (IntelliJ/Eclipse) o usando javac/java desde PowerShell.

Usuario:
- []
- admin   
- carlos

Contraseña
- []
- admin   
- programa peor que yo

# Compilar (desde la raíz del proyecto donde está src) 
javac -d out -sourcepath src $(Get-ChildItem -Recurse -Filter "*.java" | ForEach-Object { $_.FullName })

# Ejecutar la pantalla de login
java -cp out com.diskscheduler.view.LoginScreen

(En la práctica, es más sencillo importar el proyecto en un IDE y ejecutar la clase `com.diskscheduler.view.LoginScreen`.)

Siguientes pasos sugeridos
-------------------------
- Ajustar apariencia fina de la scrollbar (color, ancho) para integrarla totalmente al tema.
- Completar la lógica de simulación y pasar de los indicadores visuales simulados a valores reales calculados por el `DiskScheduler`.
- Añadir tests unitarios para los algoritmos de planificación.

Contacto
--------
mandame mensaje por whats 