/*
 * Copyright 2009 Lukasz Wozniak
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 * http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.sos.VirtualFileSystem.CredentialStore.KeePass.pl.sind.keepass.kdb;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.sos.VirtualFileSystem.CredentialStore.KeePass.pl.sind.keepass.exceptions.KeePassDataBaseException;
import com.sos.VirtualFileSystem.CredentialStore.KeePass.pl.sind.keepass.exceptions.UnsupportedDataBaseException;

public abstract class KeePassDataBaseManager {
	@SuppressWarnings("unused") private final String		conClassName	= this.getClass().getSimpleName();
	@SuppressWarnings("unused") private static final String	conSVNVersion	= "$Id$";
	@SuppressWarnings("unused") private final Logger		logger			= Logger.getLogger(this.getClass());

	@SuppressWarnings("resource") public static KeePassDataBase openDataBase(final File dbFile, final File keyFile, final String password) throws IOException,
			UnsupportedDataBaseException, KeePassDataBaseException {
		return openDataBase(new FileInputStream(dbFile), keyFile == null ? null : new FileInputStream(keyFile), password);
	}

	public static KeePassDataBase openDataBase(final InputStream dbFile, final InputStream keyFile, final String password) throws UnsupportedDataBaseException,
			IOException, KeePassDataBaseException {
		return KeePassDataBaseFactory.loadDataBase(dbFile, keyFile, password);
	}

	public static void saveDataBase(final KeePassDataBase dataBase, final File dbFile, final File keyFile, final String password) throws FileNotFoundException {
		saveDataBase(dataBase, new FileOutputStream(dbFile), keyFile == null ? null : new FileInputStream(keyFile), password);
	}

	public static void saveDataBase(final KeePassDataBase dataBase, final OutputStream dbFile, final InputStream keyFile, final String password) {
		KeePassDataBaseFactory.saveDataBase(dataBase, dbFile, keyFile, password);
	}
}
