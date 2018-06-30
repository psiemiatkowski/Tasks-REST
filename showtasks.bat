call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo Compilation error!
goto fail

:runbrowser
timeout 25
start chrome "http://localhost:8080/crud/v1/task/getTasks"
goto end

:fail
echo.
echo There were some errors

:end
echo.
echo Work showtasks.bat is finished