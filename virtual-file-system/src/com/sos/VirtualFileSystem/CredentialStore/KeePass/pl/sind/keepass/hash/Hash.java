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
package com.sos.VirtualFileSystem.CredentialStore.KeePass.pl.sind.keepass.hash;

public interface Hash {
	public static final String SHA_256="SHA-256";
	public static final String ARC_4="ARC4";

	public String getId();
	
	public byte[] hash(byte[] data);
	
	public void reset();
	
	public void update(byte[] data);
	
	public byte[] digest();

	public void update(byte[] data, int offset, int lenght);
}
