@echo off
REM =================================================
REM Script pour compiler des mods spécifiques sur Windows
REM Usage: compile ModA ModB ModLib
REM =================================================

IF "%~1"=="" (
    echo Veuillez indiquer au moins un mod à compiler.
    echo Exemple : compile ModA ModB
    exit /b 1
)

SETLOCAL

REM Parcours de tous les arguments (mods)
:LOOP
IF "%~1"=="" GOTO ENDLOOP

SET MOD=%~1
IF EXIST "%MOD%" (
    echo ===============================
    echo Compilation de %MOD%...
    echo ===============================
    cd "%MOD%"
    IF EXIST gradlew (
        call gradlew build
    ) ELSE (
        gradle build
    )
    cd ..
) ELSE (
    echo Mod "%MOD%" introuvable !
)

SHIFT
GOTO LOOP

:ENDLOOP
echo ===============================
echo Compilation terminée !
echo ===============================
pause
ENDLOCAL
