@echo off
@rem 
@rem ------------------------------------------------------------
@rem Company: Software- und Organisations-Service GmbH
@rem Author : Andreas P�schel <andreas.pueschel@sos-berlin.com>
@rem Author : Oliver Haufe <oliver.haufe@sos-berlin.com>
@rem $Id:$
@rem Purpose: start FTP Processing
@rem ------------------------------------------------------------
SETLOCAL

set CLASSPATH_BASE=${INSTALL_PATH}
set CUR_DIR=%CD%
if not exist "%CLASSPATH_BASE%" (
  echo Classpath directory "%CLASSPATH_BASE%" does not exist.
  exit /B 1
)
if not defined JAVA_HOME set JAVA_HOME=${JAVA_HOME}
set JAVA_BIN=%JAVA_HOME%\bin\java.exe
if not exist "%JAVA_BIN%" set JAVA_BIN=java.exe

cd /D "%CLASSPATH_BASE%"

"%JAVA_BIN%" -classpath "*" sos.net.SOSFTPCommand %*
set /a RC=%ERRORLEVEL%

cd /D "%CUR_DIR%"

exit /B %RC%

ENDLOCAL
:final