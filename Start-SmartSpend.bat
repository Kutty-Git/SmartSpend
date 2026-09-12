@echo off
title SmartSpend Launcher
setlocal

set "PROJECT=%~dp0"
set "BACKEND=%PROJECT%backend"
set "FRONTEND=%PROJECT%frontend"

echo ========================================
echo        SmartSpend Launcher
echo ========================================
echo.
echo Starting Backend...
start "SmartSpend Backend" cmd /k "cd /d "%BACKEND%" && set "JWT_SECRET=SmartSpend-My-Super-Secret-Key-2026-For-JWT-Testing" && mvn spring-boot:run"

timeout /t 8 /nobreak >nul

echo Starting Frontend...
start "SmartSpend Frontend" cmd /k "cd /d "%FRONTEND%" && python -m http.server 5500"

timeout /t 3 /nobreak >nul

echo Opening SmartSpend...
start "" "http://localhost:5500/"

echo.
echo SmartSpend is starting.
echo Keep both CMD windows open while using the project.
pause
