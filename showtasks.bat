call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browse
echo.
goto fail

:browse
wait
start chrome http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors